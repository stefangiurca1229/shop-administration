package com.myshopexample.repositories;

import com.myshopexample.model.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByName(String departmentName);
}
