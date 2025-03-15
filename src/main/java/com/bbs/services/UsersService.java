package com.bbs.services;

import com.bbs.entites.User;

public interface UsersService {

	public User save(User user);
	public String verify(User user);
}
