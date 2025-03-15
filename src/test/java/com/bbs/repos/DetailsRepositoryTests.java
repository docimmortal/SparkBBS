package com.bbs.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbs.entites.BBSUserDetails;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class DetailsRepositoryTests {

	@Autowired
	private DetailsRepository repo;
	
	@Test
	public void testFindAll() {
		List<BBSUserDetails> details = repo.findAll();
		assertEquals(2, details.size());
	}
	
	@Test
	public void testFindByUsername() {
		Optional<BBSUserDetails> optional = repo.findOptionalByUsername("Bob");
		assertTrue(optional.isPresent());
	}
}
