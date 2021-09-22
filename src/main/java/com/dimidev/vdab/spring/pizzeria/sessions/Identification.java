package com.dimidev.vdab.spring.pizzeria.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Component
@SessionScope
public class Identification implements Serializable {

// MEMBER VARS

    private static final long serialVersionUID = 1L;

    @Email
    private String emailAddress;

// CONSTRUCTORS


// GETTERS & SETTERS

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


// METHODS


// OVERRIDDEN METHODS

}

