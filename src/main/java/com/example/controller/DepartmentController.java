package com.example.controller;

import com.example.api.result.Result;
import com.example.commandObject.DepartmentCO;
import com.example.service.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentServices departmentServices;

    @RequestMapping(value = "/create",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE ,consumes=MediaType.APPLICATION_JSON_VALUE)
    public Result createDepartment(@RequestBody DepartmentCO departmentCO){
        return departmentServices.createDepartment(departmentCO);
    }

    @RequestMapping(value = "/findAll",method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE )
    public Result findAllDepartment(){
        return departmentServices.findAllDepartment();
    }
}
