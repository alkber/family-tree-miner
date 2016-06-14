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
import com.alkber.geektrust.beq1.relation.Brother;
import com.alkber.geektrust.beq1.relation.BrotherInLaw;
import com.alkber.geektrust.beq1.relation.Sister;
import com.alkber.geektrust.beq1.relation.SisterInLaw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The child of one's sibling (in other words, one's niece or nephew), especially in the plural
 * or as a gender-neutral term. Children of half brothers and sisters are also considered.
 *
 * This is a base class to Niece and Nephew.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class Nibling extends Child {

	public Nibling(Subject subject, boolean sex) {

		super(subject, sex);
	}

	@Override
	public List<Subject> find() {

		Set<Subject> niblings = new HashSet<Subject>();

		List<Subject> brothers = (new Brother(subject)).find();
		fillChildrenOfSubject(brothers, niblings);

		List<Subject> sisters = (new Sister(subject)).find();
		fillChildrenOfSubject(sisters, niblings);

		ArrayList<Subject> brotherInLaws = (new BrotherInLaw(subject)).find();
		fillChildrenOfSubject(brotherInLaws, niblings);

		ArrayList<Subject> sisterInLaws = (new SisterInLaw(subject)).find();
		fillChildrenOfSubject(sisterInLaws, niblings);

		return (new ArrayList<>(niblings));
	}

	protected void fillChildrenOfSubject(List<Subject> relatives, Set<Subject> children) {
		
		for(Subject current : relatives) {

			children.addAll(new Child(current, sex).find());
		}
	}
}
