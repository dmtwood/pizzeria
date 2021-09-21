package com.dimidev.vdab.spring.pizzeria.forms;

import java.math.BigDecimal;

public class PriceRangeForm {

// MEMBER VARS

    private final BigDecimal minPrice;
    private final BigDecimal maxPrice;

// CONSTRUCTORS

    public PriceRangeForm(BigDecimal minPrice, BigDecimal maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

// GETTERS ( & SETTERS IF MUTABLE)

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }


// METHODS


// OVERRIDDEN METHODS

}
