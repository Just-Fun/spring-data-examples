/*
 * Copyright 2015 the original author or authors.
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
package example.springdata.rest.uris;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.support.EntityLookup;
import org.springframework.data.rest.core.support.EntityLookupSupport;

/**
 * Custom {@link EntityLookup} to replace the usage of the database identifier in item resource URIs with the username
 * property of the {@link User}. This one is not really used out of the box as it's not a Spring bean. It's just a
 * sample of how to deploy a customization in non-Java 8 environments which can't use the fluent API in use in
 * {@link SpringDataRestCustomization}.
 * 
 * @author Oliver Gierke
 * @see SpringDataRestCustomization#configureRepositoryRestConfiguration(org.springframework.data.rest.core.config.RepositoryRestConfiguration)
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired) )
public class UserEntityLookup extends EntityLookupSupport<User> {

	private final @NonNull UserRepository repository;

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.data.rest.core.support.EntityLookup#getId(java.lang.Object)
	 */
	@Override
	public Serializable getResourceIdentifier(User entity) {
		return entity.getUsername();
	}

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.data.rest.core.support.EntityLookup#lookupEntity(java.io.Serializable)
	 */
	@Override
	public Object lookupEntity(Serializable id) {
		return repository.findByUsername(id.toString());
	}
}
