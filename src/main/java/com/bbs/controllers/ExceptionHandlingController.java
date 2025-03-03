package com.bbs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ExceptionHandlingController {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception ex) {
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", ex);
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName("error");
		return mav;
	}
}
