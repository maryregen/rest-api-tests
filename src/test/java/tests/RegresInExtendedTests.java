package tests;

import model.lombok.CheckNameRequestBodyLombokModel;
import model.lombok.CheckNameCreateResponseLombokModel;
import model.lombok.CheckNameUpdateResponseLombokModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import static io.restassured.RestAssured.given;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.RegresInSpec.*;

public class RegresInExtendedTests {

    @Tag("Jenkins")
    @DisplayName("Check users page status")
    @Test
    void checkUsersPageStatus() {
        step("Check users page status", () -> {
            given(requestSpec)
                    .when()
                    .get("?page=2")
                    .then()
                    .spec(responseSpecCode200);
        });
    }

    @Tag("Jenkins")
    @DisplayName("Check name of user")
    @Test
    void checkNameOfNewUser() {
        step("Check name of user", () -> {
            CheckNameRequestBodyLombokModel checkNameBody = new CheckNameRequestBodyLombokModel();
            checkNameBody.setName("morpheus");
            checkNameBody.setJob("leader");


            CheckNameCreateResponseLombokModel response = given(requestSpec)
                    .body(checkNameBody)
                    .when()
                    .post()
                    .then()
                    .spec(responseSpecCode201)
                    .extract().as(CheckNameCreateResponseLombokModel.class);

            assertThat(response.getName()).isEqualTo("morpheus");
        });
    }

    @Tag("Jenkins")
    @DisplayName("Get user information")
    @Test
    void getUser() {
        step("Get user information", () -> {
            given(requestSpec)
                    .when()
                    .get("/2")
                    .then()
                    .spec(responseSpecCode200);
        });
    }

    @Tag("Jenkins")
    @DisplayName("Set user's new name using method PUT")
    @Test
    void setUserNewNameUsingPut() {
        step("Set user's new name using method PUT", () -> {
            CheckNameRequestBodyLombokModel checkNameBody = new CheckNameRequestBodyLombokModel();
            checkNameBody.setName("john");
            checkNameBody.setJob("leader");

            CheckNameUpdateResponseLombokModel response = given(requestSpec)
                    .body(checkNameBody)
                    .when()
                    .put("/2")
                    .then()
                    .spec(responseSpecCode200)
                    .extract().as(CheckNameUpdateResponseLombokModel.class);

            assertThat(response.getName()).isEqualTo("john");
        });
    }

    @Tag("Jenkins")
    @DisplayName("Set user's new name using method PATCH")
    @Test
    void setUserNewNameUsingPatch() {
        step("Set user's new name using method PATCH", () -> {
            CheckNameRequestBodyLombokModel checkNameBody = new CheckNameRequestBodyLombokModel();
            checkNameBody.setName("michael");
            checkNameBody.setJob("leader");

            CheckNameUpdateResponseLombokModel response = given(requestSpec)
                    .body(checkNameBody)
                    .when()
                    .patch("/2")
                    .then()
                    .spec(responseSpecCode200)
                    .extract().as(CheckNameUpdateResponseLombokModel.class);

            assertThat(response.getName()).isEqualTo("michael");
        });
    }
}