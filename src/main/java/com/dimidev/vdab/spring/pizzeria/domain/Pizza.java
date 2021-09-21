package com.dimidev.vdab.spring.pizzeria.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class Pizza {

// MEMBER VARS

    private final long id;

    @NotBlank
    private final String name;

    @NotNull @PositiveOrZero
    private final BigDecimal price;

    private final boolean spicy;


// CONSTRUCTORS

    public Pizza(long id, String name, BigDecimal price, boolean spicy) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.spicy = spicy;
    }


// GETTERS ( & SETTERS IF MUTABLE)

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isSpicy() {
        return spicy;
    }

// METHODS


// OVERRIDDEN METHODS

}
