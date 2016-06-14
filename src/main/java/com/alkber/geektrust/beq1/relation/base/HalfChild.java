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
 * Base class to find male or female half brothers or sisters.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class HalfChild extends Child {

	public HalfChild(final Subject subject, final boolean sex) {

		super(subject, sex);
	}

	@Override
	public List<Subject> find() {

		ArrayList<Subject> brothers = new ArrayList<Subject>();

		if (subject == null) {

			// empty list
			return brothers;
		}

		// mother is known
		if(subject.getMother() != null) {

			/* full brothers + maternal half brothers */
			for(Subject current : subject.getMother().getChildren()) {

				if(current.getSex() == sex
						&& subject.getFather() != current.getFather()) {

					brothers.add(current);
				}
			}
		}

		// father is known
		if(subject.getFather() != null) {

			// full brothers + paternal half brothers
			for(Subject current : subject.getFather().getChildren()) {

				if(current.getSex() == sex
						&& subject.getMother() != current.getMother()) {

					if(!brothers.contains(current)) {

						brothers.add(current);
					}
				}
			}
		}

		return brothers;
	}
}
