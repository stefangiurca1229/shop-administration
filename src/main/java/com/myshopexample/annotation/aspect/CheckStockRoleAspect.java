package com.myshopexample.annotation.aspect;

import com.myshopexample.exception.AccessDeniedException;
import com.myshopexample.model.admin.Administrator;
import com.myshopexample.model.department.Department;
import com.myshopexample.model.dto.stock.StockDto;
import com.myshopexample.model.product.Product;
import com.myshopexample.service.ProductsService;
import com.myshopexample.service.security.PrincipalProvider;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Aspect
@Component
public class CheckStockRoleAspect {
    @Autowired
    private PrincipalProvider principalProvider;

    @Autowired
    private ProductsService productsService;

    public final static boolean verifyDepartments(List<Department> productDepartments, List<Department> administrstorDepartments){
        for(Department prodDepartment : productDepartments)
            for(Department adminDepartment : administrstorDepartments)
                if(prodDepartment.getId() == adminDepartment.getId())
                    return true;
        return false;
    }

    @Before(value = "@annotation(com.myshopexample.annotation.CheckStockRole)")
    public void before(JoinPoint joinPoint){
        Administrator administrator = principalProvider.getPrincipalProvider();
        AtomicBoolean ok = new AtomicBoolean(false);
        Arrays.stream(joinPoint.getArgs())
                .forEach(
                        o ->{
                            if(o instanceof StockDto stockDto){
                                Product product = stockDto.getProduct();
                                if(CheckStockRoleAspect.verifyDepartments(product.getDepartments(),administrator.getDepartments()))
                                    ok.set(true);
                            }
                        }
                );
        if(!ok.get())
            throw new AccessDeniedException("you are not allowed to access this departments");
    }
}