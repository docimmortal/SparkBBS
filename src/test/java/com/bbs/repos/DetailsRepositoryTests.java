package com.bbs.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bbs.entites.UserDetails;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class DetailsRepositoryTests {

	@Autowired
	private DetailsRepository repo;
	
	@Test
	public void testFindAll() {
		List<UserDetails> details = repo.findAll();
		assertEquals(2, details.size());
	}
	
	@Test
	public void testFindByUsername() {
		Optional<UserDetails> optional = repo.findOptionalByUsername("Bob");
		assertTrue(optional.isPresent());
	}
}
