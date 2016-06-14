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
import java.util.List;

public class Children extends Relation {

	public Children(Subject subject) {

		super(subject);

	}

	@Override
	public List<Subject> find() {

		ArrayList<Subject> children =  new ArrayList<Subject>();

		if(subject != null) {

			children.addAll(new Daughter(subject).find());
			children.addAll(new Son(subject).find());

		}

		return children;
	}
}
