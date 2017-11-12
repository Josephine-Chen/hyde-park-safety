package com.hydeparksafety.entity;

import java.util.Arrays;

/**
 * Created by HSong on 12/11/2017.
 */
public class UserIncident extends Incident {
    String userId;

    public String getUserId() {
        return userId;
    }

    public UserIncident setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        return "UserIncident{" +
                "userId='" + userId + '\'' +
                ", incident=" + Arrays.toString(incident) +
                ", address=" + address +
                ", reported=" + reported +
                ", occurred='" + occurred + '\'' +
                ", comment='" + comment + '\'' +
                ", isClosed=" + isClosed +
                ", ucpdiNumber='" + ucpdiNumber + '\'' +
                '}';
    }
}
