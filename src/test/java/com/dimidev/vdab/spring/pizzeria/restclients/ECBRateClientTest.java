package com.dimidev.vdab.spring.pizzeria.restclients;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ECBRateClientTest {

    private ECBRateClient ecbRateClient;

    @BeforeEach
    void beforeEach() {
        ecbRateClient = new ECBRateClient();
    }

    @Test
    void getDollarRating_ReturnsPositive() {
        assertThat( ecbRateClient.getDollarRating() ).isPositive();
    }
}