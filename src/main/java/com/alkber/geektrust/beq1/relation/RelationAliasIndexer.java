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


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * An indexing class that, would index all defined relation once during the application run time.
 * This is used by <code>RelationFactory</code> class while creating instances of relation.
 *
 * @auther Althaf K Backer <althafkbacker@gmail.com>
 */
public class RelationAliasIndexer {

	private final static RelationAliasIndexer singleton = new RelationAliasIndexer();

	/* relative path where the class files could be found */
	private final static String relationRepositoryPath = "com/alkber/geektrust/beq1/relation";

	/* used when running from jar     */
	private final static String      jarFileRelationRepositoryPath = relationRepositoryPath + "/";
	/* used when not running from jar */
	private final static String      localFileRepositoryPath       = "/" + relationRepositoryPath;
	/* hold the class names           */
	private final static Set<String> relationAliasIndex            = new HashSet<String>();

	/* black listed classes, and inner classes, in current package */
	private final static String[] blacklist = {
												"base",
												"$",
												"RelationAliasIndexer",
												"RelationFactory"};

	private final static String classExtension = ".class";

	/**
	 * Check to see if a specified relation exist as a defined relation.
	 *
	 * @param relation
	 * @return
	 * @throws Exception
	 */

	public static boolean relationExist(String relation) throws Exception {

		if(relationAliasIndex.size() == 0) {

			throw new Exception("either start() wasn't called or no relations have been " +
					"defined");
		}

		return relationAliasIndex.contains(relation);
	}

	/**
	 * Start the indexing of the relation aliases from the package path. This must be
	 * called at the application runtime.
	 *
	 * @throws IOException
	 */
	public static void start() throws IOException {

		/* if relation aliases are already cached, ignore */
		if(relationAliasIndex.size() > 0) {

			return;
		}

		final String filePath = singleton.getClass().getResource(localFileRepositoryPath)
				.getFile();

		// if file path contains jar, it means we are executing from a jar file
		if(filePath.contains(".jar")) {

			indexFromJarFile(
					new BufferedInputStream(
							new FileInputStream(filePath.substring
					(filePath.indexOf(":") + 1, filePath.indexOf("!")))));
		} else {

			new java.io.File(filePath).listFiles(new java.io.FileFilter() {

				public boolean accept(java.io.File file) {

					if(file.isFile() && file.getName().endsWith(classExtension)) {

						addToIndex(file.getName());
					}
					return false;
				}
			});
		}
	}

	/**
	 * Returns the set of relation aliases. Before calling this routine a call to
	 * <code> start() </code> must be made.
	 *
	 * @return a set of relation aliases
	 */
	public static Set<String> getReadOnlyAliasList() {

		return Collections.unmodifiableSet(relationAliasIndex);
	}

	private static void addToIndex(String classNameWithExtension) {

		for(String currentIngore : blacklist) {

			if(classNameWithExtension.contains(currentIngore)) {

				return;
			}
		}

		relationAliasIndex.add(extractClassName(classNameWithExtension));
	}

	/**
	 * Separate .class from the actual className
	 *
	 * @param classNameWithExtension
	 * @return
	 */
	private static String extractClassName(String classNameWithExtension) {

		return classNameWithExtension.replace(classExtension, "");
	}

	/**
	 * Read the defined relation classes in the package path from a jar file and
	 * add it to the index.
	 *
	 * @param in
	 * @throws IOException
	 */
	private static void indexFromJarFile(InputStream in) throws IOException {

		ZipInputStream zis = new ZipInputStream(in);
		ZipEntry       e;

		while((e = zis.getNextEntry()) != null) {

			String name = e.getName();
			zis.closeEntry();

			if(name.contains(relationRepositoryPath) && name.endsWith(classExtension)) {

				addToIndex(name.replace(jarFileRelationRepositoryPath, ""));
			}
		}
		in.close();
	}
}

