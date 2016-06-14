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

import com.alkber.geektrust.beq1.algorithm.SubjectCache;
import com.alkber.geektrust.beq1.model.Subject;

import java.util.HashMap;
import java.util.Map;

/**
 * Called to create a valid relation between an existing subject (firstRelation) and a new subject
 * (secondRelation).
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class RelationRelationView extends View {

	/* existing subject like say mother  */
	private final Subject firstSubject;
	/* new subject like say daugther/son */
	private final Subject secondSubject;
	private final Subject finalSubject;

	private static final Map<String, Boolean> secondRelationWithGender = new HashMap<>();

	static {

		secondRelationWithGender.put("Daughter", false);
		secondRelationWithGender.put("Son", true);
	}

	private static final Map<String, Subject> firstAllowedRelations = new HashMap<>();

	static {

		firstAllowedRelations.put("Mother", null);
		firstAllowedRelations.put("Father", null);
	}

	public RelationRelationView(final String input) throws Exception {

		super(input);

		// since only one instance of this 'allowed relation' is available to the
		// objects created by this class, this should be cleared for next
		// object. Of course this is not thread safe, it is better to take it
		// out of static context, if there are any intentions to share objects created
		// from this class.
		//
		// However, for the current problem domain this approach is more efficient as only one
		// instance of object exist and it doesn't create these map for each object so created.
		firstAllowedRelations.replace("Mother", null);
		firstAllowedRelations.replace("Father", null);

		if(!firstAllowedRelations.containsKey(getFirstInputParam())) {

			throw new Exception(getFirstInputParam() + " is not available as first " +
					"relation ");
		}

		if(!secondRelationWithGender.containsKey(getThirdInputParam())) {

			throw new Exception(getThirdInputParam() + " is not available as second " +
					"relation ");
		}

		firstSubject = SubjectCache.searchSubjectByName(getSecondInputParam());

		if(firstSubject == null) {

			throw new Exception("No Such Person " + getSecondInputParam() + " exist");
		}

		firstAllowedRelations.replace(getFirstInputParam(), firstSubject);

		secondSubject = SubjectCache.searchSubjectByName(getFourthInputParam());

		if(secondSubject != null) {

			throw new Exception("A Person " + getFourthInputParam() + " Already Exist");
		}

		finalSubject = new Subject(getFourthInputParam(),
					firstAllowedRelations.get("Father"),
					firstAllowedRelations.get("Mother"),
					secondRelationWithGender.get(getThirdInputParam()));
	}

	@Override
	public String output() {

		return (new StringBuilder())
				.append(getSecondInputParam())
				.append(" is now related to ")
				.append(getFourthInputParam())
				.append(" as ")
				.append(getFirstInputParam())
				.toString();
	}
}
