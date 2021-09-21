package com.dimidev.vdab.spring.pizzeria.restclients;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import(ECBRateClient.class)
@PropertySource("application.properties")
class ECBCurrencyRateClientTest {

    private final ECBRateClient ecbRateClient;

//    @BeforeEach
//    void beforeEach() {
//        ecbRateClient = new ECBRateClient();
//    }


    public ECBCurrencyRateClientTest(ECBRateClient ecbRateClient) {
        this.ecbRateClient = ecbRateClient;
    }

    @Test
    void getDollarRating_ReturnsPositive() {
        assertThat( ecbRateClient.getDollarRating() ).isPositive();
    }
}