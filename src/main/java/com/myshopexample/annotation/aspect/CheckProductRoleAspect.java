package com.myshopexample.annotation.aspect;

import com.myshopexample.exception.AccessDeniedException;
import com.myshopexample.model.admin.Administrator;
import com.myshopexample.model.department.Department;
import com.myshopexample.model.dto.product.ProductDto;
import com.myshopexample.model.product.Product;
import com.myshopexample.service.ProductsService;
import com.myshopexample.service.security.PrincipalProvider;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

@Aspect
@Component
public class CheckProductRoleAspect {
    @Autowired
    private PrincipalProvider principalProvider;
    @Autowired
    private ProductsService productsService;

    @Before(value = "@annotation(com.myshopexample.annotation.CheckProductRole)")
    public void checkRole(JoinPoint joinPoint){
        Administrator administrator = principalProvider.getPrincipalProvider();
        AtomicBoolean ok = new AtomicBoolean(false);
        Arrays.stream(joinPoint.getArgs()).forEach(
                o ->{
                    if(o instanceof ProductDto productDto){
                        if(CheckStockRoleAspect.verifyDepartments(productDto.getDepartments(),administrator.getDepartments()))
                            ok.set(true);
                    } else if (o instanceof Long productId) {
                        Product product = productsService.getProductById(productId);
                        if(CheckStockRoleAspect.verifyDepartments(product.getDepartments(),administrator.getDepartments()))
                            ok.set(true);
                    }
                }
        );
        if(!ok.get())
            throw new AccessDeniedException("you are not allowed to access this product");
    }
}
