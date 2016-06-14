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

import java.util.ArrayList;

public class GrandMother extends GrandParent {

	public GrandMother(final Subject subject) {

		super(subject);
	}

	@Override
	protected void fillGrandParents(ArrayList<Subject> grandParents) {

		if(subject == null) {

			return;
		}
		grandParents.addAll(new Mother(subject.getFather()).find());
		grandParents.addAll(new Mother(subject.getMother()).find());

//		if(subject.getFather() != null && subject.getFather().getMother() != null) {
//
//			grandParents.add(subject.getFather().getMother());
//		}
//
//		if(subject.getMother() != null && subject.getMother().getMother() != null) {
//
//			grandParents.add(subject.getMother().getMother() );
//		}
	}
}
