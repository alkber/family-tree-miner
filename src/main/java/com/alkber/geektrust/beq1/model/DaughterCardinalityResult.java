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

package com.alkber.geektrust.beq1.model;

import java.util.List;

/**
 * An immutable class the wraps the result of DaughterCardinality operation.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class DaughterCardinalityResult {

	/* daughter count , typically maximum */
	private final int count;
	/* list of subjects having same count of daughters */
	private final List<Subject> subjects;

	public DaughterCardinalityResult(int count, List<Subject> subjects) {

		this.count = count;
		this.subjects = subjects;
	}

	@Override
	public String toString() {

		return (new StringBuilder())
				.append((subjects.size() > 1 && count > 0) ? " Draw Result " : "")
				.append((count > 0 && subjects.size() >= 1)? subjects.toString() : "")
				.append(" ")
				.append(count > 0 ? count+"" : " No")
				.append(" daughters ")
				.append(count == 0 ? "found in the family tree" : "")
				.append( subjects.size() > 1 && count > 0 ? " each." : ".")
				.toString();
	}
}
