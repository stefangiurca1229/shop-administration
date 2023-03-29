package com.myshopexample.service;

import com.myshopexample.model.admin.Administrator;
import com.myshopexample.model.department.Department;
import com.myshopexample.model.dto.product.ProductDto;
import com.myshopexample.model.logs.Action;
import com.myshopexample.model.logs.Logs;
import com.myshopexample.model.product.Product;
import com.myshopexample.model.mapper.ProductMapper;
import com.myshopexample.repositories.LogRepository;
import com.myshopexample.repositories.ProductRepository;
import com.myshopexample.responses.GenericResponse;
import com.myshopexample.service.department.DepartmentService;
import com.myshopexample.service.security.PrincipalProvider;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class ProductsService {
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    @Autowired
    private PrincipalProvider adminFromSpringSecurityContext;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LogRepository logRepository;

//    public static Boolean checkDepartemnts(List<Department> productDepartments, List<Department> adminDepartments){
//        for(Department prodDep : productDepartments){
//            for(Department adminDep : adminDepartments)
//                if(prodDep == adminDep)
//                    return true;
//        }
//        return false;
//    }

    public ProductDto saveProduct(ProductDto productDto){
        Administrator administrator = adminFromSpringSecurityContext.getPrincipalProvider();
        List<Department> departments = new ArrayList<>();
        productDto.getDepartments().forEach(dep -> {
            Department department = departmentService.getDepartmentById(dep.getId());
            departments.add(department);
        });
        Product product = productMapper.ProductDtoToProduct(productDto);
        product.setDepartments(departments);
        productRepository.save(product);
        Logs log = new Logs(administrator.getName(),product.getTitle() + " was saved", Action.SAVE, LocalDate.now());
        logRepository.save(log);
        return productDto;
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).get();
    }

    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productMapper.ProductDtoToProduct(productDto);
        productRepository.save(product);
        return productDto;
    }
}
