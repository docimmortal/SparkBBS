package com.bbs.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bbs.entites.BBSUserDetails;
import com.bbs.enums.ReactionType;
import com.bbs.services.BBSUserDetailsService;
import com.bbs.utilities.MenuUtilities;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private BBSUserDetailsService service;
	
	// Temp


	@GetMapping("/loginPage")
	public String login() {
		//Bob password is Bob
		//Joe Cool password is Joe Cool
		return "login";
	}

	// Landing Page
	@GetMapping("/hello")
	public String hello(Model model, HttpSession session) throws IOException {
		System.out.println("=============> Accessing BBS");
		String username = "Unknown";
		BBSUserDetails details = new BBSUserDetails();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			username = authentication.getName();
			String role=authentication.getAuthorities().toString().substring(6);
			System.out.println(role);
			role=role.substring(0, role.length()-1);
			System.out.println(role);
			model.addAttribute("role",role);
			Optional<BBSUserDetails> optional = service.findByUsername(username);
			details = optional.get();

			System.out.println("Hello user ID: "+details.getId());
			model.addAttribute("userDetailsId",details.getId());
		}

		session.setAttribute("details", details);
		String unicode=ReactionType.FUNNY.getUnicode();
		model.addAttribute("unicode",unicode);

		model.addAttribute("menus",MenuUtilities.getMenus());

		return "hello";
	}
}
