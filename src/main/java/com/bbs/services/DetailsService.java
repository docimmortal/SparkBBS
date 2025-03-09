package com.bbs.services;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.bbs.entites.UserDetails;

public interface DetailsService {

	public Optional<UserDetails> findByUsername(String username);
	public String findUsernameByDoorId(String doorId);
	public Optional<UserDetails> findById(BigInteger id);
	public UserDetails save(UserDetails details);
	public List<UserDetails> findAll();
}
