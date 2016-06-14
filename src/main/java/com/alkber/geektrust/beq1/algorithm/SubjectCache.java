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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Since we have large family tree, searching for a subject can be costly affair, as the
 * algorithm used has to traverse all the children until end of the tree. Which in worst
 * case can be O(n). To avoid costly search, we cache the object reference to each subject
 * so created.
 *
 * A HashSet is used to cache the reference so the look up will take only O(1) under normal
 * circumstances.
 *
 * The inner working is quite simple however, is not obvious since, there are no explicit calls
 * to <code> SubjectCache </code>.
 *
 * Whenever a subject instance is created, the constructor of the <code Subject </code> calls
 * <code> Subject.NewInstanceListener </code>, which typically is the <code> SubjectCache </code>.
 *
 * Throughout the life time of application there is only one subject cache and this is implemented
 * as a singleton pattern.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class SubjectCache implements Subject.NewInstanceListener {

	private final Set<Subject> cache = new HashSet<Subject>();
	/* Singleton pattern */
	private final static SubjectCache singleton = new SubjectCache();

	public static SubjectCache getInstance() {

		return singleton;
	}

	private SubjectCache() {

	}

	/**
	 * Call back to get notifications when a new subject is created.
	 *
	 * @param newSubject
	 */
	@Override
	public void onNewSubjectCreate(Subject newSubject) {

		cache.add(newSubject);
	}

	/**
	 * Search for a subject by name
	 *
	 * @param name Name of the subject
	 * @return <code> Subject </code> if found else <code> null </code>
	 */
	public static Subject searchSubjectByName(String name) {

		for(Subject current : getInstance().cache) {
			// case insensitive search
			if(name.toLowerCase().equals(current.getName().toLowerCase())) {

				return current;
			}
		}
		return null;
	}

	/**
	 * Returns cache set as a read only, this might be required for some algorithms that might
	 * need to do custom search.
	 *
	 * @return Read Only Subject cache
	 */
	public Set<Subject> getReadOnlyCache() {

		return Collections.unmodifiableSet(cache);
	}
}
