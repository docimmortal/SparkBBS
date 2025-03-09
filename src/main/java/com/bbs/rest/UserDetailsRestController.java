package com.bbs.rest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbs.services.DetailsService;

@RestController
public class UserDetailsRestController {

	@Autowired
	private DetailsService detailsService;
	
	public UserDetailsRestController(DetailsService detailsService) {
		this.detailsService=detailsService;
	}
	
	@PostMapping(path="/userDetailsForDoor")
	public String getUsernameByDoorId(@RequestBody String json) {
		JSONObject jsonObject = new JSONObject(json);
		String username ="";
		if (jsonObject.has("doorId")) {
            String doorId = jsonObject.getString("doorId");
            username = detailsService.findUsernameByDoorId(doorId);
            System.out.println("Name: " + username);
       }
		return username;
	}

}
