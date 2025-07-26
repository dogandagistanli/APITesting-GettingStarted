package com.RSstajyer;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("Rest Assured POC - Example Tests with Stajyer")
@Feature("Performing different API Tests using Rest-Assured")
public class TestApi {

    @Test (priority = 1)
    @Description ("Example Test for executing GET request using rest assured specification - İki sayının çarpımı Dogan_Dagistanli")
    @Severity(SeverityLevel.MINOR)
    @Story ("Performing a simple test using Rest-Assured")
    public void postTesting () {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        int a = 3;
        int b = 5;
        int expected = a * b;

        String jsonBody = "{ \"yas\": " + a + ", \"dogum\": " + b + "}";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
               // .log().all()
                .when()
                .post("/posts")  // jsonplaceholder endpoint'i
                .then()
                .statusCode(201)
                .extract().response();

        System.out.println(response.asString());



        int returnedA = response.jsonPath().getInt("yas");
        int returnedB = response.jsonPath().getInt("dogum");
        int returnedID = response.jsonPath().getInt("id");

        int actualResult = returnedA * returnedB;

        Assert.assertEquals(actualResult, expected, "Çarpma sonucu yanlış");

        //System.out.println(response.asString() + returnedID);


        //get 101 ile bilgileri al ve topla

        /*RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

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

    @Test (dependsOnMethods = "postTesting", priority = 1)
    @Description ("Example Test for executing GET request using rest assured specification - İki sayının çarpımı Dogan_Dagistanli")
    @Severity(SeverityLevel.MINOR)
    @Story ("Performing a simple test using Rest-Assured")
    public void getTesting () {

        System.out.println("---------------------------------------------------------------------------------------------");

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response5 = given()
                .contentType("application/json")
                .log().all()
                .when()
                .put("/posts/100")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(100))
                .extract().response();
        System.out.println(response5.asString());




    }


    @Test ( priority = 2)
    @Description ("Example Test for executing GET request using rest assured specification - İki sayının çarpımı Dogan_Dagistanli")
    @Severity(SeverityLevel.MINOR)
    @Story ("Performing a simple test using Rest-Assured")
    public void putTesting () {

        System.out.println("---------------------------------------------------------------------------------------------");


        int a = 3;
        int b = 5;
        int expected = a * b;

        String jsonBody = "{ \"userId\": " + a + ", \"title\": " + b + "}";


        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response5 = given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
                .when()
                .put("/posts/100")
                .then()
                .statusCode(200)
                .assertThat()
                .body("userId", equalTo(a), "title" , equalTo(b))
                .log().status()
                .log().cookies()
                .log().everything()
                .extract().response();

        System.out.println(response5.asString());

    }


    @Test ( priority = 2)
    @Description ("Example Test for executing GET request using rest assured specification - İki sayının çarpımı Dogan_Dagistanli")
    @Severity(SeverityLevel.MINOR)
    @Story ("Performing a simple test using Rest-Assured")
    public void getTestingYoutube () {

        System.out.println("---------------------------------------------------------------------------------------------");

        //String jsonBody = "{ \"username\": " + "metin" + ", \"password\": " + "yesilbag" + "}";

        RestAssured.baseURI = "https://www.youtube.com/";

        Response response5 = given()
                .log().all()
                .when()
                .get("/@SkyNews")
                .then()
                .statusCode(200)
                .assertThat()
                .body("title", equalTo("(141) Sky News - YouTube"))
                .log().all()
                .extract().response();




        System.out.println(response5.asString());

    }


        @Test
        public void getTestingYoutube1() throws Exception {
            System.out.println("---------------------------------------------------------------------------------------------");

            // HTML sayfasını al
            Document doc = Jsoup.connect("https://www.youtube.com/@SkyNews").get();

            // <title> tag'ini al
            String title = doc.title();

            System.out.println("Page Title: " + title);

            // Doğrulama
            Assert.assertEquals(title, "Sky News - YouTube");
        }


}








