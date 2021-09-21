package com.dimidev.vdab.spring.pizzeria.forms;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;


public class PriceRangeForm {

// MEMBER VARS

    @NotNull
    @PositiveOrZero
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
