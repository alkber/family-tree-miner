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

import com.alkber.geektrust.beq1.algorithm.SubjectCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Fundamental model for the relationship tree. This class is an immutable type. So, once
 * instantiated with valid values it can't be altered. This behaviour is intentional, since subject
 * need not be modified, once family tree is constructed.
 * However, spouses and children can be added a runtime.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class Subject {

	/* fundamental attribute gender     */
	public static final boolean MALE   = true;
	public static final boolean FEMALE = false;
	/* fundamental attribute mother     */
	private final Subject            mother;
	/* fundamental attribute father     */
	private final Subject            father;
	/* fundamental attribute sex        */
	private final boolean            sex;
	/* fundamental attribute children   */
	private final ArrayList<Subject> children;
	/* fundamental attribute spouses    */
	private final ArrayList<Subject> spouses;

	/* meta information on the subject like name, ssn, dob , future extension */
	private final SubjectMeta meta;

	/* subject cahcer listen to object creation events from this class */
	public static final NewInstanceListener listener = SubjectCache.getInstance();

	public interface NewInstanceListener {

		void onNewSubjectCreate(Subject newSubject);
	}

	public Subject(String name, Subject father, Subject mother, boolean sex) {

		this(name, father, mother, sex, new ArrayList<Subject>());

	}

	public Subject(String name, boolean sex)  {

		this(name, null, null, sex, new ArrayList<Subject>());

	}

	public Subject(String name, Subject father, Subject mother, boolean sex, ArrayList<Subject>
			children) {

		if((mother != null && father != null) && mother == father) {

			throw new IllegalArgumentException(" both mother and father can't be the same");
		}

		if((father != null) && father.getSex() != Subject.MALE) {

			throw new IllegalArgumentException(" father should be a male gender");
		}

		if((mother != null) && mother.getSex() != Subject.FEMALE) {

			throw new IllegalArgumentException(" mother should be a female gender");
		}

		this.meta = new SubjectMeta();
		meta.setName(name);

		this.father = father;
		this.mother = mother;
		this.sex = sex;
		this.children = children;
		this.spouses = new ArrayList<>();

		if(father != null) {
			/* indirectly add child to father's set of children */
			father.addChild(this);
			/* indirectly add current mother to the set of wife */
			father.addSpouse(mother);
		}

		if(mother != null) {
			/* indirectly add child to father's set of children    */
			mother.addChild(this);
			/* indirectly add current father to the set of husband */
			mother.addSpouse(father);
		}

		/* inform subject cacher regarding the new subject */
		listener.onNewSubjectCreate(this);
	}

	public String toString() {

		return (new StringBuilder()).append((sex == MALE ? "{m} " : "{f} ")).append(this.getName()
		).toString();
	}

	public String verbose() {

		return new StringBuilder().append("\n|[M]: ").append(mother == null ? "unknown" : mother
				.getName()).append("\n").append("|[F]: ").append(father == null ? "unknown" :
				father.getName()).append("\n \\_").append((sex == MALE ? " {m} " : " {f} "))
				.append(this.getName()).append("\n\t\\_[C] : ").append(children.size() > 0 ?
						children.toString() : "no children").append("\n").toString();
	}

	/**
	 * Check to see of the subject has children.
	 *
	 * @return <code> true </code> if subject has children else <cdoe> false </cdoe>
	 */
	public boolean hasChildren() {

		return children.size() > 0;
	}

	/**
	 * Get list of spouses for the subject
	 *
	 * @return list of spouses
	 */
	public List<Subject> getSpouses() {

		return  Collections.unmodifiableList(spouses);
	}

	/**
	 * Get list of children for the subject
	 *
	 * @return list of children
	 */
	public List<Subject> getChildren() {


		return Collections.unmodifiableList(children);
	}

	/**
	 * Add a spouse to existing list of spouses
	 *
	 * @param spouse
	 */
	public void addSpouse(Subject spouse) throws IllegalArgumentException {

		if(spouse != null && !spouses.contains(spouse)) {

			spouses.add(spouse);
		}
	}

	/**
	 * Add a child to existing list of children
	 *
	 * @param newChild
	 */
	public void addChild(Subject newChild) {

		if(newChild != null && !children.contains(newChild)) {

			children.add(newChild);
		}
	}

	/**
	 * Get gender of the subject
	 *
	 * @return <code> true </code> if male <code> false </code> if female
	 */
	public boolean getSex() {

		return sex;
	}

	/**
	 * Get name of the subject
	 *
	 * @return name of the subject
	 */
	public String getName() {

		return meta.getName();
	}

	/**
	 * Get mother of the subject
	 *
	 * @return subject instance of mother
	 */
	public Subject getMother() {

		return mother;
	}

	/**
	 * Get father of the subject
	 *
	 * @return subject instance of father
	 */
	public Subject getFather() {

		return father;
	}

}
