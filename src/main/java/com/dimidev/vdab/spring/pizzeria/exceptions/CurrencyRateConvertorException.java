package com.dimidev.vdab.spring.pizzeria.exceptions;

public class CurrencyRateConvertorException extends RuntimeException{

// MEMBER VARS

    private static final long SerialVersionUID = 1L;

// CONSTRUCTORS

    public CurrencyRateConvertorException(String message) {
        super(message);
    }

    public CurrencyRateConvertorException(String message, Exception originalEx) {
        super(message, originalEx);
    }

    // GETTERS ( & SETTERS IF MUTABLE)


// METHODS


// OVERRIDDEN METHODS

}
