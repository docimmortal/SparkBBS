package com.bbs.services;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.bbs.entites.BBSUserDetails;

public interface DetailsService {

	public Optional<BBSUserDetails> findByUsername(String username);
	public UserDetails loadUserByUsername(String username);
	public String findUsernameByDoorId(String doorId);
	public Optional<BBSUserDetails> findById(BigInteger id);
	public BBSUserDetails save(BBSUserDetails details);
	public List<BBSUserDetails> findAll();
}
