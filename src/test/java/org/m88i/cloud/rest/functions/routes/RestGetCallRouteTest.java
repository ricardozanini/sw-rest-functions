package org.m88i.cloud.rest.functions.routes;

import javax.ws.rs.core.Response;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class RestGetCallRouteTest {

    @Test
    void testRouteWithStringPayload() {
        given().get("/get/string?message=Hello World!")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(containsString("received"));
    }

    @Test
    void testRouteWithObjectPayload() {
        given().get("/get/object?message=Hello World!")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(containsString("received"));
    }
}