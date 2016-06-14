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

package com.alkber.geektrust.beq1.view;

import com.alkber.geektrust.beq1.model.Subject;
import com.alkber.geektrust.beq1.relation.RelationAliasIndexer;
import com.alkber.geektrust.beq1.relation.RelationFactory;
import com.alkber.geektrust.beq1.relation.base.Relation;

/**
 * Base class for all the views.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public abstract class View {

	protected String input;

	public View(String input) {

		this.input = input;
	}

	protected Relation getRelation(String input, Subject person) throws Exception {

		String relationAlias = getFourthInputParam();

		if (!RelationAliasIndexer.relationExist(relationAlias)) {

			throw new Exception("No Such Relation " + relationAlias + " Exist");
		}

		return RelationFactory.create(relationAlias, person);
	}

	public String getFirstInputParam() {

		return input.substring(0, input.indexOf("=")).trim();
	}

	public String getThirdInputParam() {

		return input.substring(input.indexOf(";") + 1 , input.lastIndexOf("=")).trim();
	}

	public String getSecondInputParam() {

		return input.substring(input.indexOf("=") + 1, input.indexOf(";")).trim();
	}

	public String getFourthInputParam() {

		return input.substring(input.lastIndexOf("=") + 1, input.length()).trim();
	}

	/**
	 * Expecting the derived class to implement the output.
	 *
	 * @return
	 */
	abstract public String output();
}
