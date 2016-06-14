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

public class PaternalUncle extends Relation {

	public PaternalUncle(final Subject subject) {

		super(subject);
	}

	@Override
	public List<Subject> find() {

		List<Subject> paternalUncle = new ArrayList<>();


		if (subject != null && subject.getFather() != null) {

			List<Subject> brothers = (new Brother(subject.getFather()).find());
			ArrayList<Subject> brotherInLaws = (new BrotherInLaw(subject.getFather()).find());
			paternalUncle.addAll(brothers);
			paternalUncle.addAll(brotherInLaws);
		}

		// remove mother's brothers from the final list since brother in laws of father will
		// include mother's brothers as well. Since this is, paternal, focus is on the father's
		// side.
		List<Subject> motherBrothers = (new Brother(subject.getMother()).find());
		paternalUncle.removeAll(motherBrothers);
		return new ArrayList<>(paternalUncle);
	}
}
