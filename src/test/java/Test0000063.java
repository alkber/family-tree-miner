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

import com.alkber.geektrust.beq1.algorithm.RelationMiner;
import com.alkber.geektrust.beq1.relation.RelationAliasIndexer;
import com.alkber.geektrust.beq1.view.ViewFactory;

import java.io.IOException;

/**
 * Test cases for input parser
 */
public class Test0000063 extends TestBase {

	public static void main(String args[]) throws Exception {

		RelationAliasIndexer.start();
		ViewFactory.create("Person     =    Soniya  ;  Relation  =  Mother ");
		ViewFactory.create("Person=Soniya  ;  Relation=Father");
		ViewFactory.create("Person=Monica;Relative=Sally");
		ViewFactory.create("Mother=Soniya;Daughter=Jeff");
		//String input = "Person     =    Soniya  ;  Relation  =  Mother ";
	}
}
