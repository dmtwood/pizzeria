package com.dimidev.vdab.spring.pizzeria.domain;

import java.time.LocalDate;

public class Person {

    // MEMBER VARS
    private final String firstName;
    private final String lastName;
    private final int nrOfKids;
    private final boolean isMarried ;
    private final LocalDate dateOfBirth;
    private final Address address;


    //CONST
    public Person(String firstName, String lastName, int nrOfKids, boolean isMarried, LocalDate dateOfBirth, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nrOfKids = nrOfKids;
        this.isMarried = isMarried;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }


    // GET & SET
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNrOfKids() {
        return nrOfKids;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }


    // CLASS METHODS
    public String getFullName() {
        return firstName + " " + lastName;
    }


    // OVERRIDDEN METHODS
}
