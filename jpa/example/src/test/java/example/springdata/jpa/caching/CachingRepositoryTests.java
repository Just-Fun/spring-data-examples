/*
 * Copyright 2013-2016 the original author or authors.
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
package example.springdata.jpa.caching;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration test to show how to use {@link Cacheable} with a Spring Data repository.
 * 
 * @author Oliver Gierke
 * @author Thomas Darimont
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public abstract class CachingRepositoryTests {

	@Autowired CachingUserRepository repository;
	@Autowired CacheManager cacheManager;

	@Test
	public void cachesValuesReturnedForQueryMethod() {

		User dave = new User();
		dave.setUsername("dmatthews");

		dave = repository.save(dave);

		User result = repository.findByUsername("dmatthews");
		assertThat(result, is(dave));

		// Verify entity cached
		Cache cache = cacheManager.getCache("byUsername");
		ValueWrapper wrapper = cache.get("dmatthews");
		assertThat(wrapper.get(), is((Object) dave));
	}
}
