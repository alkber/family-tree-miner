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

package com.alkber.geektrust.beq1.relation;

import com.alkber.geektrust.beq1.model.Subject;
import com.alkber.geektrust.beq1.relation.base.Relation;

import java.util.ArrayList;
import java.util.List;

public class SisterInLaw extends Relation {

	public SisterInLaw(final Subject subject) {

		super(subject);
	}

	@Override
	public ArrayList<Subject> find() {

		ArrayList<Subject> sisterInLaws = new ArrayList<Subject>();

		if (subject == null) {
			// empty list
			return  sisterInLaws;
		}

		// CASE : spouses sisters
		for(Subject currentWife : subject.getSpouses()) {

			List<Subject> currentWifeSisters = (new Sister(currentWife)).find();
			sisterInLaws.addAll(currentWifeSisters);
		}

		// CASE : brothers's wife
		List<Subject> brothers = (new Brother(subject)).find();

		for(Subject currentBrother : brothers) {

			// considering all the wifes of the current brother
			for(Subject currentSisterInLaw : currentBrother.getSpouses()) {

				// ignoring same sex spouse relationship
				if(currentSisterInLaw.getSex() == Subject.FEMALE) {

					sisterInLaws.add(currentSisterInLaw);
				}
			}
		}

		return sisterInLaws;
	}
}
