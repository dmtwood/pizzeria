package com.dimidev.vdab.spring.pizzeria.services;

import com.dimidev.vdab.spring.pizzeria.restclients.CurrencyRateClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EuroServiceTest {

// MEMBER VARS

    @Mock
    private CurrencyRateClient currencyRateClient;
    private DefaultEuroService defaultEuroService;
    @BeforeEach
    void beforeEach(){
        defaultEuroService = new DefaultEuroService(currencyRateClient);
    }

// TEST METHODS

     @Test
    void eurToDollar() {
        when( currencyRateClient.getDollarRating() )
                .thenReturn( BigDecimal.valueOf(1.5) );

         assertThat(defaultEuroService.euroToDollar(
                 BigDecimal.valueOf(2) )
         ).isEqualByComparingTo(
                 BigDecimal.valueOf(3)
         );

        verify(currencyRateClient).getDollarRating();
    }

}
