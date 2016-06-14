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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaternalUncle extends Relation {

	public MaternalUncle(final Subject subject) {

		super(subject);
	}

	@Override
	public List<Subject> find() {

		List<Subject> maternalUncle = new ArrayList<>();

		if(subject != null && subject.getMother() != null) {

			List<Subject> brothers = (new Brother(subject.getMother()).find());
			ArrayList<Subject> brotherInLaws = (new BrotherInLaw(subject.getMother()).find());
			maternalUncle.addAll(brothers);
			maternalUncle.addAll(brotherInLaws);
		}

		// remove father's brothers from the final list since brother in laws of mother will
		// include father's brothers as well. Since this is, maternal focus is on the mother's
		// side.

		List<Subject> fatherBrothers = (new Brother(subject.getFather()).find());
		maternalUncle.removeAll(fatherBrothers);

		return (maternalUncle);
	}
}
