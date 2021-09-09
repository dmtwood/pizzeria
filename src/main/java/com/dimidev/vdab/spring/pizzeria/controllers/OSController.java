package com.dimidev.vdab.spring.pizzeria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("/os")
class OSController {

// MEMBER VARS

    private static final String[] OSs = { "Windows", "Linux", "Android", "Macintosh"};

// CONSTRUCTORS


// GETTERS ( & SETTERS IF MUTABLE)


// METHODS

    @GetMapping
    public ModelAndView os(
            @RequestHeader("User-agent") String userAgent,
            @CookieValue(name = "colorCookie", required = false) String color
    ) {
        ModelAndView osView = new ModelAndView("os");
        osView.addObject("color", color);
        Arrays.stream(OSs)
                .filter( userAgent::contains )
                .findFirst()
                .ifPresent( osString -> osView.addObject("osString", osString));
        return osView;
    }

// OVERRIDDEN METHODS

}
