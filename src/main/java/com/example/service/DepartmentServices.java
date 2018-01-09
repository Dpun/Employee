package com.example.service;

import com.example.api.result.Result;
import com.example.commandObject.DepartmentCO;
import com.example.constant.StatusType;
import com.example.model.Department;
import com.example.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServices  {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Result createDepartment(DepartmentCO departmentCO){
        Result result = new Result();
        if(departmentCO != null){
            Department department = new Department();
            department.setDeptName(departmentCO.getDeptName());
            department.setLocation(departmentCO.getDeptLocation());

            Department department1 = departmentRepository.save(department);
            result.setStatus(StatusType.SUCCESS);
            result.setMessage("Department Details created successfully.");
            result.setData(department1);
        }else{
            result.setStatus(StatusType.FAIL);
            result.setMessage("Department Details is empty.");
        }

        return result;
    }

    public Result findDepartmentById(Integer deptId){
        Result result = new Result();
        Optional<Department> department = departmentRepository.findById(deptId);
        if(department.isPresent()){
            result.setStatus(StatusType.SUCCESS);
            result.setData(department.get());
        }else{
            result.setStatus(StatusType.FAIL);
        }

        return result;
    }

    public Result findAllDepartment(){
        Result result = new Result();
        Iterable<Department> departments = departmentRepository.findAll();
        if(departments != null){
            result.setStatus(StatusType.SUCCESS);
            result.setMessage("Department List");
            result.setData(departments);
        }else{
            result.setStatus(StatusType.FAIL);
            result.setMessage("Department List is empty.");
        }

        return result;
    }
}
