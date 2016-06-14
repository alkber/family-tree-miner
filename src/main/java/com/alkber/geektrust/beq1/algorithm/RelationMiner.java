/*
 * Copyright 2016 Althaf K Backer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alkber.geektrust.beq1.algorithm;

import com.alkber.geektrust.beq1.model.Subject;
import com.alkber.geektrust.beq1.relation.RelationAliasIndexer;
import com.alkber.geektrust.beq1.relation.RelationFactory;
import com.alkber.geektrust.beq1.relation.base.Relation;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * This another classical map reduce problem, here two <code> Subjects </code> are given, algorithm
 * has to find out all possible relations subject A has with subject B.
 *
 * It is good to know how the class <code> Relation </code> is structured before diving into
 * details.
 *
 * First of all, algorithm assumes it already has a set of relation aliases like Mother, Father etc
 * and there are respective implementation <code> Relation </code> of such aliases in the package
 * path <code> com.alkber.geektrust.beq1.relation </code>. This is required to create the relation
 * at runtime using reflection.
 *
 * For each relation instance created from the relation aliases for this pivot subject, a call
 * to <code> has(...) </code> is made from relation, with possible relative as parameter. If true
 * such a relative exist in that relation.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class RelationMiner {

	/* final result containing the relations */
	private final Set<String> relations = new HashSet<String>();
	/* the main pivot subject */
	private final Subject        wrtSubject;
	/* the relative with pivot subject */
	private final Subject        possibleRelative;
	private final int            relationsCnt;

	/* thread synchronization */
	private final CountDownLatch countDownLatch;
	private int MAX_POOL_SZ   = 4;
	private int MIN_POOL_SZ   = 4;
	private int MAX_IDLE_TIME = 5;

	/**
	 * Create an instance of relation miner for the pivot subject <code>wrtSubject</code> and
	 * possible relative <code> possibleRelative </code>.
	 *
	 * @param wrtSubject
	 * @param possibleRelative
	 */
	public RelationMiner(Subject wrtSubject, Subject possibleRelative) {

		this.wrtSubject = wrtSubject;
		this.possibleRelative = possibleRelative;
		this.relationsCnt = RelationAliasIndexer.getReadOnlyAliasList().size();
		this.countDownLatch = new CountDownLatch(relationsCnt);
	}

	/**
	 * Find the possible relations
	 *
	 * @return St of relation aliases
	 */
	public Set<String> find() {

		// since the total number of relations alias are pre known we use ArrayBlockingQueue
		BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(relationsCnt);
		// prepopulate task queue with relation miner task all the relations alias
		for(String currentAlias : RelationAliasIndexer.getReadOnlyAliasList()) {

			taskQueue.add(new RelationMinerTask(currentAlias));
		}

		try {

			ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
					MAX_POOL_SZ,
					MIN_POOL_SZ,
					MAX_IDLE_TIME,
					TimeUnit.MINUTES,
					taskQueue);

			threadPool.prestartAllCoreThreads();
			// blocks untill all the threads have completed the execution
			countDownLatch.await();
			threadPool.shutdown();
		} catch(InterruptedException e) {

			e.printStackTrace();
		}
		// return the list of relations aliases
		return relations;
	}

	private void addToRelations(String relation) {

		synchronized(relations) {

			relations.add(relation);
		}
	}

	private class RelationMinerTask implements Runnable {

		private final String possibleRelation;

		public RelationMinerTask(String possibleRelation) {

			this.possibleRelation = possibleRelation;
		}

		@Override
		public void run() {

			// create a an instance of relation from the alias, this is a run time polymorphism.
			// relation base class can get instantiated with a Mother, Father, Brother etc
			// implementations of the relation.
			Relation relation = RelationFactory.create(possibleRelation, wrtSubject);

			if(relation != null) {

				if(relation.has(possibleRelative)) {

					addToRelations(possibleRelation);
				}
			}
			countDownLatch.countDown();
		}
	}
}
