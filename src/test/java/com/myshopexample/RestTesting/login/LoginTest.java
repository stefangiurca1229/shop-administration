package com.myshopexample.RestTesting.login;

import com.myshopexample.RestTesting.CRUD.product.ProductTest;
import com.myshopexample.RestTesting.CRUD.stock.StockTesting;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @Test
    public CookieFilter logInUser(String userName, String password){
        CookieFilter cookieFilter = new CookieFilter();

        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri("http://localhost:9000/login")
                .contentType("multipart/form-data")
                .multiPart("username", userName)
                .multiPart("password", password)
                .when()
                .post()
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(200);
        return cookieFilter;
    }
}
