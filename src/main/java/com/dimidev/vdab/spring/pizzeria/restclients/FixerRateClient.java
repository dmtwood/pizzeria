package com.dimidev.vdab.spring.pizzeria.restclients;

import com.dimidev.vdab.spring.pizzeria.exceptions.CurrencyRateConvertorException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class FixerRateClient implements CurrencyRateClient {

// MEMBER VARS

    private static final Pattern JSONPROP_QUOTE_USD_QUOTE_IS = Pattern.compile(".*\"USD\":");

    private final URL restApiUrl;

// CONSTRUCTORS

    public FixerRateClient() {
        try {
            // free plan, no https included
            restApiUrl = new URL("http://data.fixer.io/api/latest?access_key=5f9fa72f4ec711aa491d2c15dfb7710e&symbols=USD");
        } catch (MalformedURLException e) {
            throw new CurrencyRateConvertorException("url to fixer doesn't get valid data.");
        }
    }

// GETTERS ( & SETTERS IF MUTABLE)


// METHODS

// OVERRIDDEN METHODS


    @Override
    public BigDecimal getDollarRating() {
        try (
                Scanner scanner = new Scanner(restApiUrl.openStream())
        ) {
            // strip the property part of the JSON -> { "USD":
            scanner.skip(JSONPROP_QUOTE_USD_QUOTE_IS);

            // strip the last } of the JSON
            scanner.useDelimiter("}");

            // convert the value part of the JSON to a BigDecimal
            BigDecimal dollarRating = new BigDecimal(scanner.next());

            return dollarRating;

        } catch (IOException | NumberFormatException ex) {
            throw new CurrencyRateConvertorException("Can't get Fixer Rate", ex);
        }
    }
}
