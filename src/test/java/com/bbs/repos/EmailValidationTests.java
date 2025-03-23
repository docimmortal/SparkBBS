package com.bbs.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbs.entites.EmailValidation;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class EmailValidationTests {

	@Autowired
	private EmailValidationRepository repo;
	
	@Test
	public void getUnvalidatedEmails() {
		repo.save(createEmailValidation("joe@cool.com","abc123",true));
		repo.save(createEmailValidation("bob@smith.com","abc124",false));
		repo.save(createEmailValidation("hacker@hack.com","hak123",false));

		List<EmailValidation> results = repo.getUnvalidatedEmails();
		assertEquals(2, results.size());
	}
	
	@Test
	public void testKeyMatches() {
		repo.save(createEmailValidation("bob@smith.com","abc124",false));

		Boolean results = repo.checkKey("bob@smith.com","abc124");
		assertTrue(results);
	}
	
	@Test
	public void testKeyMatchesIgnoringCase() {
		repo.save(createEmailValidation("rob@smith.com","abc333",false));

		Boolean results = repo.checkKey("ROB@SMITH.COM","abc333");
		assertTrue(results);
	}
	
	@Test
	public void testKeyDoesNotMatch() {
		repo.save(createEmailValidation("bob@smith.com","abc123",false));

		Boolean results = repo.checkKey("bob@smith.com","abc111");
		assertFalse(results);
	}
	
	@Test
	public void testEmailDoesNotMatch() {
		repo.save(createEmailValidation("bob@smith.com","abc124",false));

		Boolean results = repo.checkKey("bobby@smith.com","abc124");
		assertFalse(results);
	}
	
	@Test
	public void testScenerioGenerateNewKey() {
		repo.save(createEmailValidation("bobby@smith.com","abc124",false));
		Integer results = repo.countByEmailIgnoreCase("BOBBY@SMITH.COM");
		if (results==1) {
			Boolean validated = repo.checkValidated("BOBBY@SMITH.COM");
			if (!validated) {
				repo.deleteByEmailIgnoreCase("BOBBY@SMITH.COM");
				results = repo.countByEmailIgnoreCase("BOBBY@SMITH.COM");
				assertEquals(0,results);
				repo.save(createEmailValidation("bobby@smith.com","abc999",false));
				Boolean exists = repo.existsByEmailIgnoreCaseAndCodeKeyIgnoreCase("bobby@smith.com","abc999");
				assertTrue(exists);
			} else {
				assertTrue(false);
			}
		}
	}
	
	private EmailValidation createEmailValidation(String email, String key, Boolean validated) {
		EmailValidation ev = new EmailValidation();
		ev.setEmail(email);
		ev.setCodeKey(key);
		ev.setValidated(validated);
		ev.setDateSent(LocalDateTime.now());
		return ev;
	}
}
