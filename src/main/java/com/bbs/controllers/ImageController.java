package com.bbs.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bbs.entites.BBSUserDetails;
import com.bbs.services.BBSUserDetailsService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ImageController {
	
	@Autowired
	private BBSUserDetailsService service;

	@GetMapping("/image/{username}")
	public void showUserImage(@PathVariable String username,
	                               HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg"); // Or whatever format you wanna use
		System.out.println("USERNAME: "+username);
		Optional<BBSUserDetails> optional = service.findByUsername(username);
		BBSUserDetails details = optional.get();
		InputStream is = new ByteArrayInputStream(details.getPhoto());
		IOUtils.copy(is, response.getOutputStream());
	}
}
