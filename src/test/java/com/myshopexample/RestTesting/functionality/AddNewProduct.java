package com.myshopexample.RestTesting.functionality;

import com.myshopexample.RestTesting.login.LoginTest;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class AddNewProduct {
    private final String url = "http://localhost:9000/";
    @Test
    public void addNewItProductFromItAdministrator(){
        LoginTest loginTest = new LoginTest();
        CookieFilter cookieFilter = loginTest.logInUser("it","it");
        String productJsonBody = String.format("""
                {
                 "title": "%s",
                 "price": 780.99,
                  "description": "merge",
                  "thumbnail": "telefon mobil",
                  "category": "ELECTRONICS",
                  "departments": [{
                  "id": 1
                  }]
                }
                """, "samsung");
            RestAssured
                    .given()
                    .filter(cookieFilter)
                    .baseUri(url + "save-new-product")
                    .contentType(ContentType.JSON)
                    .body(productJsonBody)
                    .when()
                    .post()
                    .then()
                    .assertThat()
                    .statusCode(200);
    }
    @Test
    public void addNewProductFromFashionAdministrator(){
        LoginTest loginTest = new LoginTest();
        CookieFilter cookieFilter = loginTest.logInUser("fashion","fashion");
        String productJsonBody = """
                {
                 "title": "samsung",
                 "price": 780.99,
                  "description": "merge",
                  "thumbnail": "telefon mobil",
                  "category": "ELECTRONICS",
                  "departments": [{
                  "id": 1
                  }]
                }
                """;
        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri(url + "save-new-product")
                .contentType(ContentType.JSON)
                .body(productJsonBody)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(403);
    }
    @Test
    public void addALotOfPrducts(){
        LoginTest loginTest = new LoginTest();
        CookieFilter fashionCookieFilter = loginTest.logInUser("fashion","fashion");
        CookieFilter itCookieFiltre = loginTest.logInUser("it","it");
        String[] itProductNames = {"casti", "laptop", "ssd", "hdd", "keyboard", "televizor", "mouse", "boxa"};
        String[] fashionProductsName = {"camasa", "tricou", "curea", "sosete", "aidasi","hanorac","pantaloni","geaca"};
        ALotOfProducts aLotOfItProducts = new ALotOfProducts(itProductNames,"ELECTRONICS",itCookieFiltre, "1");
        aLotOfItProducts.addALotOfPrducts();
        ALotOfProducts aLotOfFashionProducts = new ALotOfProducts(fashionProductsName,"CLOTHES",fashionCookieFilter, "2");
        aLotOfFashionProducts.addALotOfPrducts();
        }
    @Test
    public void addNewProductUnauthorisedPerson(){
        String productJsonBody = """
                {
                 "title": "samsung",
                 "price": 780.99,
                  "description": "foarte foarte buna",
                  "thumbnail": "tableta",
                  "category": "ELECTRONICS",
                  "departments": [{
                  "id": 1
                  }]
                }
                """;

        RestAssured
                .given()
                .baseUri(url + "save-new-product")
                .contentType(ContentType.JSON)
                .body(productJsonBody)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(401);
    }
    @Test
    public void addNewProductFromAStockAdministrator(){
        LoginTest loginTest = new LoginTest();
        CookieFilter cookieFilter = loginTest.logInUser("stockIt","stockIt");
        String productJsonBody = """
                {
                 "title": "samsung",
                  "description": "merge",
                  "thumbnail": "telefon mobil",
                  "category": "ELECTRONICS",
                  "departments": [{
                  "id": 1
                  }]
                }
                """;
        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri(url + "save-new-product")
                .contentType(ContentType.JSON)
                .body(productJsonBody)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(403);
    }
}
