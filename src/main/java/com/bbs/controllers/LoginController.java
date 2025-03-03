package com.bbs.controllers;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bbs.entites.UserDetails;
import com.bbs.enums.ReactionType;
import com.bbs.services.DetailsService;
import com.bbs.utilities.MenuUtilities;

@Controller
public class LoginController {

	@Autowired
	private DetailsService service;
	
	// Temp


	@GetMapping("/loginPage")
	public String login() {
		//Bob password is Bob
		//Joe Cool password is Joe Cool
		return "login";
	}

	// Landing Page
	@GetMapping("/hello")
	public String hello(Model model) throws IOException {
		String username = "Unknown";
		UserDetails details = new UserDetails();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			username = authentication.getName();
			String role=authentication.getAuthorities().toString().substring(6);
			System.out.println(role);
			role=role.substring(0, role.length()-1);
			System.out.println(role);
			model.addAttribute("role",role);
			Optional<UserDetails> optional = service.findByUsername(username);
			details = optional.get();

			System.out.println("Hello user ID: "+details.getId());
			model.addAttribute("userDetailsId",details.getId());
		}

		model.addAttribute("details", details);
		String unicode=ReactionType.FUNNY.getUnicode();
		model.addAttribute("unicode",unicode);

		model.addAttribute("menus",MenuUtilities.getMenus());

		return "hello";
	}
}
