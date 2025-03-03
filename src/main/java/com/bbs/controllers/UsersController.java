package com.bbs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.entites.Authority;
import com.bbs.entites.UserDetails;
import com.bbs.entites.User;
import com.bbs.services.AuthoritiesService;
import com.bbs.services.DetailsService;
import com.bbs.services.UsersService;

@Controller
public class UsersController {

	@Autowired
	private DetailsService service;
	
	@Autowired
	private UsersService uService;
	
	@Autowired
	private AuthoritiesService aService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/getUsers")
	public String displayUsers(Model model) {
		List<UserDetails> users = service.findAll();
		model.addAttribute("users", users);
		return "users/listUsers";
	}
	
	@PostMapping("/addUser")
	public String addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username,
			@RequestParam String password, @RequestParam String email, Model model) {
		System.out.println("Adding user...");
		User user = new User(username, passwordEncoder.encode(password), true);
		uService.save(user);
		Authority authority = new Authority(username,"ROLE_USER");
		aService.save(authority);
		UserDetails details = new UserDetails(username, firstName, lastName, email);
		service.save(details);
		List<UserDetails> users = service.findAll();
		model.addAttribute("users", users);
		return "users/listUsers";
	}
}
