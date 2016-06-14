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

package com.alkber.geektrust.beq1.view;

import com.alkber.geektrust.beq1.algorithm.SubjectCache;
import com.alkber.geektrust.beq1.model.Subject;

/**
 * Common base class for <code>PersonRelation</code> and <code>PersonRelative</code> views.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class PersonView  extends View {

	protected static Subject person;

	public PersonView(final String input) {

		super(input);
		person = SubjectCache.searchSubjectByName(getSecondInputParam());

		if (person == null) {

			throw new IllegalArgumentException(getSecondInputParam() + " Person Not Found");
		}
	}

	@Override
	public String output() {

		return null;
	}
}
