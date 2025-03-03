package com.bbs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.entites.Authority;
import com.bbs.repos.AuthoritiesRepository;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

	@Autowired
	private AuthoritiesRepository repo;
	
	@Override
	public Authority save(Authority authority) {
		return repo.save(authority);
	}

}
