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

import com.alkber.geektrust.beq1.model.Subject;
import com.alkber.geektrust.beq1.relation.Sister;

/**
 * Created by alcoder on 6/5/16.
 */
public class TestBase {

	final static Subject norman = new Subject("Norman", Subject.MALE);
	final static Subject lolly = new Subject("Lolly", Subject.FEMALE);

	/* bob's parents */
	final static Subject fedrick = new Subject("Fedrick", Subject.MALE);
	final static Subject sally = new Subject("Sally", norman, lolly, Subject.FEMALE);

	/* root parents */
	final static Subject bob   = new Subject("Bob", fedrick, sally, Subject.MALE);
	final static Subject alice = new Subject("Alice", Subject.FEMALE);

	/* children of root parents */
	final static Subject john   = new Subject("John", bob, alice, Subject.MALE);
	final static Subject mathew = new Subject("Mathew", bob, alice, Subject.MALE);
	final static Subject shiny  = new Subject("Shiny", bob, alice, Subject.FEMALE);

	/* smith's parents */
	final static Subject paul = new Subject("Paul", Subject.MALE);
	final static Subject jane = new Subject("Jane", Subject.FEMALE);

	/* alice's second husband smith*/
	final static Subject smith = new Subject("Smith", paul, jane, Subject.MALE);

	/* children of alice's with smith */
	final static Subject will   = new Subject("Will", smith, alice, Subject.MALE);
	final static Subject sherin = new Subject("Sherin", smith, alice, Subject.FEMALE);
	final static Subject joy    = new Subject("Joy", smith, alice, Subject.MALE);

	/* mathew marries magret, now has a child helen */
	final static Subject magret = new Subject("Magret", Subject.FEMALE);
	final static Subject helen = new Subject("Helen", mathew, magret, Subject.FEMALE);

	/* sherin marries linus, now has a child meena */
	final static Subject linus = new Subject("Linus", Subject.MALE);
	final static Subject meena = new Subject("Meena", linus, sherin, Subject.FEMALE);
	final static Subject a11 = new Subject("a11", null, sherin, Subject.FEMALE);
	final static Subject a22 = new Subject("a22", null, sherin, Subject.FEMALE);
	final static Subject a33 = new Subject("a33", null, sherin, Subject.FEMALE);

	final static Subject a111 = new Subject("a111", null, meena, Subject.FEMALE);
	final static Subject a222 = new Subject("a222", null, meena, Subject.FEMALE);
	final static Subject a333 = new Subject("a333", null, meena, Subject.FEMALE);
	final static Subject a444= new Subject("a444", null, meena, Subject.FEMALE);

	/* shiny marries james, now has a child cameron */
	final static Subject james = new Subject("James", Subject.MALE);
	final static Subject cameron = new Subject("Cameron", james, shiny, Subject.MALE);

	/* john marries aleena, now has a child philip */
	final static Subject aleena = new Subject("Aleena", Subject.FEMALE);
	final static Subject philip = new Subject("Philip", john, aleena, Subject.MALE);
	final static Subject victoria = new Subject("Victoria", john, aleena, Subject.FEMALE);
	final static Subject a1 = new Subject("a1", null, victoria, Subject.FEMALE);
	final static Subject a2 = new Subject("a2", null, victoria, Subject.FEMALE);
	final static Subject a3 = new Subject("a3", null, victoria, Subject.FEMALE);
	final static Subject a4 = new Subject("a4", null, victoria, Subject.FEMALE);

	/* parents of soniya */
	final static Subject fred = new Subject("Fred", Subject.MALE);
	final static Subject eleen = new Subject("Eleen", Subject.FEMALE);

	/* siblings of soniya */
	final static Subject alex = new Subject("Alex",fred, eleen, Subject.MALE);
	final static Subject fibna = new Subject("Fibna",fred, eleen, Subject.FEMALE);

	/* alex marries cathy */
	static {
		/* alex's wife */
		Subject cathy = new Subject("Cathy", Subject.FEMALE);
		alex.addSpouse(cathy);
	}

	/* fibna marries victor */
	static {
		/* fibna's husband */
		Subject victor = new Subject("Victor", Subject.MALE);
		fibna.addSpouse(victor);
	}

	/* will marries soniya, now has two children tony and monica */
	final static Subject soniya = new Subject("Soniya",fred, eleen, Subject.FEMALE);
	final static Subject tony = new Subject("Tony", will, soniya, Subject.MALE);
	final static Subject monica = new Subject("Monica", will, soniya, Subject.FEMALE);

}
