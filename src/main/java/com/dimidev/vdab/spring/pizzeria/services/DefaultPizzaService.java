package com.dimidev.vdab.spring.pizzeria.services;

import com.dimidev.vdab.spring.pizzeria.domain.Pizza;
import com.dimidev.vdab.spring.pizzeria.repositories.PizzaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DefaultPizzaService implements PizzaService {

// MEMBER VARS

    private final PizzaRepository pizzaRepository;

// CONSTRUCTORS

    public DefaultPizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }


// METHODS

    @Override
    public long create(Pizza pizza) {
        return pizzaRepository.create(pizza);
    }

    @Override
    public void update(Pizza pizza) {
        pizzaRepository.update(pizza);
    }

    @Override
    public void delete(long id) {
        pizzaRepository.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Pizza> findById(long id) {
        return pizzaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pizza> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return pizzaRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Transactional(readOnly = true)
    @Override
    public long findNrOfPizzas() {
        return pizzaRepository.findPizzaCount();
    }

    @Transactional(readOnly = true)
    @Override
    public List<BigDecimal> findUniquePrices() {
        return pizzaRepository.findUniquePrices();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pizza> findByPrice(BigDecimal wantedPrice) {
        return pizzaRepository.findByPrice(wantedPrice);
    }


// OVERRIDDEN METHODS

}
