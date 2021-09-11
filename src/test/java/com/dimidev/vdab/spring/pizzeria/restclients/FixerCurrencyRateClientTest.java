package com.dimidev.vdab.spring.pizzeria.restclients;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixerCurrencyRateClientTest {

// MEMBER VARS

    private FixerRateClient client;

// CONSTRUCTORS

    @BeforeEach
    void beforeEach() {
        client = new FixerRateClient();
    }


// TEST METHODS

    @Test
    void getDollarRating_ReturnsPositive() {
        Assertions
                .assertThat(client.getDollarRating()).isPositive();
    }

}

