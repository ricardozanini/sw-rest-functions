package org.m88i.cloud.rest.functions.routes;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.m88i.cloud.rest.functions.Constants;
import org.m88i.cloud.rest.functions.domain.Ack;

@ApplicationScoped
public class RestGetCallRoute extends RouteBuilder {

    @ConfigProperty(name = "kogito.producer.endpoint", defaultValue = Constants.DEFAULT_CLIENT_ENDPOINT)
    String producerEndpoint;

    @Override
    public void configure() {
        final JsonDataFormat jsonDataFormat = new JsonDataFormat();
        jsonDataFormat.setUnmarshalType(Ack.class);
        jsonDataFormat.setLibrary(JsonLibrary.Jackson);

        from("direct:restGetCall")
                .routeId("myRestCallFunction")
                .log(LoggingLevel.INFO, "Received message with body ${body}")
                .choice()
                    .when().body(f -> (f instanceof String))
                        .log(LoggingLevel.INFO, "Message body is a String, skipping JSON conversion")
                        .endChoice()
                    .otherwise()
                        .marshal().json(JsonLibrary.Jackson)
                        .log(LoggingLevel.INFO, "Converted body to JSON Object: ${body}")
                        .endChoice()
                .end()
                .log(LoggingLevel.INFO, "Sending message with ${body}")
                .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .to(producerEndpoint)
                .unmarshal(jsonDataFormat); //otherwise we can just convert the body to String and return JSON directly
    }

}
