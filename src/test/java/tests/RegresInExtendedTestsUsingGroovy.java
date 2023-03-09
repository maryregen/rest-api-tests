package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.RegresInSpec.*;
import static org.hamcrest.CoreMatchers.*;

public class RegresInExtendedTestsUsingGroovy {

    @DisplayName("Check users page status using Groovy")
    @Test
    void checkUsersPageStatusUsingGroovy() {
        step("Check users page status", () -> {
            Response response = given(requestSpec)
                    .when()
                    .get("?page=2")
                    .then()
                    .spec(responseSpecCode200)
                    .extract().response();

            assert response.statusCode() == 200;
        });
    }

    @DisplayName("Check users information using Groovy")
    @Test
    void checkInformationUsingGroovy() {
        step("Check users page status", () -> {
            given(requestSpec)
                    .when()
                    .get("?page=2")
                    .then()
                    .spec(responseSpecCode200)
                    .body("data.find{it.id == 7}.first_name", is("Michael"))
                    .body("data.findAll{it.id > 7}.flatten().email", hasItem("rachel.howell@reqres.in"));
        });
    }

    @DisplayName("Check user id using Groovy")
    @Test
    void checkUserIdUsingGroovy() {
        step("Check users page status", () -> {
            Response response = given(requestSpec)
                    .when()
                    .get("?page=2")
                    .then()
                    .spec(responseSpecCode200)
                    .extract().response();

            JsonPath jsonPath = response.jsonPath();
            List <Integer> ids = jsonPath.get("data.id");
            assertTrue(ids.contains(7));

        });
    }




}