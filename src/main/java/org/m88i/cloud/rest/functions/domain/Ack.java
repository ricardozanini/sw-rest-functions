package org.m88i.cloud.rest.functions.domain;

import java.util.Date;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Ack {

    private Date received;

    public Ack() {
        this.received = new Date();
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
    }
}
