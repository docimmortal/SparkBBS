package com.bbs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.entites.User;
import com.bbs.repos.UsersRepository;

@Service
public class UserServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepository repo;
	
	@Override
	public User save(User user) {
		return repo.save(user);
	}

}
