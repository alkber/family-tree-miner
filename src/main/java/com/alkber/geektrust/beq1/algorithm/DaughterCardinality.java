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

import com.alkber.geektrust.beq1.model.DaughterCardinalityResult;
import com.alkber.geektrust.beq1.model.Subject;
import com.alkber.geektrust.beq1.relation.Children;
import com.alkber.geektrust.beq1.relation.Daughter;
import com.alkber.geektrust.beq1.relation.DaughterInLaw;
import com.alkber.geektrust.beq1.relation.SonInLaw;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This is a classic map reduce problem. There is a starting subject, first all the daughters and
 * daughter in laws of subject are taken and split into different thread of execution to compute
 * the (child) daughter count of each  of the starting subject. For each daughter/daughter in law
 * so taken, their daughters and daughter in laws are taken next and split into the different thread
 * of execution.
 * <p>
 * They all update a thread safe variable for the daughter count and list of subjects subject
 * associated with that count. Finally when all the daughters in the family tree are considered,
 * we will have highest value for count of the daughters. This count is used to map out set of
 * subjects having same highest count of daughters.
 * <p>
 * The question didn't mention about a draw case, however it is handled as well. So there would be
 * multiple subjects in the result.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class DaughterCardinality {

	/* a thread safe blockign queue to add daughter counting task */
	private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
	/* the root subject to start the counting from   */
	private final Subject wrtSubject;
	/* the highest value for the count of daughters  */
	private int                         daughterCntMax = 0;
	/* to map count and subjects associated with the count */
	private Map<Integer, Set<Subject>> resultCache = new HashMap<>();

	private int MAX_POOL_SZ   = 4;
	private int MIN_POOL_SZ   = 4;
	private int MAX_IDLE_TIME = 5;

	/**
	 * @param wrtSubject the root subject to start the traversal from
	 */
	public DaughterCardinality(Subject wrtSubject) {

		this.wrtSubject = wrtSubject;
		// default result
		resultCache.put(0, new HashSet<>());
	}

	/**
	 * Submit next set of tasks to the thread pool.
	 *
	 * @param currentSubject the subject for which children has to submitted
	 */
	private void submitBatchTask(Subject currentSubject) {

		List<Subject> possibleWinners = (new Daughter(currentSubject)).find();
		possibleWinners.addAll((new DaughterInLaw(currentSubject).find()));

		try {

			for(Subject current : possibleWinners) {

				if (current.getSex() == Subject.FEMALE) {

					taskQueue.put(new DaughterCountCalculatorTask(current));
				}
			}

		} catch(Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Call to start search for max number of daughters
	 *
	 * @return an instance of <code>DaughterCardinalityResult</code>
	 */
	public DaughterCardinalityResult find() {

		submitBatchTask(wrtSubject);

		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(MAX_POOL_SZ, MIN_POOL_SZ,
				MAX_IDLE_TIME, TimeUnit.MINUTES, taskQueue);
		// prestart the threads else the task submitted wouldn't be executed
		threadPool.prestartAllCoreThreads();

		// this kind of poll loop shouldn't be considered when the producer
		// thread has network latency when submitting task, here it is assumed
		// that tasks are submitted without delay, while here if task queue is
		// empty it means all the tasks has been taken for execution, it doenot
		// mean it tasks have completed.
		while(taskQueue.peek() != null) {

			try {

				Thread.sleep(1000);
			} catch(InterruptedException e) {

				e.printStackTrace();
			}
		}


		threadPool.shutdown();
		try {

			threadPool.awaitTermination(5, TimeUnit.DAYS.MINUTES);
		} catch(InterruptedException e) {

			e.printStackTrace();
		}

		// after execution, daughterCntMax has the count of max daughters  for a subject
		// since, there could be a draw case, we have a multimap structure to deal with
		// this issue. So, it might return multiple subjects.
		return new DaughterCardinalityResult(daughterCntMax, new ArrayList<>(resultCache.get
				(daughterCntMax)));
	}

	/**
	 * Update the result cache with the subject's daughter count. If daughter count is
	 * found to be greater than the <code> daughterCntMax </code>, the it is updated with
	 * new value from <code>currentDaughterCount</code> and subject is added to the multimap
	 * with key as <code>currentDaughterCount</code>.
	 *
	 * @param currentDaughterCount
	 * @param subject
	 */
	private void update(int currentDaughterCount, Subject subject) {

		synchronized(wrtSubject) {

			//System.out.println(subject.getName() + " has " + currentDaughterCount + " daughters// ");

			if(daughterCntMax < currentDaughterCount) {

				daughterCntMax = currentDaughterCount;
			}
			// this is just a book marks with subjects and their daughter count.
			if(resultCache.get(currentDaughterCount) == null) {

				resultCache.put(currentDaughterCount, new HashSet<>());
				resultCache.get(currentDaughterCount).add(subject);
			} else {

				resultCache.get(currentDaughterCount).add(subject);
			}
		}
	}

	/**
	 * Execution chunk, for finding the daughter count for a subject. Here, once the count for
	 * the current subject is found. The current subject's children (males and females) are
	 * added the next task queue. Like wise this process is repeated for each subject that enters
	 * this execution chunk.
	 */
	private class DaughterCountCalculatorTask implements Runnable {

		private final Subject currentSubject;

		public DaughterCountCalculatorTask(Subject current) {

			this.currentSubject = current;
		}

		@Override
		public void run() {
			// get all the daughters for current subject
			List<Subject> daughters = (new Daughter(currentSubject)).find();
			// update the result cache, if required else ignored
			update(daughters.size(), currentSubject);
			// submit current subjects children for same iteration
			submitBatchTask(currentSubject);
		}
	}
}
