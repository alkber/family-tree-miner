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

/**
 * Display the help.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class HelpView extends View {

	private static String helpContent;

	public HelpView(final String input) {

		super(input);
		helpContent = (new StringBuilder())
				.append(" rlist")
				.append("\n")
				.append("  \\_list available relations ")
				.append("\n")
				.append(" Person = { member name } ; Relation = { a relation }")
				.append("\n")
				.append("  \\_find members in the Relation wrt Person")
				.append("\n")
				.append(" Person = { member name } ; Relative = { member name }")
				.append("\n")
				.append("  \\_find Relations of Person wrt Relative")
				.append("\n")
				.append(" Father/Mother = { member name } ; Daughter/Son = { new member name }")
				.append("\n")
				.append("  \\_create a son/daughter to an existing mother / father")
				.append("\n")
				.append(" #Daughter = { member name } ;")
				.append("\n")
				.append("  \\_find max daughter count and member related, wrt member")
				.append("\n")
				.append(" bye ")
				.append("\n")
				.append("  \\_exit query session ")
				.toString();
	}

	@Override
	public String output() {

		return helpContent;
	}
}
