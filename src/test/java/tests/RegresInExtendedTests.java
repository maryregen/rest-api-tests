package tests;

import model.lombok.RequestBodyLombokModel;
import model.lombok.CreateResponseLombokModel;
import model.lombok.UpdateResponseLombokModel;
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
            RequestBodyLombokModel checkNameBody = new RequestBodyLombokModel();
            checkNameBody.setName("morpheus");
            checkNameBody.setJob("leader");


            CreateResponseLombokModel response = given(requestSpec)
                    .body(checkNameBody)
                    .when()
                    .post()
                    .then()
                    .spec(responseSpecCode201)
                    .extract().as(CreateResponseLombokModel.class);

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
            RequestBodyLombokModel checkNameBody = new RequestBodyLombokModel();
            checkNameBody.setName("john");
            checkNameBody.setJob("leader");

            UpdateResponseLombokModel response = given(requestSpec)
                    .body(checkNameBody)
                    .when()
                    .put("/2")
                    .then()
                    .spec(responseSpecCode200)
                    .extract().as(UpdateResponseLombokModel.class);

            assertThat(response.getName()).isEqualTo("john");
        });
    }

    @Tag("Jenkins")
    @DisplayName("Set user's new name using method PATCH")
    @Test
    void setUserNewNameUsingPatch() {
        step("Set user's new name using method PATCH", () -> {
            RequestBodyLombokModel checkNameBody = new RequestBodyLombokModel();
            checkNameBody.setName("michael");
            checkNameBody.setJob("leader");

            UpdateResponseLombokModel response = given(requestSpec)
                    .body(checkNameBody)
                    .when()
                    .patch("/2")
                    .then()
                    .spec(responseSpecCode200)
                    .extract().as(UpdateResponseLombokModel.class);

            assertThat(response.getName()).isEqualTo("michael");
        });
    }
}