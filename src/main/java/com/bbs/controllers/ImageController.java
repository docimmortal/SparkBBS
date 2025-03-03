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

import com.bbs.entites.UserDetails;
import com.bbs.services.DetailsService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ImageController {
	
	@Autowired
	private DetailsService service;

	@GetMapping("/image/{username}")
	public void showUserImage(@PathVariable String username,
	                               HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg"); // Or whatever format you wanna use
		System.out.println("USERNAME: "+username);
		Optional<UserDetails> optional = service.findByUsername(username);
		UserDetails details = optional.get();
		InputStream is = new ByteArrayInputStream(details.getPhoto());
		IOUtils.copy(is, response.getOutputStream());
	}
}
