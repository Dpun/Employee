package com.example.json.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;

import com.example.constant.StatusType;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat
public class ErrorResponse {
	private String status;
	private List<String> errorList;
	
	public ErrorResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public ErrorResponse(BindingResult bindingResult) {
		this.setStatus(StatusType.FAIL);
		errorList = new ArrayList<>();
		if(bindingResult.hasErrors()){
			bindingResult.getFieldErrors().forEach((k)->{
				errorList.add(k.getDefaultMessage());
			});
		}
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return status;
	}
}
