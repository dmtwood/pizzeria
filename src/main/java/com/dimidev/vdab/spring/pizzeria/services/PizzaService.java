package com.dimidev.vdab.spring.pizzeria.services;

import com.dimidev.vdab.spring.pizzeria.domain.Pizza;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public interface PizzaService {

// MEMBER VARS


// METHODS

    long create(Pizza pizza);

    void update(Pizza pizza);

    void delete(long id);

    List<Pizza> findAll();

    Optional<Pizza> findById(long id);

    List<Pizza> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    long findNrOfPizzas();

    List<BigDecimal> findUniquePrices();

    List<Pizza> findByPrice(BigDecimal wantedPrice);
}
