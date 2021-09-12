package com.dimidev.vdab.spring.pizzeria.services;

import com.dimidev.vdab.spring.pizzeria.exceptions.CurrencyRateConvertorException;
import com.dimidev.vdab.spring.pizzeria.restclients.CurrencyRateClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
class DefaultEuroService implements EuroService {

// MEMBER VARS

    private final CurrencyRateClient[] currencyRateClients;

// CONSTRUCTORS

    public DefaultEuroService(CurrencyRateClient[] currencyRateClients) {
        this.currencyRateClients = currencyRateClients;
    }

// GETTERS ( & SETTERS IF MUTABLE)


// METHODS


// OVERRIDDEN METHODS

    @Override
    public BigDecimal euroToDollar(BigDecimal amountOfEuros) {
        Exception lastException = null;
        for (CurrencyRateClient currencyRateClient : currencyRateClients) {
            try {
                return amountOfEuros
                        .multiply(currencyRateClient.getDollarRating())
                        .setScale(2, RoundingMode.HALF_UP);
            } catch (CurrencyRateConvertorException ex) {
                lastException = ex;
            }
        }
        throw new CurrencyRateConvertorException("Can't get any USD rate from ECB nor Fixer rest client", lastException);
    }
}
