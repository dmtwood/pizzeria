package com.dimidev.vdab.spring.pizzeria.sessions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyControllerAdvice {

// MEMBER VARS

    private final Identification identification;

// CONSTRUCTORS

    public MyControllerAdvice(Identification identification) {
        this.identification = identification;
    }


// METHODS

    @ModelAttribute
    void addDataToModel(Model model){
        model.addAttribute(identification);
    }

// OVERRIDDEN METHODS

}
