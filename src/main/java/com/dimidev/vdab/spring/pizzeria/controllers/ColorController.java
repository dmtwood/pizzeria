package com.dimidev.vdab.spring.pizzeria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/colors")
public class ColorController {

// MEMBER VARS

    private static final int A_YEAR_IN_SECONDS = 31_536_000;

// CONSTRUCTORS


// GETTERS ( & SETTERS IF MUTABLE)


// METHODS

    @GetMapping
    public ModelAndView showView(
            @CookieValue(name = "colorCookie", required = false) String color
    ) {
        return new ModelAndView("colors", "colorCookie", color);
    }

    @GetMapping("/{color}")
    public ModelAndView color(
            @PathVariable String color, HttpServletResponse httpServletResponse
    ) {
        Cookie colorCookie = new Cookie("colorCookie", color);
        colorCookie.setMaxAge(A_YEAR_IN_SECONDS);
        colorCookie.setPath("/");
        httpServletResponse.addCookie(colorCookie);
        return new ModelAndView("colors");
    }

// OVERRIDDEN METHODS

}
