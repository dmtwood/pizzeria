package com.dimidev.vdab.spring.pizzeria.services;

import com.dimidev.vdab.spring.pizzeria.restclients.CurrencyRateClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
class DefaultEuroService implements EuroService {

// MEMBER VARS

    private final CurrencyRateClient currencyRateClient;

// CONSTRUCTORS

    public DefaultEuroService(CurrencyRateClient currencyRateClient) {
        this.currencyRateClient = currencyRateClient;
    }

// GETTERS ( & SETTERS IF MUTABLE)


// METHODS


// OVERRIDDEN METHODS

    @Override
    public BigDecimal euroToDollar(BigDecimal amountOfEuros) {
        return amountOfEuros
                .multiply(currencyRateClient.getDollarRating())
                .setScale(2, RoundingMode.HALF_UP);
    }
}
