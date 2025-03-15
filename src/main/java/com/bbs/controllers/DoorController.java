package com.bbs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.entites.Menu;
import com.bbs.entites.User;
import com.bbs.entites.UserDetails;
import com.bbs.services.JwtService;
import com.bbs.utilities.MenuUtilities;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class DoorController {

	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/doorTest")
	public ModelAndView doorTest(@RequestParam(required=true) String token,
			@RequestParam(required=true) String doorId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:http://localhost:9000/main");
		mav.addObject("token", token);
		mav.addObject("doorId", doorId);
		return mav;
	}
	
	@PostMapping("/doors")
	public String doors(Model model, HttpServletRequest request, HttpSession session) {
		model.addAttribute("menus",MenuUtilities.getMenus());
		model.addAttribute("doorlist",MenuUtilities.getDoors());
		session.setAttribute("sessionId",request.getSession().getId());
		UserDetails details=(UserDetails)session.getAttribute("details");
		model.addAttribute("token",jwtService.generateToken(details.getUsername()));
		List<Menu> stuff = MenuUtilities.getDoors();
		for (Menu m: stuff) {
			System.out.println(m.getUrl()+" "+m.getLabel());
		}
		return "doors/doors";
	}
}
