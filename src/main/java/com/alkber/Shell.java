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

package com.alkber;

import com.alkber.geektrust.beq1.model.Subject;
import com.alkber.geektrust.beq1.relation.RelationAliasIndexer;
import com.alkber.geektrust.beq1.view.ExitView;
import com.alkber.geektrust.beq1.view.NotRecognizedView;
import com.alkber.geektrust.beq1.view.View;
import com.alkber.geektrust.beq1.view.ViewFactory;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main Entry point for the relation miner
 *
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class Shell {

	private static void loadLengaburuFamilyTree() throws Exception {

		// root parents
		final Subject shan = new Subject("Shan", Subject.MALE);
		final Subject anga = new Subject("Anga", Subject.FEMALE);

		// male children of root parents
		final Subject ish  = new Subject("Ish", shan, anga, Subject.MALE);
		final Subject chit = new Subject("Chit", shan, anga, Subject.MALE);
		final Subject vich = new Subject("Vich", shan, anga, Subject.MALE);
		// female child of root parents
		final Subject satya = new Subject("Satya", shan, anga, Subject.FEMALE);

		final Subject ambi = new Subject("Ambi", Subject.FEMALE);
		// children of chit and amit
		final Subject drita = new Subject("Drita", chit, ambi, Subject.MALE);
		final Subject jaya  = new Subject("Jaya", Subject.FEMALE);

		// bachelor jata
		final Subject jata = new Subject("Jata", drita, jaya, Subject.MALE);

		final Subject driya = new Subject("Driya", drita, jaya, Subject.FEMALE);
		final Subject mnu   = new Subject("Mnu", Subject.MALE);
		driya.addSpouse(mnu);

		// bachelor vrita
		final Subject vrita = new Subject("Vrita", chit, ambi, Subject.MALE);

		final Subject lika = new Subject("Lika", Subject.FEMALE);
		// children of vich and lika
		final Subject vila = new Subject("Vila", vich, lika, Subject.MALE);
		final Subject jnki = new Subject("Jnki", Subject.FEMALE);

		final Subject lavnya = new Subject("Lavnya",vila, jnki, Subject.FEMALE);
		final Subject gru = new Subject("Gru", Subject.MALE);
		lavnya.addSpouse(gru);

		final Subject chika = new Subject("Chika", vich, lika, Subject.FEMALE);
		final Subject kplia = new Subject("Kplia", Subject.MALE);
		chika.addSpouse(kplia);

		final Subject vyan = new Subject("Vyan", Subject.MALE);
		vyan.addSpouse(satya);
		// children of satya and vyan
		final Subject satvy = new Subject("Satvy", vyan, satya, Subject.FEMALE);
		final Subject asva  = new Subject("Asva", Subject.MALE);
		satvy.addSpouse(asva);

		final Subject savya = new Subject("Savya", vyan, satya, Subject.MALE);
		final Subject krpi  = new Subject("Krpi", Subject.FEMALE);

		// bachelor kriya
		final Subject kriya  = new Subject("Kriya",savya, krpi, Subject.MALE);

		final Subject saayan = new Subject("Saayan", vyan, satya, Subject.MALE);
		final Subject mina   = new Subject("Mina", Subject.FEMALE);
		// bachelor kriya
		final Subject misa   = new Subject("Misa",saayan, mina, Subject.MALE);
	}

	public static void main(String args[]) throws IOException, Exception {

		//Evaluators are request to read : FIRST_READ_ME.txt

		// THERE ARE RUDIMENTARY TEST CASES IN THE TEST FOLDER.
		// THEY ARE HOWEVER MANUAL TEST CASES SINCE THE RULE STATES
		// USE OF 3rd PARTY LIBRARIES LIKE junit WEREN'T ALLOWED.
		// AN INTERESTING TEST CASE FOR YOU GUYS MIGHT BE Test0000065

		// must be done first to index set of relations available
		RelationAliasIndexer.start();

		System.out.println("Copyright (C) 2016 Althaf K Backer");
		System.out.println("Apache License, Version 2.0");
		System.out.println("Welcome to Lengaburu Family Tree Miner.");
		System.out.println("|__");
		System.out.println("   \\_loading existing family tree ...");
		loadLengaburuFamilyTree();
		System.out.println("(!) type help");

		// only one static family tree must be loaded at a time else results are undefined
		//loadTestFamilyTree();

		String input = "";
		Scanner inputScanner = (new Scanner(System.in));
		while(true) {

			System.out.print("shell > ");
			input = inputScanner.nextLine();

			View action = null;
			try {

				action = ViewFactory.create(input);

				if(action instanceof NotRecognizedView) {

					System.out.println(action.output());
					continue;
				}

				if(action instanceof ExitView) {

					System.out.println(action.output());
					break;
				}

				System.out.println(action.output());

			} catch(Exception e) {

				System.out.println("(!) " + ((action != null) ? action.output() : e.getMessage()));
			}
		}

		inputScanner.close();
	}

//	private static void loadTestFamilyTree() throws Exception {
//
//		final Subject norman = new Subject("Norman", Subject.MALE);
//		final Subject lolly  = new Subject("Lolly", Subject.FEMALE);
//
//	/* bob's parents */
//		final Subject fedrick = new Subject("Fedrick", Subject.MALE);
//		final Subject sally   = new Subject("Sally", norman, lolly, Subject.FEMALE);
//
//	/* root parents */
//		final Subject bob   = new Subject("Bob", fedrick, sally, Subject.MALE);
//		final Subject alice = new Subject("Alice", Subject.FEMALE);
//
//	/* children of root parents */
//		final Subject john   = new Subject("John", bob, alice, Subject.MALE);
//		final Subject mathew = new Subject("Mathew", bob, alice, Subject.MALE);
//		final Subject shiny  = new Subject("Shiny", bob, alice, Subject.FEMALE);
//
//	/* smith's parents */
//		final Subject paul = new Subject("Paul", Subject.MALE);
//		final Subject jane = new Subject("Jane", Subject.FEMALE);
//
//	/* alice's second husband smith*/
//		final Subject smith = new Subject("Smith", paul, jane, Subject.MALE);
//
//	/* children of alice's with smith */
//		final Subject will   = new Subject("Will", smith, alice, Subject.MALE);
//		final Subject sherin = new Subject("Sherin", smith, alice, Subject.FEMALE);
//		final Subject joy    = new Subject("Joy", smith, alice, Subject.MALE);
//
//	/* mathew marries magret, now has a child helen */
//		final Subject magret = new Subject("Magret", Subject.FEMALE);
//		final Subject helen  = new Subject("Helen", mathew, magret, Subject.FEMALE);
//		final Subject b1     = new Subject("a1", null, helen, Subject.FEMALE);
//		final Subject b2     = new Subject("a2", null, helen, Subject.FEMALE);
//		final Subject b3     = new Subject("a3", null, helen, Subject.FEMALE);
//		final Subject b4     = new Subject("a4", null, helen, Subject.FEMALE);
//
//	/* sherin marries linus, now has a child meena */
//		final Subject linus = new Subject("Linus", Subject.MALE);
//		final Subject meena = new Subject("Meena", linus, sherin, Subject.FEMALE);
//		final Subject a11   = new Subject("a11", null, sherin, Subject.FEMALE);
//		final Subject a22   = new Subject("a22", null, sherin, Subject.FEMALE);
//		final Subject a33   = new Subject("a33", null, sherin, Subject.FEMALE);
//
//		final Subject a111 = new Subject("a111", null, meena, Subject.FEMALE);
//		final Subject a222 = new Subject("a222", null, meena, Subject.FEMALE);
//		final Subject a333 = new Subject("a333", null, meena, Subject.FEMALE);
//		final Subject a444 = new Subject("a444", null, meena, Subject.FEMALE);
//
//		final Subject a1111 = new Subject("a1111", null, meena, Subject.FEMALE);
//		final Subject a2222 = new Subject("a2222", null, meena, Subject.FEMALE);
//		final Subject a3333 = new Subject("a3333", null, meena, Subject.FEMALE);
//		final Subject a4444 = new Subject("a4444", null, meena, Subject.FEMALE);
//
//	/* shiny marries james, now has a child cameron */
//		final Subject james   = new Subject("James", Subject.MALE);
//		final Subject cameron = new Subject("Cameron", james, shiny, Subject.MALE);
//
//	/* john marries aleena, now has a child philip */
//		final Subject aleena   = new Subject("Aleena", Subject.FEMALE);
//		final Subject philip   = new Subject("Philip", john, aleena, Subject.MALE);
//		final Subject victoria = new Subject("Victoria", john, aleena, Subject.FEMALE);
//		final Subject a1       = new Subject("a1", null, victoria, Subject.FEMALE);
//		final Subject a2       = new Subject("a2", null, victoria, Subject.FEMALE);
//		final Subject a3       = new Subject("a3", null, victoria, Subject.FEMALE);
//		final Subject a4       = new Subject("a4", null, victoria, Subject.FEMALE);
//
//	/* parents of soniya */
//		final Subject fred  = new Subject("Fred", Subject.MALE);
//		final Subject eleen = new Subject("Eleen", Subject.FEMALE);
//
//	/* siblings of soniya */
//		final Subject alex  = new Subject("Alex", fred, eleen, Subject.MALE);
//		final Subject fibna = new Subject("Fibna", fred, eleen, Subject.FEMALE);
//
//	/* alex marries cathy */
//		{
//		/* alex's wife */
//			Subject cathy = new Subject("Cathy", Subject.FEMALE);
//			alex.addSpouse(cathy);
//		}
//
//	/* fibna marries victor */
//		{
//		/* fibna's husband */
//			Subject victor = new Subject("Victor", Subject.MALE);
//			fibna.addSpouse(victor);
//		}
//
//	/* will marries soniya, now has two children tony and monica */
//		final Subject soniya = new Subject("Soniya", fred, eleen, Subject.FEMALE);
//		final Subject tony   = new Subject("Tony", will, soniya, Subject.MALE);
//		final Subject monica = new Subject("Monica", will, soniya, Subject.FEMALE);
//
//		RelationAliasIndexer.start();
//	}

}





