package com.example.exception;

import org.springframework.validation.BindingResult;

import com.example.json.error.ErrorResponse;

public class ResponseErrorException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1578775757505208711L;
	private ErrorResponse errorResponse;
	
	public ResponseErrorException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public ResponseErrorException(BindingResult bindingResult) {
		this.errorResponse=new ErrorResponse(bindingResult);
	}
	
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
}
