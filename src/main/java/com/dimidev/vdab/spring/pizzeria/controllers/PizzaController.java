package com.dimidev.vdab.spring.pizzeria.controllers;

import com.dimidev.vdab.spring.pizzeria.domain.Pizza;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;

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
    public ModelAndView pizzas() {
        return new ModelAndView("pizzas", "pizzas", pizzas);
    }


    @GetMapping("{id}")
    public ModelAndView pizza(@PathVariable long id) {
        ModelAndView pizzaDetailView = new ModelAndView("pizza");
        Arrays.stream(pizzas)
                .filter( pizza -> pizza.getId() == id )
                .findFirst()
                .ifPresent( pizza -> pizzaDetailView.addObject("pizza", pizza ) );
        return pizzaDetailView;
    }


// OVERRIDDEN METHODS

}


















