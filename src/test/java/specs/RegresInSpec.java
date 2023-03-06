package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.with;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class RegresInSpec {
    public static RequestSpecification requestSpec = with()
            .log().all()
            .contentType(ContentType.JSON)
            .baseUri("https://reqres.in")
            .basePath("/api/users")
            .filter(withCustomTemplates());

    public static ResponseSpecification responseSpecCode200 = new ResponseSpecBuilder()
        .log(STATUS)
        .log(BODY)
        .expectStatusCode(200)
        .build();

    public static ResponseSpecification responseSpecCode201 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .build();
}


