package com.bbs.repos;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bbs.entites.EmailValidation;

import jakarta.transaction.Transactional;

@Repository
public interface EmailValidationRepository extends JpaRepository<EmailValidation, BigInteger>{

	@Query("select ev from EmailValidation ev WHERE ev.validated = FALSE")
	List<EmailValidation> getUnvalidatedEmails();
	
	@Query("select case when exists ( select ev from EmailValidation ev where LOWER(ev.email)=LOWER(:email) AND LOWER(ev.codeKey)=LOWER(:codeKey) ) THEN 'TRUE' ELSE 'FALSE' END")
	Boolean checkKey(String email, String codeKey);
	
	@Query ("select ev.validated from EmailValidation ev where LOWER(ev.email)=LOWER(:email)")
	Boolean checkValidated(String email);
	
	Boolean existsByEmailIgnoreCaseAndCodeKeyIgnoreCase(String email, String codeKey);
	
	Integer countByEmailIgnoreCase(String email);
	
	@Modifying
	@Transactional
	void deleteByEmailIgnoreCase(String email);
	
	@Modifying
	@Transactional
	@Query("update EmailValidation ev set ev.validated=true where LOWER(ev.email)= LOWER(:email) and LOWER(ev.codeKey)=LOWER(:codeKey)")
	void validateEmail(String email, String codeKey);
}
