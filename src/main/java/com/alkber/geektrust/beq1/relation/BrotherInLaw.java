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

public class BrotherInLaw extends Relation {

	public BrotherInLaw(final Subject subject) {

		super(subject);
	}

	@Override
	public ArrayList<Subject> find() {

		ArrayList<Subject> brotherInLaws = new ArrayList<Subject>();

		if (subject == null) {

			// empty list
			return brotherInLaws;
		}

		/* CASE : spouse's brothers */
		for(Subject currentSpouse : subject.getSpouses()) {

			List<Subject> spouseBrothers = (new Brother(currentSpouse)).find();
			brotherInLaws.addAll(spouseBrothers);
		}

		/* CASE : sister's husbands */
		List<Subject> sisters = (new Sister(subject)).find();

		for(Subject currentSister : sisters) {

			/* considering all the husband of the current sister */
			for(Subject currentBrotherInLaw : currentSister.getSpouses()) {

				/* ignoring same sex spouse relationship */
				if(currentBrotherInLaw.getSex() == Subject.MALE) {

					brotherInLaws.add(currentBrotherInLaw);
				}
			}
		}

		return brotherInLaws;
	}
}
