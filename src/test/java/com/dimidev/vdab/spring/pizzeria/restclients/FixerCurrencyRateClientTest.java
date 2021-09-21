package com.dimidev.vdab.spring.pizzeria.restclients;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(FixerRateClient.class)
@PropertySource("application.properties")
public class FixerCurrencyRateClientTest {

// MEMBER VARS

    private final FixerRateClient client;

// CONSTRUCTORS

//    @BeforeEach
//    void beforeEach() {
//        client = new FixerRateClient();
//    }

    public FixerCurrencyRateClientTest(FixerRateClient client) {
        this.client = client;
    }


// TEST METHODS

    @Test
    void getDollarRating_ReturnsPositive() {
        Assertions
                .assertThat(client.getDollarRating()).isPositive();
    }

}

