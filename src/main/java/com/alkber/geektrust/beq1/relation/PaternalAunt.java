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

public class PaternalAunt extends Relation {

	public PaternalAunt(final Subject subject) {

		super(subject);
	}

	@Override
	public List<Subject> find() {

		List<Subject> paternalAunt = new ArrayList<>();

		if (subject != null && subject.getFather() != null) {

			List<Subject> sisters = (new Sister(subject.getFather()).find());
			ArrayList<Subject> sisterInLaws = (new SisterInLaw(subject.getFather()).find());
			paternalAunt.addAll(sisters);
			paternalAunt.addAll(sisterInLaws);
		}

		// remove mother's sisters from the final list since sister in laws of father. will
		// include father's sisters as well. Since this is, paternal focus is on the father's
		// side.
		List<Subject> motherSisters = (new Sister(subject.getMother()).find());
		paternalAunt.removeAll(motherSisters);

		return (paternalAunt);
	}
}
