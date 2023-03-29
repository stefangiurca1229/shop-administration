package com.myshopexample.RestTesting.functionality;

import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class ALotOfProducts {
    private final String[] atributes = {" violet", " roz", " orange"};
    private final String[] models = {" A0"," A1"," A2"};
    private final String[] productionYears = {" 2018"," 2019"," 2020"," 2021"," 2022"};
    private final String categoryName;
    private final CookieFilter cookieFilter;
    private final String url = "http://localhost:9000/";
    private final String[] depNameProductNames;
    private final String depId;
    public ALotOfProducts(String[] depNameProductNames, String categoryName, CookieFilter cookieFilter, String depId){
        this.depNameProductNames = depNameProductNames;
        this.categoryName = categoryName;
        this.cookieFilter = cookieFilter;
        this.depId = depId;
    }
    @Test
    public void addALotOfPrducts(){
        for(int i=0; i<depNameProductNames.length; i++){
            for(int j=0; j< atributes.length; j++){
                for(int k=0; k< models.length; k++){
                    for(int p=0; p< productionYears.length; p++){
                        String itProductJsonBody = String.format("""
                {
                 "title": "%s",
                 "price": 780.99,
                  "description": "merge",
                  "thumbnail": "telefon mobil",
                  "category": "%s",
                  "departments": [{
                  "id": %s
                  }]
                }
                """, depNameProductNames[i] + atributes[j] + " model " + models[k] + productionYears[p],categoryName, depId);

                        RestAssured
                                .given()
                                .filter(cookieFilter)
                                .baseUri(url + "save-new-product")
                                .contentType(ContentType.JSON)
                                .body(itProductJsonBody)
                                .when()
                                .post()
                                .then()
                                .assertThat()
                                .statusCode(200);
                    }
                }
            }
        }
    }
}
