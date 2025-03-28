package com.bbs.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.entites.EmailValidation;
import com.bbs.repos.EmailValidationRepository;


@Service
public class EmailValidationServiceImpl implements EmailValidationService {

	@Autowired
	private EmailValidationRepository repo;
	
	public List<EmailValidation> getUnvalidatedEmails() {
		return repo.getUnvalidatedEmails();
	}
	
	public Boolean checkValidated(String email) {
		return repo.checkValidated(email);
	}
	
	public Integer countByEmailIgnoreCase(String email) {
		return repo.countByEmailIgnoreCase(email);
	}
	
	// Used if administrator wants to delete an entry
	public void deleteByEmailIgnoreCase(String email) {
		repo.deleteByEmailIgnoreCase(email);
	}
	
	// sets to true if key matches and key has not expired
	public void validateEmail(String email, String codeKey) {
		LocalDateTime nowMinus15 = LocalDateTime.now().minusMinutes(15);
		Boolean validated = repo.checkKey(email, codeKey, nowMinus15);
		if (validated) {
			repo.validateEmail(email, codeKey);
		}
		
	}
	
	public String getCodeKey(String email) {
		return repo.getCodeKey(email);
	}
	
	public String generateCodeKey(String email) {
		Integer count = repo.countByEmailIgnoreCase(email);
		if (count != 0) {
			repo.deleteByEmailIgnoreCase(email);
		}
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder codeKey=new StringBuilder();
		for (int i=0; i<6; i++) {
			int num = rand.nextInt(10);
			codeKey.append(Integer.toString(num));
		}
		repo.save(createEmailValidation(email,codeKey.toString(), false));
		return codeKey.toString();
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
