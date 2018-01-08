package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.result.Result;
import com.example.constant.StatusType;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Result createEmployee(Employee employee){
		Employee saveEmployee = employeeRepository.save(employee);
		Result result = new Result();
		if(saveEmployee != null){
			result.setStatus(StatusType.SUCCESS);
			result.setMessage("Employee created Successfully.");
			result.setData(saveEmployee);
		}else{
			result.setStatus(StatusType.FAIL);
			result.setMessage("Fail to create Employee.");
		}
		
		return result;
	}
	
	public Result getAllEmployeeList(){
		Iterable<Employee> employeeIterable = employeeRepository.findAll();
		Result result = new Result();
		if(employeeIterable != null){
			result.setStatus(StatusType.SUCCESS);
			result.setMessage("Employee list get Successfully.");
			result.setData(employeeIterable);
		}else{
			result.setStatus(StatusType.FAIL);
			result.setMessage("Fail to get employee list.");
		}
		
		return result;
	}
	
	public Result findEmployeeById(Integer employeeId){
		Result result = new Result();
		if(employeeId != null && employeeId != 0){
			Optional<Employee> employee = employeeRepository.findById(employeeId);
			if(employee.isPresent()){
				result.setStatus(StatusType.SUCCESS);
				result.setMessage("Employee details get successfully.");
				result.setData(employee);
			}else{
				result.setStatus(StatusType.FAIL);
				result.setMessage("Employee record not found.");
			}
			
			return result;
		}else{
			result.setStatus(StatusType.FAIL);
			result.setMessage("Fail to get Employee details.id : "+employeeId);
			
			return result;
		}
	}
	
	public Result updateEmployeeDetails(Integer employeeId,Employee employee){
		Result result = new Result();
		if(employeeId == null || employeeId == 0){
			result.setStatus(StatusType.FAIL);
			result.setMessage("Fail to update Employee details.id : "+employeeId);
			
			return result;
		}
		employee.setEmployeeId(employeeId);
		Employee saveEmployee = employeeRepository.save(employee);
		if(saveEmployee != null){
			result.setStatus(StatusType.SUCCESS);
			result.setMessage("Employee details updated Successfully.");
			result.setData(saveEmployee);
			
			return result;
		}else{
			result.setStatus(StatusType.FAIL);
			result.setMessage("Fail to update Employee details.id : "+employeeId);
			
			return result;
		}
	}
	
	public Result partialUpdateEmployeeDetails(Integer employeeId,Employee partialEmployeeDetails){
		Result result = new Result();
		if(employeeId == null || employeeId == 0){
			result.setStatus(StatusType.FAIL);
			result.setMessage("Fail to partial update Employee details.id : "+employeeId);
			
			return result;
		}
		Optional<Employee> emp = employeeRepository.findById(employeeId);
		if(emp.isPresent()){
			Employee employee = emp.get();
			if(partialEmployeeDetails.getEmployeeDesignation() != null)
				employee.setEmployeeDesignation(partialEmployeeDetails.getEmployeeDesignation());
			if(partialEmployeeDetails.getEmployeeName() != null)
				employee.setEmployeeName(partialEmployeeDetails.getEmployeeName());
			if(partialEmployeeDetails.getEmployeeSalary() != null && partialEmployeeDetails.getEmployeeSalary() != 0)
				employee.setEmployeeSalary(partialEmployeeDetails.getEmployeeSalary());
			
			Employee saveEmployee = employeeRepository.save(employee);
			
			if(saveEmployee != null){
				result.setStatus(StatusType.SUCCESS);
				result.setMessage("Employee details updated Successfully.");
				result.setData(saveEmployee);
			}else{
				result.setStatus(StatusType.FAIL);
				result.setMessage("Fail to update Employee details.id : "+employeeId);
			}
		}else{
			result.setStatus(StatusType.FAIL);
			result.setMessage("Employee record not found.");
		}
		return result;
		
	}
	
	public Result deleteEmployee(Integer employeeId){
		Result result = new Result();
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if(employee != null){
			employeeRepository.delete(employee.get());
			result.setStatus(StatusType.SUCCESS);
			result.setMessage("Employee details deleted Successfully.id : "+employeeId);
		}else{
			result.setStatus(StatusType.FAIL);
			result.setMessage("Fail to update Employee details.id : "+employeeId);
		}
		
		return result;
	}
}
