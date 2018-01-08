package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.result.Result;
import com.example.commandObject.EmployeeCO;
import com.example.exception.ResponseErrorException;
import com.example.model.Employee;
import com.example.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	@ResponseBody
	public String hello(){
		return "hello Dinesh Pun";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE ,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result createEmployee(@Valid @RequestBody EmployeeCO employee,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new ResponseErrorException(bindingResult);
		}
		return employeeService.createEmployee(employee);
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public Result getAllEmployee(){
		return employeeService.getAllEmployeeList();
	}
	
	@RequestMapping(value="/{employeeId}",method=RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result updateEmployeeDetails(@PathVariable Integer employeeId,@RequestBody Employee employee){
		return employeeService.updateEmployeeDetails(employeeId,employee);
	}
	
	@RequestMapping(value="/{employeeId}",method=RequestMethod.PATCH,produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result partialUpdateEmployeeDetails(@PathVariable Integer employeeId,@RequestBody Employee employee){
		return employeeService.partialUpdateEmployeeDetails(employeeId,employee);
	}
	
	@RequestMapping(value="/{employeeId}",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result getEmployeeById(@PathVariable Integer employeeId){
		return employeeService.findEmployeeById(employeeId);
	}
	
	@RequestMapping(value="/delete/{employeeId}",method=RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result deleteEmployee(@PathVariable Integer employeeId){
		return employeeService.deleteEmployee(employeeId);
	}
}
