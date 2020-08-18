package org.m88i.cloud.rest.functions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.m88i.cloud.rest.functions.domain.Message;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class ClientResourceTest {

    @Test
    void getMessage() throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();

        given()
                .when()
                .body(mapper.writeValueAsString(new Message("Hola Mundo!")))
                .contentType(MediaType.APPLICATION_JSON)
                .post("/client")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(containsString("received"));
    }
}