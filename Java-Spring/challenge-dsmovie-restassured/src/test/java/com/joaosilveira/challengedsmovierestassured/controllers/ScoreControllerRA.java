package com.joaosilveira.challengedsmovierestassured.controllers;

import com.joaosilveira.challengedsmovierestassured.tests.TokenUtil;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ScoreControllerRA {

    private String clientUsername, clientPassword, adminUsername, adminPassword;
    private String adminToken, clientToken, invalidToken;

    private Map<String, Object> postScoreInstance;

    private Long notExistingId, existingId;

    @BeforeEach
    public void setUp() throws Exception {

        baseURI = "http://localhost:8080";

        notExistingId = 50L;

        clientUsername = "maria@gmail.com";
        clientPassword = "123456";
        adminUsername = "alex@gmail.com";
        adminPassword = "123456";

        clientToken = TokenUtil.obtainAccessToken(clientUsername, clientPassword);
        adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);

        postScoreInstance = new HashMap<>();
        postScoreInstance.put("movieId", notExistingId);
        postScoreInstance.put("score", 4);

    }

    @Test
    public void saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist() throws Exception {

        JSONObject newScore = new JSONObject(postScoreInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(newScore)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .put("/scores")
        .then()
                .statusCode(404);



    }

    @Test
    public void saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId() throws Exception {

        postScoreInstance.put("movieId", null);
        JSONObject newScore = new JSONObject(postScoreInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(newScore)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put("/scores")
                .then()
                .statusCode(422);

    }

    @Test
    public void saveScoreShouldReturnUnprocessableEntityWhenScoreIsLessThanZero() throws Exception {

        postScoreInstance.put("score", -2);
        JSONObject newScore = new JSONObject(postScoreInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(newScore)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put("/scores")
                .then()
                .statusCode(422);

    }
}