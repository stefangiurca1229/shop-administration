package com.myshopexample.RestTesting.CRUD.stock;

import com.myshopexample.RestTesting.login.LoginTest;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.ContentType;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StockTesting {
    private final String url = "http://localhost:9000/";
    @Test
    public void addNewStock(CookieFilter cookieFilter, int expectedCode){
        String stockProduct1JsonBody = """
                {
                "quantity": 100,
                "enable": true
                }
                """;
        String stockProduct2JsonBody = """
                {
                "quantity": 10,
                "enable": true
                }
                """;
        String stockProduct3JsonBody = """
                {
                "quantity": 100,
                "enable": true
                }
                """;
        List<String> productStocks = new ArrayList<>(){{
            add(stockProduct1JsonBody);
            add(stockProduct2JsonBody);
            add(stockProduct3JsonBody);
        }};

        for(int i=0; i<productStocks.size(); i++)
        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri(url + "add-new-stock")
                .contentType(ContentType.JSON)
                .body(productStocks.get(i))
                .queryParam("productId",i+1)
                .when()
                .post()
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(expectedCode);
    }

    @Test
    public void addNewStockToProduct(CookieFilter cookieFilter, int expectedCode){
        String stockProductJsonBody = """
                {
                "quantity": 100,
                "enable": true
                }
                """;
        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri(url + "add-new-stock")
                .contentType(ContentType.JSON)
                .body(stockProductJsonBody)
                .queryParam("productId",1)
                .when()
                .post()
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(expectedCode);
    }

    @Test
    public void getStockByProductId(){
        RestAssured
                .given()
                .baseUri(url+"get-stock")
                .queryParam("productId", "1")
                .when()
                .get()
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(200);
    }
}
