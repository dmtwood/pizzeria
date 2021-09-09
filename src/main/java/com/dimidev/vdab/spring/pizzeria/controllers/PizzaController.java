package com.dimidev.vdab.spring.pizzeria.controllers;

import com.dimidev.vdab.spring.pizzeria.domain.Pizza;
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

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    // MEMBER VARS
    private final Pizza[] pizzas = {
            new Pizza( 1, "pollo", BigDecimal.valueOf(13), false),
            new Pizza( 2, "funghi", BigDecimal.valueOf(15), false),
            new Pizza( 3, "pepperoni", BigDecimal.valueOf(14), true),
            new Pizza( 4, "veggie", BigDecimal.valueOf(12), false),
            new Pizza( 5, "pizza papi", BigDecimal.valueOf(17), true),
    };

// CONSTRUCTORS


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
                .filter( pizza -> pizza.getId() == id )
                .findFirst()
                .ifPresent( pizza -> pizzaDetailView.addObject("pizza", pizza ) );
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

    private List<BigDecimal> getPrices() {
        return Arrays.stream( pizzas )
                .map( Pizza::getPrice )
                .distinct()
                .sorted()
                .collect( Collectors.toList() );
    }


    List<Pizza> pizzasByPrice( BigDecimal price ) {
        return Arrays.stream(pizzas)
                .filter(pizza -> pizza.getPrice().compareTo(price) == 0)
                .collect(Collectors.toList());
    }
    @GetMapping("/prices/{price}")
    public ModelAndView pizzasWithPrice(
            @PathVariable BigDecimal price
    ){
        return new ModelAndView("prices", "pizzasWithPrice", pizzasByPrice(price))
                .addObject("prices", getPrices());
    }

// OVERRIDDEN METHODS

}


















