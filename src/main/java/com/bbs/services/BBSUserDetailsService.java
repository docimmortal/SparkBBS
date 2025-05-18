package com.bbs.services;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.bbs.entites.BBSUserDetails;

public interface BBSUserDetailsService {

	public Optional<BBSUserDetails> findByUsername(String username);
	public BBSUserDetails loadUserByUsername(String username);
	public String findUsernameByDoorId(String doorId);
	public Optional<BBSUserDetails> findById(BigInteger id);
	public BBSUserDetails save(BBSUserDetails details);
	public List<BBSUserDetails> findAll();
}
