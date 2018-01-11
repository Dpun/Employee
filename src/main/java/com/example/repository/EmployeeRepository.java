package com.example.repository;

import com.example.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e where e.employeeName like ?1")
    public List<Employee> findEmployeeDetailsByName(String pattern);
    @Query("SELECT e FROM Employee e Where e.department = ?1")
    public List<Employee> findEmployeeDeptWise(Department dept);
    @Query(value = "SELECT e.employee_id,e.employee_name FROM employee e,department d where e.department_dept_id=d.dept_id AND d.location =?1",nativeQuery = true)
    public List<Employee> findEmployeeByDeptCity(String location);
}
