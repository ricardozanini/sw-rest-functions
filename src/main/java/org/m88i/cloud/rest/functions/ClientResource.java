package org.m88i.cloud.rest.functions;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.m88i.cloud.rest.functions.domain.Ack;
import org.m88i.cloud.rest.functions.domain.Message;

/**
 * This is the client reply
 */
@Path("/client")
public class ClientResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getMessage(Message message) {
        if (message.getMessage() == null || message.getMessage().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(new Ack()).build();
    }
}
