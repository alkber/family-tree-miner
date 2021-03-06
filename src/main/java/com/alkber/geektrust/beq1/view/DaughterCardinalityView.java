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

import com.alkber.geektrust.beq1.algorithm.DaughterCardinality;
import com.alkber.geektrust.beq1.algorithm.SubjectCache;
import com.alkber.geektrust.beq1.model.Subject;

/**
 * Called to find and show daughter cardinality.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class DaughterCardinalityView extends View {

	private final Subject rootSubject;

	public DaughterCardinalityView(final String input) {

		super(input);

		rootSubject = SubjectCache.searchSubjectByName(getSecondInputParam());

		if(rootSubject == null) {

			throw new IllegalArgumentException("No Such Person " + getSecondInputParam()  +
					" Found");
		}
	}

	@Override
	public String output() {

		return (new DaughterCardinality(rootSubject)).find().toString();
	}
}
