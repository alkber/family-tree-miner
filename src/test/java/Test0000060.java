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

import com.alkber.geektrust.beq1.relation.RelationAliasIndexer;

import java.io.IOException;

/**
 * Retrieve the set of defined relation ship aliases like Mother, Father etc
 */
public class Test0000060 extends TestBase {

	public static void main(String args[]) throws IOException {

		RelationAliasIndexer.start();
		System.out.println(RelationAliasIndexer.getReadOnlyAliasList());
	}
}
