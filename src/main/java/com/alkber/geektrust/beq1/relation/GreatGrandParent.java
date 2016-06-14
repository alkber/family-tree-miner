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
import com.alkber.geektrust.beq1.relation.GrandFather;
import com.alkber.geektrust.beq1.relation.GrandMother;
import com.alkber.geektrust.beq1.relation.base.Relation;

import java.util.ArrayList;
import java.util.List;

public abstract class GreatGrandParent extends Relation {

	protected final List<Subject> grandParentsOfSubject;

	public GreatGrandParent(final Subject subject) {

		super(subject);
		grandParentsOfSubject = new ArrayList<Subject>();
	}

	@Override
	public List<Subject> find() {

		ArrayList<Subject> greatGrandPatrents = new ArrayList<Subject>();

		if (subject == null) {
			// empty list
			return greatGrandPatrents;
		}

		grandParentsOfSubject.addAll(new GrandFather(subject).find());
		grandParentsOfSubject.addAll(new GrandMother(subject).find());
		fillGreatGrandParents(greatGrandPatrents);
		return greatGrandPatrents;
	}

	abstract protected  void fillGreatGrandParents(ArrayList<Subject> greatGrandParents);
}
