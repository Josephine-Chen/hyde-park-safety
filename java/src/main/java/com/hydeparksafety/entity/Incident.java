package com.hydeparksafety.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by HSong on 11/11/2017.
 */
public class Incident {
    String[] incident;
    Address address;
    @JsonFormat(pattern="MM/dd/yy hh:mm a")
    Date reported;
    String occurred;
    String comment;
    boolean isClosed;
    String ucpdiNumber;

    public String[] getIncident() {
        return incident;
    }

    public Incident setIncident(String[] incident) {
        this.incident = incident;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Incident setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Date getReported() {
        return reported;
    }

    public Incident setReported(Date reported) {
        this.reported = reported;
        return this;
    }

    public String getOccurred() {
        return occurred;
    }

    public Incident setOccurred(String occurred) {
        this.occurred = occurred;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Incident setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public Incident setClosed(boolean closed) {
        isClosed = closed;
        return this;
    }

    public String getUcpdiNumber() {
        return ucpdiNumber;
    }

    public Incident setUcpdiNumber(String ucpdiNumber) {
        this.ucpdiNumber = ucpdiNumber;
        return this;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "incident=" + Arrays.toString(incident) +
                ", address=" + address +
                ", reported=" + reported +
                ", occurred=" + occurred +
                ", comment='" + comment + '\'' +
                ", isClosed=" + isClosed +
                ", ucpdiNumber='" + ucpdiNumber + '\'' +
                '}';
    }
}
