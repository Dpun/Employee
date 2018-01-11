package com.example.controller;

import javax.validation.Valid;

import com.example.commandObject.DepartmentCO;
import com.example.service.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value = "/searchByName",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result getEmployeeDetailsByName(@RequestParam String employeeName){
		return employeeService.getEmployeeDetailsByName(employeeName);
	}

	@RequestMapping(value = "/departmentwise",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result countEmployeeByDepartmentwise(){
		return employeeService.findNumberOfEmployeeDepartmentWise();
	}

	@RequestMapping(value = "/listBySalary",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result findEmployeeBySalary(@RequestParam Integer amount){
		return employeeService.findEmployeeBySalaryMoreThan(amount);
	}

	@RequestMapping(value = "/costToCompany",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result findTotalCostToEmployee(){
		return employeeService.findTotalCostToEmployee();
	}

	@RequestMapping(value = "/costDeptWise",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result totalCostByDeptWise(){
		return employeeService.findTotalCostToEmployeeDepartmentwise();
	}

	@RequestMapping(value = "/minMaxSalary",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result findMinMaxSalary(){
		return employeeService.findEmployeeWithMinMaxSalary();
	}

	@RequestMapping(value = "/find/deptWise",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result findEmployeeByDeptWise(@RequestParam Integer deptID){
		return employeeService.findEmployeeByDeptID(deptID);
	}

	@RequestMapping(value = "/find/ByLocation",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result findEmployeeByDeptLocation(@RequestParam String location){
		return employeeService.findEmployeeByDeptLocation(location);
	}
}
