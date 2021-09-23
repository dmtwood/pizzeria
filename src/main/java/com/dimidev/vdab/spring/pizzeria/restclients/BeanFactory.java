package com.dimidev.vdab.spring.pizzeria.restclients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
public class BeanFactory {

// MEMBER VARS

    private final URL ecbRateURL;
    private final URL fixerRateURL;


// CONSTRUCTORS

    public BeanFactory(@Value("${ecbRateUrl}") URL ecbRateURL, @Value("${fixerRateUrl}") URL fixerRateURL) {
        this.ecbRateURL = ecbRateURL;
        this.fixerRateURL = fixerRateURL;
    }


// METHODS

    @Bean
    ECBRateClient ecbRateClient(){
        return new ECBRateClient(ecbRateURL);
    }

    @Bean
    FixerRateClient fixerRateClient(){
        return new FixerRateClient(fixerRateURL);
    }

}
