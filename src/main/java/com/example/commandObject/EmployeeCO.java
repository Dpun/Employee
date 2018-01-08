package com.example.commandObject;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EmployeeCO {
	private Integer emplId;
	@NotNull(message="Employee name cannot be empty.")
	private String emplName;
	@NotNull(message="Employee designation cannot be empty.")
	private String emplDesignation;
	@Min(value=15000,message="Employee Salary cannot be less than 15000.")
	@Max(value=50000,message="Employee Salary cannot be more than 15000.")
	private Integer emplSalary;
	
	public Integer getEmplId() {
		return emplId;
	}
	public void setEmplId(Integer emplId) {
		this.emplId = emplId;
	}
	public String getEmplName() {
		return emplName;
	}
	public void setEmplName(String emplName) {
		this.emplName = emplName;
	}
	public String getEmplDesignation() {
		return emplDesignation;
	}
	public void setEmplDesignation(String emplDesignation) {
		this.emplDesignation = emplDesignation;
	}
	public Integer getEmplSalary() {
		return emplSalary;
	}
	public void setEmplSalary(Integer emplSalary) {
		this.emplSalary = emplSalary;
	}
}
