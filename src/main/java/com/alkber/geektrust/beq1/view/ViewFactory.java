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

import com.alkber.geektrust.beq1.relation.RelationAliasIndexer;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a factory pattern, to create polymorphic views based on the input string pattern. Reason
 * why it is polymorphic is that, the class behaves exactly the same manner, however, the
 * implementation differs. We achieve runtime polymorphism.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class ViewFactory {


	/* grammar to parse valid input query for person to relation */
	private final static String PERSON_RELATION_GRAMMER = grammarGenerator("Person", "Relation");
	/* grammar to parse valid input query for person to relative */
	private final static String PERSON_RELATIVE_GRAMMER = grammarGenerator("Person", "Relative");
	/* list of relation to relation grammar, keeping an expanded list for future use  */
	private final static List<String> RELATION_RELATION_GRAMMERS = new ArrayList<String>();
	/* grammar to parse a valid input query to find daughter cardinality */
	private final static String DAUGHTER_CARDINALITY_GRAMMER = "\\s*#Daughter\\s*=\\s*([a-z|A-Z])"
			+ "+\\s*;";

	/* Frequently created objects */
	private static View NOT_RECOGNIZED = new NotRecognizedView(null);
	private static View HELP           = new HelpView(null);
	private static View RLIST          = new RlistView(null);
	private static View EXIT           = new ExitView(null);

	/* populate a grammar for all possible relation combination, though only Mother
	 * daughter relation was asked in the problem, this is how we can scale it to
	 * meet other relation combinations.
	 * */
	static {

		for(String currentRelation : RelationAliasIndexer.getReadOnlyAliasList()) {

			for(String currentSubRelation : RelationAliasIndexer.getReadOnlyAliasList()) {

				RELATION_RELATION_GRAMMERS.add(grammarGenerator(currentRelation,
						currentSubRelation));
			}
		}
	}

	/**
	 * Create a view based on the input string pattern.
	 *
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static View create(String input) throws Exception {

		View v = NOT_RECOGNIZED;

		if(input.toLowerCase().equals("help")) {

			v = HELP;

		} else if(input.toLowerCase().equals("rlist")) {

			v = RLIST;

		} else if(input.toLowerCase().equals("bye")) {

			v = EXIT;

		} else if(input.matches(DAUGHTER_CARDINALITY_GRAMMER)) {

			v  = new DaughterCardinalityView(input);

		} else if(input.matches(PERSON_RELATION_GRAMMER)) {

			v = new PersonRelationView(input);

		} else if(input.matches(PERSON_RELATIVE_GRAMMER)) {

			v = new PersonRelativeView(input);

		} else {

			for(String currentGrammer : RELATION_RELATION_GRAMMERS) {

				if(input.matches(currentGrammer)) {

					v = new RelationRelationView(input);
				}
			}
		}

		return v;
	}

	/**
	 * A common grammar generator reused for various cases, which only differs in the
	 * <code>key01</code> and <code> key02</code>.
	 *
	 * @param key01
	 * @param key02
	 * @return a grammar string with <code>key01</code> and <code>key02</code> concatenated.
	 */
	private static String grammarGenerator(String key01, String key02) {

		if(key01 == null || key02 == null) {

			throw new IllegalArgumentException("input keys can't be null");
		}

		if(key01.length() == 0 || key02.length() == 0) {

			throw new IllegalArgumentException("input keys can't be of length zero");
		}

		StringBuilder sb = new StringBuilder();

		sb.append("\\s*")
				.append(key01)
				.append("\\s*=\\s*([a-z|A-Z])+\\s*;\\s*")
				.append(key02)
				.append("\\s*=\\s*([a-z|A-Z])+\\s*");

		return sb.toString();
	}
}
