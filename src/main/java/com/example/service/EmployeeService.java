package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.result.Result;
import com.example.commandObject.EmployeeCO;
import com.example.constant.StatusType;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentServices departmentServices;
	
	public Result createEmployee(EmployeeCO employee){
		Result result2 = new Result();
		Employee emp = new Employee();
		emp.setEmployeeName(employee.getEmplName());
		emp.setEmployeeSalary(employee.getEmplSalary());
		emp.setEmployeeDesignation(employee.getEmplDesignation());

		if(employee.getDeptId() != null && employee.getDeptId() > 0){
			Result result = departmentServices.findDepartmentById(employee.getDeptId());
			if(result.getStatus().equals(StatusType.SUCCESS)){
				emp.setDepartment((Department) result.getData());
				Employee saveEmployee = employeeRepository.save(emp);

				if(saveEmployee != null){
					result2.setStatus(StatusType.SUCCESS);
					result2.setMessage("Employee created Successfully.");
					result2.setData(saveEmployee);
				}else{
					result2.setStatus(StatusType.FAIL);
					result2.setMessage("Fail to create Employee.");
				}
			}else{
				result2.setStatus(StatusType.FAIL);
				result2.setMessage("Department is not found to map with Employee.");
			}
		}

		return result2;
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

	public Result getEmployeeDetailsByName(String employeeName){
		Result result = new Result();
		if(employeeName == null && employeeName.length() == 0 && employeeName.equals("") ){
			result = new Result();
			result.setStatus(StatusType.FAIL);
			result.setMessage("Employee Name cannot be empty.");

			return result;
		}
		List<Employee> employee = employeeRepository.findEmployeeDetailsByName("%"+employeeName+"%");
		if(!employee.isEmpty()){
			result.setStatus(StatusType.SUCCESS);
			result.setMessage("Employee details fetched successfully.");
			result.setData(employee);
		}else{
			result.setStatus(StatusType.FAIL);
			result.setMessage("Employee details not found by this name : "+employeeName);
		}

		return result;
	}
}
