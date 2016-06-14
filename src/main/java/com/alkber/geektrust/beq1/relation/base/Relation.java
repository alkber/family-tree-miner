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

package com.alkber.geektrust.beq1.relation.base;

import com.alkber.geektrust.beq1.model.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for all the relation definitions. All new relation must derive from this base
 * class. Main, operation would be instantiation of the class with a subject, then finding a set
 * of subjects related to the initialized subject. The <code>find()</code> operation decides how
 * subjects are mapped to the current relation.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public abstract class Relation {

	protected final Subject subject;

	/**
	 * Made private to ensure that constructor only valid constructors with <code> Subject </code>
	 * as parameter is called.
	 */
	private Relation() {

		subject = null;
	}

	/**
	 * The subject under consideration for the defined relation ship.
	 *
	 * @param subject
	 */
	public Relation(Subject subject)  {

		this.subject = subject;
	}

	/**
	 * Search for all the subject[s] that are part of the current relationship context, with
	 * respect to the subject. So, a sister relation would return all the sisters for the current
	 * subject.
	 *
	 * @return List of subject that are part of current relationship context.
	 */
	public abstract List<Subject> find();

	/**
	 * Search if the given <code> possibleRelative </code> is part of current <code> subject </code>
	 * in the current relation. If <code> possibleRelative </code> is found among subjects of
	 * current relation <code> true </code> is returned else <code> false </code>.
	 *
	 * @param possibleRelative subject to be searched in the current relation.
	 * @return <code>true </code> if current subject has such a relative in the current relation.
	 *         <code>false</code> otherwise.
	 */
	public boolean has(Subject possibleRelative) {

		return (find().contains(possibleRelative));
	}
}
