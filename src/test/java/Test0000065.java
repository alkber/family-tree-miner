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
import com.alkber.geektrust.beq1.algorithm.SubjectCache;
import com.alkber.geektrust.beq1.model.Subject;
import com.alkber.geektrust.beq1.relation.RelationAliasIndexer;

import java.io.IOException;

/**
 * Retrieve the set of defined relation ship aliases like Mother, Father etc
 */
public class Test0000065  {

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

	public static void main(String args[]) throws Exception  {

		loadLengaburuFamilyTree();
		RelationAliasIndexer.start();
		for (Subject wrtSubject : SubjectCache.getInstance().getReadOnlyCache()) {

			System.out.println("### Relations to " + wrtSubject.getName() );
			for( Subject currentSubject : SubjectCache.getInstance().getReadOnlyCache()) {

				System.out.print(" with " + currentSubject.getName() + " are " );
				System.out.println(new RelationMiner(wrtSubject, currentSubject).find());
			}
		}
	}
}
