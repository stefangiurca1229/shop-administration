package com.myshopexample.RestTesting.CRUD.product;

import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductTest {
    private final String url = "http://localhost:9000/";
    @Test
    public void getProductsByCategory(){
        RestAssured
                .given()
                .baseUri(url+"get-products-by-category")
                .queryParam("category","FOOD")
                .queryParam("page", "1")
                .queryParam("size", "1")
                .when()
                .get()
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(200);
    }

    public void postProductTest(CookieFilter cookieFilter, int expectedCode){
        String product1JsonBody = """
                {
                 "title": "nuci",
                  "description": "sunt bune",
                  "stock": 9,
                  "price": 8.2,
                  "thumbnail": "copacul vesel",
                  "category": "FOOD"
                }
                """;
        String product2JsonBody = """
                {
                 "title": "sarmale",
                  "description": "chiar bune",
                  "stock": 19,
                  "price": 8.12,
                  "thumbnail": "poza",
                  "category": "FOOD"
                }
                """;
        String product3JsonBody = """
                {
                 "title": "samsung",
                  "description": "merge",
                  "stock": 87,
                  "price": 699.99,
                  "thumbnail": "telefon mobil",
                  "category": "ELECTRONICS"
                }
                """;

        List<String> s = new ArrayList<>();
        s.add(product1JsonBody);
        s.add(product2JsonBody);
        s.add(product3JsonBody);

        for(String JsonBody : s) {
            RestAssured
                    .given()
                    .filter(cookieFilter)
                    .baseUri(url + "save-new-product")
                    .contentType(ContentType.JSON)
                    .body(JsonBody)
                    .when()
                    .post()
                    .then()
                    .assertThat()
                    .statusCode(expectedCode);
        }
    }

    public void updateProduct(CookieFilter cookieFilter, int expectedCode){
        String productJsonBody = """
                {
                "id": 1,
                 "title": "ssd",
                 "price": 60.99,
                  "description": "120 GB",
                  "thumbnail": "stocare",
                  "category": "ELECTRONICS",
                  "departments": [{
                  "id": 1
                  }]
                }
                """;
        RestAssured
                .given()
                .filter(cookieFilter)
                .baseUri(url + "update-product")
                .contentType(ContentType.JSON)
                .body(productJsonBody)
                .when()
                .put()
                .then()
//                .log()
//                .body()
                .assertThat()
                .statusCode(expectedCode);
    }

}
