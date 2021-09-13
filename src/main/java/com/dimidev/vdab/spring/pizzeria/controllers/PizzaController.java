package com.dimidev.vdab.spring.pizzeria.controllers;

import com.dimidev.vdab.spring.pizzeria.domain.Pizza;
import com.dimidev.vdab.spring.pizzeria.exceptions.CurrencyRateConvertorException;
import com.dimidev.vdab.spring.pizzeria.services.EuroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.*;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    // MEMBER VARS

    private final EuroService euroService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass() );

    private final Pizza[] pizzas = {
            new Pizza(1, "pollo", BigDecimal.valueOf(13), false),
            new Pizza(2, "funghi", BigDecimal.valueOf(15), false),
            new Pizza(3, "pepperoni", BigDecimal.valueOf(14), true),
            new Pizza(4, "veggie", BigDecimal.valueOf(12), false),
            new Pizza(5, "pizza papi", BigDecimal.valueOf(17), true),
    };

// CONSTRUCTORS

    public PizzaController(EuroService euroService) {
        this.euroService = euroService;
    }


// GETTERS ( & SETTERS IF MUTABLE)


    // METHODS
    @GetMapping
    public ModelAndView pizzas(
            @CookieValue(name = "colorCookie", required = false) String color
    ) {
        ModelAndView pizzasView = new ModelAndView("pizzas", "pizzas", pizzas);
        pizzasView.addObject("color", color);
        return pizzasView;
    }


    @GetMapping("{id}")
    public ModelAndView pizza(
            @PathVariable long id,
            @CookieValue(name = "colorCookie", required = false) String color
    ) {
        ModelAndView pizzaDetailView = new ModelAndView("pizza");
        pizzaDetailView.addObject("color", color);
        Arrays.stream(pizzas)
                .filter(pizza -> pizza.getId() == id)
                .findFirst()
                .ifPresent( pizza -> {
                            pizzaDetailView.addObject("pizza", pizza);
                            try {
                                pizzaDetailView.addObject("priceInDollar", euroService.euroToDollar(pizza.getPrice()));
                            } catch (CurrencyRateConvertorException ex) {
                                logger.error("USD rates can't be fetched");
                            }
                        } );
        return pizzaDetailView;
    }


    @GetMapping("/prices")
    public ModelAndView prices() {
        return new ModelAndView(
                "prices",
                "prices",
                getPrices()
        );
    }


    @GetMapping("/prices/{price}")
    public ModelAndView pizzasWithPrice(
            @PathVariable BigDecimal price
    ) {
        return new ModelAndView(
                "prices",
                "pizzasWithPrice",
                pizzasByPrice(price)
        ).addObject(
                "prices",
                getPrices()
        );
    }


    private List<BigDecimal> getPrices() {
        return Arrays.stream(pizzas)
                .map(Pizza::getPrice)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private List<Pizza> pizzasByPrice(BigDecimal price) {
        return Arrays.stream(pizzas)
                .filter(pizza -> pizza.getPrice().compareTo(price) == 0)
                .collect(Collectors.toList());
    }


// OVERRIDDEN METHODS

}


















