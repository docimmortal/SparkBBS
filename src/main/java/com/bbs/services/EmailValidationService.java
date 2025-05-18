package com.bbs.services;

import java.util.List;

import com.bbs.entites.EmailValidation;

public interface EmailValidationService {

	List<EmailValidation> getUnvalidatedEmails();
	Boolean checkValidated(String email);
	Integer countByEmailIgnoreCase(String email);
	void deleteByEmailIgnoreCase(String email);
	void validateEmail(String email, String codeKey);
	String getCodeKey(String email);
	String generateCodeKey(String email);
}
