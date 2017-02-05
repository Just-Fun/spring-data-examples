/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example;

import javaslang.collection.Map;
import javaslang.collection.Seq;
import javaslang.collection.Set;
import javaslang.control.Option;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

/**
 * Repository interface showing the usage of Javaslang collections and its {@link Option} type as repository query
 * method return types.
 * 
 * @author Oliver Gierke
 */
public interface PersonRepository extends Repository<Person, Long> {

	Person save(Person person);

	/**
	 * {@link Option} can be used as alternative to JDK 8's {@link Optional}.
	 * 
	 * @param id
	 * @return
	 */
	Option<Person> findById(Long id);

	/**
	 * {@link Seq} can be used as alternative to JDK's {@link List}. Javaslang's {@link Set} and {@link Map} are
	 * supported, too, and transparently mapped from their JDK counterparts.
	 * 
	 * @param firstname
	 * @return
	 */
	Seq<Person> findByFirstnameContaining(String firstname);
}
