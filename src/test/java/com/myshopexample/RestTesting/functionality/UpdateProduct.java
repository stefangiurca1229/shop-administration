package com.myshopexample.RestTesting.functionality;

import com.myshopexample.RestTesting.CRUD.product.ProductTest;
import com.myshopexample.RestTesting.login.LoginTest;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.jupiter.api.Test;

public class UpdateProduct {
    @Test
    public void updateProductByItAdministrator(){
        LoginTest loginTest = new LoginTest();
        ProductTest productTest = new ProductTest();
        CookieFilter cookieFilter = loginTest.logInUser("it","it");
        productTest.updateProduct(cookieFilter,200);
    }
    @Test
    public void updateProductByFashionAdministrator(){
        LoginTest loginTest = new LoginTest();
        ProductTest productTest = new ProductTest();
        CookieFilter cookieFilter = loginTest.logInUser("fashion","fashion");
        productTest.updateProduct(cookieFilter,403);
    }
}
