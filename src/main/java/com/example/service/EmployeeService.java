package com.example.service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
		if(employee.isPresent()){
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

	public Result findNumberOfEmployeeDepartmentWise(){
		Result result = new Result();
		Iterable<Employee> employees = employeeRepository.findAll();
		if(employees != null){
			Map<String,Long> list = StreamSupport.stream(employees.spliterator(),false).collect(Collectors.groupingBy(e->e.getDepartment().getDeptName(),Collectors.counting()));
			if(!list.isEmpty()){
				result.setStatus(StatusType.SUCCESS);
				result.setData(list);
				result.setMessage("Result reterived successfully.");
			}
		}else{
			result.setStatus(StatusType.FAIL);
			result.setMessage("Unable to fetch Record.");
		}
		return result;
	}

	public Result findEmployeeBySalaryMoreThan(Integer amount){
		Result result = new Result();
		Iterable<Employee> employees = employeeRepository.findAll();
		if(employees != null){
			List<Employee> employeesBySalary = StreamSupport.stream(employees.spliterator(),false).filter(e-> e.getEmployeeSalary()> amount).sorted((a,b)->a.getEmployeeId().compareTo(b.getEmployeeId())).collect(Collectors.toList());
			if(!employeesBySalary.isEmpty()){
				result.setStatus(StatusType.SUCCESS);
				result.setData(employeesBySalary);
				result.setMessage("Record retrived Successfully.");
			}else {
				result.setStatus(StatusType.FAIL);
				result.setMessage("Unable to fetch Record.");
			}
		}
		return result;
	}

	public Result findTotalCostToEmployee(){
		Result result = new Result();
		Iterable<Employee> employees = employeeRepository.findAll();
		if(employees != null){
			//Integer totalCost = StreamSupport.stream(employees.spliterator(),false).collect(Collectors.summingInt(emp->emp.getEmployeeSalary()));
			Integer totalCost = StreamSupport.stream(employees.spliterator(),false).map(emp->emp.getEmployeeSalary()).reduce(0,Integer::sum);
			result.setStatus(StatusType.SUCCESS);
			result.setData(totalCost);
			result.setMessage("Record retrived Successfully.");
		}else {
			result.setStatus(StatusType.FAIL);
			result.setMessage("Unable to fetch Record.");
		}

		return result;
	}

	public Result findTotalCostToEmployeeDepartmentwise(){
		Result result = new Result();
		Iterable<Employee> employees = employeeRepository.findAll();
		if(employees != null){
			//StreamSupport.stream(employees.spliterator(),false).collect(Collectors.groupingBy(employee->employee.getDepartment().getDeptId(),Collectors.summingInt(employee->employee.getEmployeeSalary())));
			result.setStatus(StatusType.SUCCESS);
			result.setData(StreamSupport.stream(employees.spliterator(),false).collect(Collectors.groupingBy(employee->employee.getDepartment().getDeptName(),Collectors.summingInt(employee->employee.getEmployeeSalary()))));
			result.setMessage("Record retrived Successfully.");
		}else {
			result.setStatus(StatusType.FAIL);
			result.setMessage("Unable to fetch Record.");
		}
		return result;
	}

	public Result findEmployeeWithMinMaxSalary(){
		Result result = new Result();
		Iterable<Employee> employees = employeeRepository.findAll();
		if(employees != null){
			Map<String,Employee> list = new HashMap<>();
			Employee employee =	StreamSupport.stream(employees.spliterator(),false).max((emp1,emp2)->emp1.getEmployeeSalary()>emp2.getEmployeeSalary()?1:-1).get();
			list.put("Max Salary",employee);

			Employee employee2 =	StreamSupport.stream(employees.spliterator(),false).min((emp1,emp2)->emp1.getEmployeeSalary()>emp2.getEmployeeSalary()?1:-1).get();
			list.put("Min Salary",employee2);

			result.setStatus(StatusType.SUCCESS);
			result.setData(list);
			result.setMessage("Record retrived Successfully.");
		}else {
			result.setStatus(StatusType.FAIL);
			result.setMessage("Unable to fetch Record.");
		}
		return result;
	}

	public Result findEmployeeByDeptID(Integer deptID){
		Result result = new Result();
		if (deptID > 0){
			Result result1 = departmentServices.findDepartmentById(deptID);
			if(result1.getStatus().equals(StatusType.SUCCESS)){
				List<Employee> employeeList = employeeRepository.findEmployeeDeptWise((Department)result1.getData());
				result.setStatus(StatusType.SUCCESS);
				result.setData(employeeList);
				result.setMessage("Record retrived Successfully.");
			}
		}else{
			result.setStatus(StatusType.FAIL);
			result.setMessage("Unable to fetch Record.");
		}
		return result;
	}

	public Result findEmployeeByDeptLocation(String location){
		Result result = new Result();
		if(location != null && location.length()>0){
			List<Employee> list = employeeRepository.findEmployeeByDeptCity(location);
			result.setStatus(StatusType.SUCCESS);
			result.setData(list);
			result.setMessage("Record retrived Successfully.");
		}else{
			result.setStatus(StatusType.FAIL);
			result.setMessage("Unable to fetch Record.");
		}
		return result;
	}
}
