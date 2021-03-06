package com.example.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "employee" )
public class Employee implements Serializable{
	private static final long serialVersionUID = 3887857669294571139L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer employeeId;
	@Column(name="employee_name")
	private String employeeName;
	@Column(name="employee_salary")
	private Integer employeeSalary;
	@Column(name="employee_designation")
	private String employeeDesignation;

	@ManyToOne
	private Department department;
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Integer getEmployeeSalary() {
		return employeeSalary;
	}
	public void setEmployeeSalary(Integer employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	public String getEmployeeDesignation() {
		return employeeDesignation;
	}
	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
