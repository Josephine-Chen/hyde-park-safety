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

    public void setIncident(String[] incident) {
        this.incident = incident;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getReported() {
        return reported;
    }

    public void setReported(Date reported) {
        this.reported = reported;
    }

    public String getOccurred() {
        return occurred;
    }

    public void setOccurred(String occurred) {
        this.occurred = occurred;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public String getUcpdiNumber() {
        return ucpdiNumber;
    }

    public void setUcpdiNumber(String ucpdiNumber) {
        this.ucpdiNumber = ucpdiNumber;
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
