package com.dimidev.vdab.spring.pizzeria.domain;

public class Address {
    private final String street;
    private final String nr;
    private final String areaCode;
    private final String town;


    public Address(String street, String nr, String areacode, String town) {
        this.street = street;
        this.nr = nr;
        this.areaCode = areacode;
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public String getNr() {
        return nr;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getTown() {
        return town;
    }
}
