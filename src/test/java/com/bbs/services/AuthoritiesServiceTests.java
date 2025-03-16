package com.bbs.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.bbs.entites.Authority;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthoritiesServiceTests {

	@Autowired
	private AuthoritiesService service;
	
	@Test
	public void testSave() {
		Authority authority = new Authority("fred", "ROLE_USER");
		authority=service.save(authority);
	}
}
