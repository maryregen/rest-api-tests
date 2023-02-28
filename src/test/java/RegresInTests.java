import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class RegresInTests {
    @Test
    void checkUsersPageStatus() {
        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    void checkNameOfNewUser() {
        String request_body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        given()
                .log().all()
                .contentType(JSON)
                .body(request_body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", equalTo("morpheus"));
    }

    @Test
    void getUser() {
        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    void setUserNewNameUsingPut() {
        String request_body = "{ \"name\": \"john\", \"job\": \"leader\" }";
        given()
                .log().all()
                .contentType(JSON)
                .body(request_body)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("john"));
    }

    @Test
    void setUserNewNameUsingPatch() {
        String request_body = "{ \"name\": \"michael\", \"job\": \"leader\" }";
        given()
                .log().all()
                .contentType(JSON)
                .body(request_body)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("michael"));
    }
}