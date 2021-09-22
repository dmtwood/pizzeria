package com.dimidev.vdab.spring.pizzeria.controllers;

import com.dimidev.vdab.spring.pizzeria.domain.Address;
import com.dimidev.vdab.spring.pizzeria.domain.Person;
import com.dimidev.vdab.spring.pizzeria.sessions.Identification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

//@RestController -> when hard coding html, not using thymeleaf
@Controller
@RequestMapping("/")
class IndexController {

    // MEMBER VARS

    private final Identification identification;
    private final AtomicInteger nrOfVisits = new AtomicInteger();


    // CONSTRUCTORS

    public IndexController(Identification identification) {
        this.identification = identification;
    }


    // METHODS

    private String getPartOfDay() {
        int currHour = LocalTime.now().getHour();
        if
        (currHour < 12) return "morning";
        else if
        (currHour > 17) return "evening";
        else
            return "afternoon";
    }

    @GetMapping
    public ModelAndView index(
            @CookieValue(name = "colorCookie", required = false) String color
    ) {
        ModelAndView indexView = new ModelAndView("index", "partOfTheDay", getPartOfDay() );
        indexView.addObject("color", color);
        indexView.addObject("nrOfVisits", nrOfVisits.incrementAndGet() );
        indexView.addObject("chef",
                new Person(
                    "Dimi", "Fromaggi", 1,true ,
                    LocalDate.of(1976,8, 5),
                    new Address("Kruishuisstraat", "66b2", "2300", "Turnhout")
        ));
//        //add the session -> moved to AdviceController to generalize scope
//        indexView.addObject(identification);
        return indexView;
    }

//    @GetMapping
//    public String index() {
//
//        // boiler plate method, no best practice (error prone)
//        StringBuilder indexHtml = new StringBuilder(
//                "<!DOCTYPE html>\n" +
//                        "<html lang=\"en\">\n" +
//                        "<head>\n" +
//                        "    <meta charset=\"UTF-8\">\n" +
//                        "    <title>Pizza Papa</title>\n" +
//                        "    <link rel=\"icon\" href=\"images/luigi.ico\" type=\"image/x-icon\">\n" +
//                        "    <link rel=\"stylesheet\" href=\"css/luigi.css\" type=\"text/css\">\n" +
//                        "</head>\n" +
//                        "<body>\n" +
//                        "   <img src=\"images/luigi.jpg\" alt=\"pizza\" class=\"fullwidth\"/>\n" +
//                        "   <h1> Pizza Luigi </h1>"
//        );
//
//        int currentHour = LocalTime.now().getHour();
//        if (currentHour < 12) {
//            indexHtml.append("Good morning.");
//        } else if (currentHour > 18) {
//            indexHtml.append("Good evening.");
//        } else {
//            indexHtml.append("Good afternoon.");
//        }
//
//        indexHtml.append(
//                "</body>\n" +
//                        "</html>"
//        );
//        return indexHtml.toString();
//    }

}
