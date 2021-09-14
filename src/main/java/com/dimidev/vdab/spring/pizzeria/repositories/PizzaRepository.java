package com.dimidev.vdab.spring.pizzeria.repositories;

import com.dimidev.vdab.spring.pizzeria.domain.Pizza;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PizzaRepository {

// MEMBER VARS

// METHODS

    long create(Pizza pizza);

    void update(Pizza pizza);

    void delete(long id);

    Optional<Pizza> findById(long id);

    List<Pizza> findAll();

    List<Pizza> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    long findPizzaCount();

    List<BigDecimal> findUniquePrices();

    List<Pizza> findbyPrice(BigDecimal wantedPrice);

    List<Pizza> findByIds(Set<Long> ids);



// OVERRIDDEN METHODS

}
