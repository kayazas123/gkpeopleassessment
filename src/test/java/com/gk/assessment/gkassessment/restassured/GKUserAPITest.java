package com.gk.assessment.gkassessment.restassured;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AYAZ on 16/04/2018.
 */
public class GKUserAPITest {

    private static final Logger LOG = LoggerFactory.getLogger(GKUserAPITest.class);

    @Before
    public void init(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port=8080;
        RestAssured.baseURI="/api";
    }

    @Test
    public void getAllUsers(){
        /*Response response = given()
            .when()
            .get("/users");
        LOG.info(response.body().prettyPrint());

        given().when().get("/users").then().statusCode(200);*/

    }

    @Test
    public void getUser(){
        /*given()
            .when()
            .get("/users/user/1")
            .then()
            .statusCode(200);*/
    }

}
