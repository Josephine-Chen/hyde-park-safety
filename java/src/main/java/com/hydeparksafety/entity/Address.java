package com.hydeparksafety.entity;

/**
 * Created by HSong on 11/11/2017.
 */
public class Address {
    String street;
    String locationName;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
