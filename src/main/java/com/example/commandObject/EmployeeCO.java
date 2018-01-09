package com.example.commandObject;

import javax.validation.constraints.NotNull;

import com.example.validator.constraint.DepartmentConstraint;
import com.example.validator.constraint.NameConstraint;
import com.example.validator.constraint.SalaryConstraint;

public class EmployeeCO  {
	private Integer emplId;
	@NotNull
	@NameConstraint(message="Employee name length must not be less than 5.")
	private String emplName;
	private String emplDesignation;
	@SalaryConstraint(message="Salary must be between 5000 and 50000.")
	private Integer emplSalary;
	@DepartmentConstraint
	private Integer deptId;

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

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
}
