package com.RSstajyer;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("Rest Assured POC - Example Tests with Stajyer")
@Feature("Performing different API Tests using Rest-Assured")
public class TestApiDogan {

    @Test
    @Description("Example Test for executing GET request using rest assured specification - İki sayının çarpımı Dogan_Dagistanli")
    @Severity(SeverityLevel.MINOR)
    @Story ("Performing a simple test using Rest-Assured")

    public static void apiTesting () {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        int a = 3;
        int b = 5;
        int expected = a * b;

        String jsonBody = "{ \"a\": " + a + ", \"b\": " + b + " }";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/posts")  // jsonplaceholder endpoint'i
                .then()
                .statusCode(201)
                .extract().response();

        int returnedA = response.jsonPath().getInt("a");
        int returnedB = response.jsonPath().getInt("b");

        int actualResult = returnedA * returnedB;

        Assert.assertEquals(actualResult, expected, "Çarpma sonucu yanlış");


       /* RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        String jsonBody = "{ \"a\": 0, \"b\": 5 }";

        given()
                .log().all()
                .body(jsonBody)
                .when()
                .post("/multiply")
                .then()
                .statusCode(200)
                .body("result", equalTo(0));*/


    }
}
