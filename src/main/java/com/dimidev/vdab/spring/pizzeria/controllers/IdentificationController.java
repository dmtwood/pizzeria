package com.dimidev.vdab.spring.pizzeria.controllers;

import com.dimidev.vdab.spring.pizzeria.sessions.Identification;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.Serializable;

@Controller
@RequestMapping("/identification")
public class IdentificationController {

// MEMBER VARS

    private final Identification identification;


// CONSTRUCTORS

    public IdentificationController(Identification identification) {
        this.identification = identification;
    }


// METHODS

    public @GetMapping
    ModelAndView identification(){
        return new ModelAndView("identification", "identification", identification);
    }

    public @PostMapping
    String identification(@Valid Identification identification, Errors errors){
        if (errors.hasErrors())
            return "identification";
        this.identification.setEmailAddress(identification.getEmailAddress());
        return "redirect:/";
    }

// OVERRIDDEN METHODS

}





























