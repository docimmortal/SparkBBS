package com.bbs.utilities;

import java.util.ArrayList;
import java.util.List;

import com.bbs.entites.Menu;

public class MenuUtilities {

	public static List<Menu> getMenus() {
		List<Menu> menus = new ArrayList<>();
		menus.add(new Menu("Message Board","/readMessage"));
		menus.add(new Menu("Doors","/doors"));
		return menus;
	}
	
	// Temporary. This will be stored in DB table.
	public static List<Menu> getDoors() {
		List<Menu> menus = new ArrayList<>();
		menus.add(new Menu("Test Door","/doorTest"));
		return menus;
	}
}
