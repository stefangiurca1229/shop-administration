package com.myshopexample.model.dto.product;

import com.myshopexample.model.department.Department;
import com.myshopexample.model.dto.department.DepartmentDto;
import com.myshopexample.model.product.Category;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String thumbnail;
    private Category category;
    private List<Department> departments;
}
