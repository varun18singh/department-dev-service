package com.varbycode.departmentservice.controller;


import com.varbycode.departmentservice.client.EmployeeClient;
import com.varbycode.departmentservice.model.Department;
import com.varbycode.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER= LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeClient employeeClient;

    @PostMapping
    public Department add(@RequestBody Department department){
        LOGGER.info("Department add: {} "+department);
        return departmentRepository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll(){
        LOGGER.info("list of all Department : {} ");
        return  departmentRepository.findAll();
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployee(){
        LOGGER.info("list of all find AllWithEmployee : {} ");
        List<Department> departments=  departmentRepository.findAll();

        departments.forEach(department -> department.setEmployees(employeeClient.findByDepartment((long) department.getId())));

        return departments;
    }


    @GetMapping("/{id}")
    public Department findById(@PathVariable("id") Long id){
        LOGGER.info("Department find by Id : {} "+id);
        return departmentRepository.findById(id);
    }


}
