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

import com.alkber.geektrust.beq1.relation.BrotherInLaw;
import com.alkber.geektrust.beq1.relation.GrandChildren;
import com.alkber.geektrust.beq1.relation.base.GrandChild;

/**
 * Grandchildren of male subject
 */
public class Test0000024 extends TestBase{

	public static void main(String args[]) {

		System.out.println((new GrandChildren(bob).find()));
	}
}
