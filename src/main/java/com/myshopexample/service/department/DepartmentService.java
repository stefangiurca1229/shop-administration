package com.myshopexample.service.department;

import com.myshopexample.model.department.Department;
import com.myshopexample.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department getDepartmentByName(String departmentName){
        return departmentRepository.findByName(departmentName);
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).get();
    }
}
