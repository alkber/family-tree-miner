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

package com.alkber.geektrust.beq1.relation;

import com.alkber.geektrust.beq1.model.Subject;
import com.alkber.geektrust.beq1.relation.base.Relation;

import java.lang.reflect.Constructor;

/**
 * This is the core class that instantiate relation classes like Mother, Father etc from the
 * relation alias passed. This is done using reflection, so for correct functioning of this
 * class this class must belong to the package <code>com.alkber.geektrust.beq1.relation</code>.
 *
 * Factory approach to create relation instances has flexible advantage of adding and removing
 * relations. It would be just a matter of creating an new class derived from <code>Relation</code>
 * then adding it to this package. The system is intelligent enough to detect these new classes
 * and load them when finding relations.
 *
 * This class work in rappot with <code>RelationAliasIndexer</code>, so when a family tree is
 * constructed, <code>RelationAliasIndexer.start()</code> must be called.
 *
 * @author Althaf K Backer <althafkbacker@gmail.com>
 */
public class RelationFactory {

	private static String relationPackage = "com.alkber.geektrust.beq1.relation";

	public  static Relation create(String alias, Subject subject) {

		try {

			if(RelationAliasIndexer.relationExist(alias)) {

				ClassLoader classLoader = RelationFactory.class.getClassLoader();
				try {

					Class aClass = classLoader.loadClass(relationPackage + "." + alias);
					/* get constructor that takes Subject as parameter */
					Constructor constructor = aClass.getConstructor(Subject.class);

					return (Relation) constructor.newInstance(subject);
				} catch(ClassNotFoundException e) {

				}
			}

		} catch(Exception e) {

		}
		return  null;
	}
}
