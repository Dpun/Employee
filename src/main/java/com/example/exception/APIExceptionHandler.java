package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.json.error.ErrorResponse;

@ControllerAdvice
@RestController
public class APIExceptionHandler {
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ResponseErrorException.class)
	public ErrorResponse handleValidateException(ResponseErrorException ex){
		return ex.getErrorResponse();
	}
}
