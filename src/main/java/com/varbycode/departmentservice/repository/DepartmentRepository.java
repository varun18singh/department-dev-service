package com.varbycode.departmentservice.repository;

import com.varbycode.departmentservice.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository {

    private List<Department> list=new ArrayList<>();

    public Department addDepartment(Department department){
        list.add(department);
        return department;
    }

    public Department findById(Long id){
        return list.stream()
                .filter(department -> department.getId() == id).findFirst().orElseThrow();
    }

    public List<Department> findAll(){
        return list;
    }
}
