package com.dimidev.vdab.spring.pizzeria.controllers;

import com.dimidev.vdab.spring.pizzeria.domain.Pizza;
import com.dimidev.vdab.spring.pizzeria.services.PizzaService;
import com.dimidev.vdab.spring.pizzeria.sessions.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("order")
public class OrderController {

// MEMBER VARS

    private final Order order;
    private final PizzaService pizzaService;


// CONSTRUCTORS

    public OrderController(Order order, PizzaService pizzaService) {
        this.order = order;
        this.pizzaService = pizzaService;
    }


// METHODS

    @GetMapping
    public ModelAndView showOrder(){

        List<Pizza> allPizzas = pizzaService.findAll();

        ModelAndView orderView = new ModelAndView("order")
                .addObject("allPizzas", allPizzas);

        if (order.isFilled()){
            orderView.addObject("pizzasInOrder",
                    allPizzas.stream()
                            .filter(pizza -> order.contains(pizza.getId()))
                            .collect(Collectors.toList())
                    );
        }
        return orderView;
    }
     @PostMapping
     public String addToOrder(long id){
        order.add(id);
        return "redirect:/order";
     }

// OVERRIDDEN METHODS

}
