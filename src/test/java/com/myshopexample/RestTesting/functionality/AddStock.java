package com.myshopexample.RestTesting.functionality;

import com.myshopexample.RestTesting.CRUD.stock.StockTesting;
import com.myshopexample.RestTesting.login.LoginTest;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.jupiter.api.Test;

public class AddStock {
    @Test
    public void addItProductStock(){
        LoginTest loginTest = new LoginTest();
        StockTesting stockTesting = new StockTesting();
        CookieFilter cookieFilter = loginTest.logInUser("stockIt","stockIt");
//        stockTesting.addNewStock(cookieFilter,200);
        stockTesting.addNewStockToProduct(cookieFilter,200);
    }
    @Test
    public void addItProductStockFromFashionStockAdmin(){
        LoginTest loginTest = new LoginTest();
        StockTesting stockTesting = new StockTesting();
        CookieFilter cookieFilter = loginTest.logInUser("stockFashion","stockFashion");
//        stockTesting.addNewStock(cookieFilter,403);
        stockTesting.addNewStockToProduct(cookieFilter,403);
    }
    @Test
    public void addItProductStockFromItAdmin(){
        LoginTest loginTest = new LoginTest();
        StockTesting stockTesting = new StockTesting();
        CookieFilter cookieFilter = loginTest.logInUser("it","it");
//        stockTesting.addNewStock(cookieFilter,403);
        stockTesting.addNewStockToProduct(cookieFilter,403);
    }
}
