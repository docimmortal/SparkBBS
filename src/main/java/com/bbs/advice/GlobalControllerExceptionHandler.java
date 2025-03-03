package com.bbs.advice;

import java.io.IOException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ConstraintViolationException.class)
	public void constraintViolationException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}
}
