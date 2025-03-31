package com.bbs.controllers;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.entites.Authority;
import com.bbs.entites.BBSUserDetails;
import com.bbs.entites.User;
import com.bbs.services.AuthoritiesService;
import com.bbs.services.BBSUserDetailsService;
import com.bbs.services.UsersService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsersController {

	@Autowired
	private BBSUserDetailsService service;
	
	@Autowired
	private UsersService uService;
	
	@Autowired
	private AuthoritiesService aService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 @Autowired
     private AuthenticationManager authenticationManager;
	
	@PostMapping("/getUsers")
	@PreAuthorize("hasRole('ADMIN')")
	public String displayUsers(Model model) {
		List<BBSUserDetails> users = service.findAll();
		model.addAttribute("users", users);
		return "users/listUsers";
	}
	
	@GetMapping("/newUserPage")
	public String goToNewUserPage() {
		return "users/newUser";
	}
	
	@PostMapping("/addUser")
	public String addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username,
			@RequestParam String password1, @RequestParam String password2, @RequestParam String email, 
			HttpServletRequest request, Model model) {
		System.out.println("Adding user...");
		User user = new User(username, passwordEncoder.encode(password1), true);
		uService.save(user);
		Authority authority = new Authority(username,"ROLE_USER");
		aService.save(authority);
		
		// Generate player ID
		long now = System.currentTimeMillis();
        Long number = new Random(now).nextLong();
		String playerId=number.toString()+username.charAt(0)+firstName.charAt(0)+lastName.charAt(0)+email.charAt(0);
		playerId =  passwordEncoder.encode(playerId).substring(8);
		
		BBSUserDetails details = new BBSUserDetails(username, playerId, firstName, lastName, email);
		service.save(details);

		try {
	        request.login(username, password1);
	    } catch (ServletException e) {
	        e.printStackTrace();
	    }
		return "redirect:/hello";
	}
	
	public void authWithAuthManager(HttpServletRequest request, String username, String password) {
	    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
	    authToken.setDetails(new WebAuthenticationDetails(request));
	    
		Authentication authentication = authenticationManager.authenticate(authToken);
	    
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	@PostMapping("/admitAddUser")
	public String admitAddUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username,
			@RequestParam String password1, @RequestParam String password2, @RequestParam String email, Model model) {
		System.out.println("Adding user...");
		User user = new User(username, passwordEncoder.encode(password1), true);
		uService.save(user);
		Authority authority = new Authority(username,"ROLE_USER");
		aService.save(authority);
		
		// Generate player ID
		long now = System.currentTimeMillis();
        Long number = new Random(now).nextLong();
		String playerId=number.toString()+username.charAt(0)+firstName.charAt(0)+lastName.charAt(0)+email.charAt(0);
		playerId =  passwordEncoder.encode(playerId).substring(8);
		
		BBSUserDetails details = new BBSUserDetails(username, playerId, firstName, lastName, email);
		service.save(details);
		List<BBSUserDetails> users = service.findAll();
		model.addAttribute("users", users);
		return "users/listUsers";
	}
}
