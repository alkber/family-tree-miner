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

package com.alkber.geektrust.beq1.relation.base;

import com.alkber.geektrust.beq1.model.Subject;
import com.alkber.geektrust.beq1.relation.base.Child;

import java.util.ArrayList;
import java.util.List;

/**
 * Siblings find both brothers and sisters of the current subject. Though class is names
 * sibling, it also included half brothers and sisters if they exist.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class Sibling extends Child {

	public Sibling(final Subject subject, final boolean sex) {

		super(subject, sex);
	}

	@Override
	public List<Subject> find() {

		ArrayList<Subject> childrenBySex = new ArrayList<Subject>();

		/* mother is known */
		if(subject != null && subject.getMother() != null) {

			/* full childrenBySex + maternal half childrenBySex */
			for(Subject current : subject.getMother().getChildren()) {

				if(current.getSex() == sex && subject != current) {

					childrenBySex.add(current);
				}
			}
		}

		/* father is known */
		if(subject != null && subject.getFather() != null) {

			/* full childrenBySex + paternal half childrenBySex */
			for(Subject current : subject.getFather().getChildren()) {

				if(current.getSex() == sex && subject != current) {

					if(!childrenBySex.contains(current)) {

						childrenBySex.add(current);
					}
				}
			}
		}

		return childrenBySex;
	}
}

