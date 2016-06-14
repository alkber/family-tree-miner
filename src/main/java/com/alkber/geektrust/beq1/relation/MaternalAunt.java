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

public class MaternalAunt extends Relation {

	public MaternalAunt(final Subject subject) {

		super(subject);
	}

	@Override
	public List<Subject> find() {

		List<Subject> maternalAunt = new ArrayList<>();

		if (subject.getMother() != null) {

			List<Subject> sisters = (new Sister(subject.getMother()).find());
			maternalAunt.addAll(sisters);
			ArrayList<Subject> sisterInLaws = (new SisterInLaw(subject.getMother()).find());
			maternalAunt.addAll(sisterInLaws);
		}

		// remove father's sisters from the final list since sister in laws of mother will
		// include father's sisters as well. Since this is, maternal focus is on the mother's
		// side.

		List<Subject> fatherSisters = (new Sister(subject.getFather()).find());
		maternalAunt.removeAll(fatherSisters);

		return (maternalAunt);
	}
}
