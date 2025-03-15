package com.bbs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bbs.entites.User;
import com.bbs.repos.UsersRepository;

@Service
public class UserServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepository repo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;
	
	public String verify(User user) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUsername());
		}
		return "failed";
	}
	
	@Override
	public User save(User user) {
		return repo.save(user);
	}

}
