package com.bbs.rest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbs.services.BBSUserDetailsService;

@RestController
public class UserDetailsRestController {

	@Autowired
	private BBSUserDetailsService detailsService;
	
	public UserDetailsRestController(BBSUserDetailsService detailsService) {
		this.detailsService=detailsService;
	}
	
	@PostMapping(path="/userDetailsForDoor")
	public String getUsernameByDoorId(@RequestBody String json) {
		JSONObject jsonObject = new JSONObject(json);
		String username ="";
		if (jsonObject.has("doorId")) {
            String doorId = jsonObject.getString("doorId");
            username = detailsService.findUsernameByDoorId(doorId);
       }
		return username;
	}

}
