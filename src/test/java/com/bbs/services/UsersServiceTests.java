package com.bbs.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import com.bbs.entites.User;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UsersServiceTests {

	@Autowired
	private UsersService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void testSave() {
		User user = new User("fred", passwordEncoder.encode("Pass123"), true);
		System.out.println(passwordEncoder.encode("Joe Cool"));
		user=service.save(user);
	}
	
}
