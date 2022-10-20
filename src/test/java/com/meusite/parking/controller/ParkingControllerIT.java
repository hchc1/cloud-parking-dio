package com.meusite.parking.controller;

import com.meusite.parking.controller.dto.ParkingCreateDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerIT extends AbstractContainerBase {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .header("Authorization", "Basic lPoasKjsa2Msnsa6neUSL")
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void whenCreateThenCheckItsCreated() {
        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("VERMELHO");
        createDTO.setLicense("IRD-4567");
        createDTO.setModel("ONIX");
        createDTO.setState("RR");

        RestAssured.given()
                .header("Authorization", "Basic lPoasKjsa2Msnsa6neUSL")
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("IRD-4567"))
                .body("color", Matchers.equalTo("VERMELHO"))
                .body("model", Matchers.equalTo("ONIX"))
                .body("state", Matchers.equalTo("RR"));
    }
}