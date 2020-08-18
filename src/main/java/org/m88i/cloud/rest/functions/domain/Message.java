package org.m88i.cloud.rest.functions.domain;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Message {

    private String message;

    public Message() {

    }

    public Message(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
