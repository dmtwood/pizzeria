package com.dimidev.vdab.spring.pizzeria.controllers;

import com.dimidev.vdab.spring.pizzeria.domain.Pizza;
import com.dimidev.vdab.spring.pizzeria.exceptions.CurrencyRateConvertorException;
import com.dimidev.vdab.spring.pizzeria.forms.PriceRangeForm;
import com.dimidev.vdab.spring.pizzeria.services.EuroService;
import com.dimidev.vdab.spring.pizzeria.services.PizzaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    // MEMBER VARS

    private final EuroService euroService;
    private final PizzaService pizzaService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

/*    private final Pizza[] pizzas = {
            new Pizza(1, "pollo", BigDecimal.valueOf(13), false),
            new Pizza(2, "funghi", BigDecimal.valueOf(15), false),
            new Pizza(3, "pepperoni", BigDecimal.valueOf(14), true),
            new Pizza(4, "veggie", BigDecimal.valueOf(12), false),
            new Pizza(5, "pizza papi", BigDecimal.valueOf(17), true),
    };*/

// CONSTRUCTORS

    public PizzaController(EuroService euroService, PizzaService pizzaService) {
        this.euroService = euroService;
        this.pizzaService = pizzaService;
    }


// GETTERS ( & SETTERS IF MUTABLE)


    // METHODS
    @GetMapping
    public ModelAndView pizzas(
            @CookieValue(name = "colorCookie", required = false) String color
    ) {
        ModelAndView pizzasView = new ModelAndView("pizzas", "pizzas", pizzaService.findAll());
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

/*        Arrays.stream(pizzas)
                .filter(pizza -> pizza.getId() == id)
                .findFirst()
                .ifPresent( pizza -> {
                    pizzaDetailView.addObject("pizza", pizza);
                    try {
                        pizzaDetailView.addObject("priceInDollar", euroService.euroToDollar(pizza.getPrice()));
                    } catch (CurrencyRateConvertorException ex) {
                        logger.error("USD rates can't be fetched");
                    }
                } );*/

        pizzaService.findById(id).ifPresent(
                pizza -> {
                    pizzaDetailView.addObject(pizza);
                    try {
                        pizzaDetailView.addObject("priceInDollar", euroService.euroToDollar(pizza.getPrice()));
                    } catch (CurrencyRateConvertorException ex) {
                        logger.error("USD rates can't be fetched");
                    }
                }
        );
        return pizzaDetailView;
    }


    @GetMapping("/prices")
    public ModelAndView prices() {
        return new ModelAndView(
                "prices",
                "prices",
                // getPrices()
                pizzaService.findUniquePrices()
        );
    }


    @GetMapping("/prices/{price}")
    public ModelAndView pizzasWithPrice(
            @PathVariable BigDecimal price
    ) {
        return new ModelAndView(
                "prices",
                "pizzasWithPrice",
                // pizzasByPrice(price)
                pizzaService.findByPrice(price)
        ).addObject(
                "prices",
                // getPrices()
                pizzaService.findUniquePrices()
        );
    }


    @GetMapping("/pricerange/form")
    public ModelAndView priceRangeForm() {
        return new ModelAndView("pricerange")
                .addObject(
                        "priceRangeForm",
                        new PriceRangeForm(null, null)
                );
    }

    @GetMapping("/pricerange")
    public ModelAndView pricerange(
            @Valid PriceRangeForm priceRangeForm,
            Errors errors
    ) {
        ModelAndView pricerangeView = new ModelAndView("pricerange");
        if (errors.hasErrors())
            return pricerangeView;
        return pricerangeView.addObject(
                "pizzasInPricerange",
                pizzaService.findByPriceBetween(
                        priceRangeForm.getMinPrice(),
                        priceRangeForm.getMaxPrice()
                )
        );
    }

    // returns a Pizza Object as a form to the addPizza view
    @GetMapping("/add/form")
    public ModelAndView addForm(){
        ModelAndView addPizzaView = new ModelAndView("add");
        addPizzaView.addObject("newPizza", new Pizza(0,"",null,false));
        return addPizzaView;
    }

    @PostMapping
    public String add(
            @Valid Pizza newPizza,
            Errors errors,
            RedirectAttributes redirectAttributes
    ){
        if (errors.hasErrors())
//            return new ModelAndView("add");
            return "add";
        long newId = pizzaService.create(newPizza);
        redirectAttributes.addAttribute("added", newId);
//        return new ModelAndView("pizzas", "pizzas", pizzaService.findAll());
        return "redirect:/pizzas";
    }


/*    private List<BigDecimal> getPrices() {
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
    }*/

}


















