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

public class Cousin extends Relation {

	public Cousin(final Subject subject) {

		super(subject);
	}

	@Override
	public ArrayList<Subject> find() {

		Set<Subject> cousins = new HashSet<Subject>();

		if (subject == null) {

			// empty list
			return new ArrayList<Subject>(cousins);
		}

		List<Subject> paternalUncles = (new PaternalUncle(subject)).find();
		fillChildrenOfSubjects(paternalUncles, cousins);

		List<Subject> paternalAunties = (new PaternalAunt(subject)).find();
		fillChildrenOfSubjects(paternalAunties, cousins);

		List<Subject> maternalUncles = (new MaternalUncle(subject)).find();
		fillChildrenOfSubjects(maternalUncles, cousins);

		List<Subject> maternalAunties = (new MaternalAunt(subject)).find();
		fillChildrenOfSubjects(maternalAunties, cousins);

		return (new ArrayList<Subject>(cousins));
	}

	protected void fillChildrenOfSubjects(List<Subject> subjects, Set<Subject>
			children) {

		for(Subject current : subjects) {

			children.addAll(current.getChildren());
		}
	}
}
