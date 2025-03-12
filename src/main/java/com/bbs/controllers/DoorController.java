package com.bbs.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.bbs.entites.Menu;
import com.bbs.utilities.MenuUtilities;

import jakarta.servlet.http.HttpSession;

@Controller
public class DoorController {

	@PostMapping("/doorTest")
	public String doorTest() {
		return "redirect:http://localhost:9000/main";
	}
	
	@PostMapping("/doors")
	public String doors(Model model, HttpSession session) {
		model.addAttribute("menus",MenuUtilities.getMenus());
		model.addAttribute("doorlist",MenuUtilities.getDoors());
		List<Menu> stuff = MenuUtilities.getDoors();
		for (Menu m: stuff) {
			System.out.println(m.getUrl()+" "+m.getLabel());
		}
		return "doors/doors";
	}
}
