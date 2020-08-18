package org.m88i.cloud.rest.functions;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.ProducerTemplate;
import org.m88i.cloud.rest.functions.domain.Ack;
import org.m88i.cloud.rest.functions.domain.Message;

@Path("")
public class RestCallersResource {

    private final ObjectMapper mapper = new ObjectMapper();
    @Inject
    ProducerTemplate producerTemplate;

    @Path("/get/string")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Ack callRestClientCallerAsString(@QueryParam("message") String message) throws JsonProcessingException {
        return producerTemplate.requestBody("direct:restGetCall", " { \"message\": \"" + message + "\" } ", Ack.class);
    }

    @Path("/get/object")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Ack callRestClientCallerAsObject(@QueryParam("message") String message) throws JsonProcessingException {
        return producerTemplate.requestBody("direct:restGetCall", new Message(message), Ack.class);
    }
}
