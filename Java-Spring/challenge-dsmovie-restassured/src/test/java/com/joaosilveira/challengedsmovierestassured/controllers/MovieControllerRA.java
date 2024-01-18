package com.joaosilveira.challengedsmovierestassured.controllers;

import com.joaosilveira.challengedsmovierestassured.tests.TokenUtil;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class MovieControllerRA {

    private String clientUsername, clientPassword, adminUsername, adminPassword;
    private String adminToken, clientToken, invalidToken;
    private Long existingMovieId, nonExistingMovieId, dependentMovieId;
    private Map<String, Object> postMovie;


    @BeforeEach
    public void setUp() throws Exception {
        baseURI = "http://localhost:8080";

        existingMovieId = 5L;
        nonExistingMovieId = 1000L;
        dependentMovieId = 1L;

        clientUsername = "joaquim@gmail.com";
        clientPassword = "123456";
        adminUsername = "alex@gmail.com";
        adminPassword = "123456";

        clientToken = TokenUtil.obtainAccessToken(clientUsername, clientPassword);
        adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);
        invalidToken = adminToken + "xpto";

        postMovie = new HashMap<>();
        postMovie.put("title", "Test Movie");
        postMovie.put("score", 0.0f);
        postMovie.put("count", 0);
        postMovie.put("image", "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/jBJWaqoSCiARWtfV0GlqHrcdidd.jpg");

    }

    @Test
    public void findAllShouldReturnOkWhenMovieNoArgumentsGiven() {

        given()
                .get("/movies")
        .then()
                .statusCode(200);

    }

    @Test
    public void findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty() {

        given()
                .get("/movies?title=the")
                .then()
                .statusCode(200)
            .body("content[0].title", equalTo("The Witcher"));

    }

    @Test
    public void findByIdShouldReturnMovieWhenIdExists() {

        given()
                .get("/movies/{id}", existingMovieId)
        .then()
                .statusCode(200)
                .body("title", equalTo("Shang-Chi e a Lenda dos Dez Anéis"))
                .body("id", equalTo(5));

    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {

        given()
                .get("/movies/{id}", nonExistingMovieId)
                .then()
                .statusCode(404);

    }

    @Test
    public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle() throws JSONException {

        postMovie.put("title", "");
        JSONObject newMovie = new JSONObject(postMovie);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(newMovie)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/movies")
                .then()
                .statusCode(422);

    }

    @Test
    public void insertShouldReturnForbiddenWhenClientLogged() throws Exception {

        JSONObject newMovie = new JSONObject(postMovie);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + clientToken)
                .body(newMovie)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/movies")
                .then()
                .statusCode(403);

    }

    @Test
    public void insertShouldReturnUnauthorizedWhenInvalidToken() throws Exception {

        JSONObject newMovie = new JSONObject(postMovie);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + invalidToken)
                .body(newMovie)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/movies")
                .then()
                .statusCode(401);

    }
}