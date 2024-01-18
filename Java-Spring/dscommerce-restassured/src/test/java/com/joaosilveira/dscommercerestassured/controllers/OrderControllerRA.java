package com.joaosilveira.dscommercerestassured.controllers;

import com.joaosilveira.dscommercerestassured.tests.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

public class OrderControllerRA {


    private Long existingOrderId, nonExistingOrderId, notSelfOrder;
    private String clientUsername, clientPassword, adminUsername, adminPassword;
    private String adminToken, clientToken, invalidToken;


    @BeforeEach
    public void setUp() throws Exception {

        existingOrderId = 1L;
        notSelfOrder = 2L;
        nonExistingOrderId = 200L;


        clientUsername = "maria@gmail.com";
        clientPassword = "123456";
        adminUsername = "alex@gmail.com";
        adminPassword = "123456";

        clientToken = TokenUtil.obtainAccessToken(clientUsername, clientPassword);
        adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);
        invalidToken = adminToken + "xpto";

    }

    @Test
    public void findByIdShouldReturnOrderWhenExistingIdWhenAdminLogged() {

        given()
                .header("Authorization", "Bearer " + adminToken)
                .get("/orders/{id}", existingOrderId)
        .then()
                .statusCode(200)
                .body("moment", equalTo("2022-07-25T13:00:00Z"))
                .body("status", equalTo("PAID"))
                .body("client.name", equalTo("Maria Brown"))
                .body("items[0].name", equalTo("The Lord of the Rings"));

    }

    @Test
    public void findByIdShouldReturnOrderWhenExistingIdWhenClientLoggedAndSelfOrder() {

        given()
                .header("Authorization", "Bearer " + clientToken)
                .get("/orders/{id}", existingOrderId)
        .then()
                .statusCode(200)
                .body("moment", equalTo("2022-07-25T13:00:00Z"))
                .body("status", equalTo("PAID"))
                .body("client.name", equalTo("Maria Brown"))
                .body("items[0].name", equalTo("The Lord of the Rings"))
                .body("client.id", equalTo(1));

    }


    @Test
    public void findByIdShouldReturnForbiddenWhenNotSelfOrder() {

        given()
                .header("Authorization", "Bearer " + clientToken)
                .get("/orders/{id}", notSelfOrder)
                .then()
                .statusCode(403);

    }

    @Test
    public void findByIdShouldReturnNotFoundWhenNotExistingIdAndAdminLogged() {

        given()
                .header("Authorization", "Bearer " + adminToken)
                .get("/orders/{id}", nonExistingOrderId)
                .then()
                .statusCode(404);

    }

    @Test
    public void findByIdShouldReturnNotFoundWhenNotExistingIdAndClientLogged() {

        given()
                .header("Authorization", "Bearer " + clientToken)
                .get("/orders/{id}", nonExistingOrderId)
                .then()
                .statusCode(404);

    }

    @Test
    public void findByIdShouldReturnUnauthorizedWhenNotLogged() {

        given()
                .header("Authorization", "Bearer " + invalidToken)
                .get("/orders/{id}", existingOrderId)
                .then()
                .statusCode(401);

    }

}
