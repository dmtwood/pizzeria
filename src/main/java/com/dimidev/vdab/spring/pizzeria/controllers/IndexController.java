package com.dimidev.vdab.spring.pizzeria.controllers;

import com.sun.source.tree.NewArrayTree;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;

//@RestController -> when hard coding html, not using thymeleaf
@Controller
@RequestMapping("/")
class IndexController {

    @GetMapping
    public ModelAndView index() {
        int currHour = LocalTime.now().getHour();
        if
        (currHour < 12) return new ModelAndView("index", "indexMessage", "morning");
        else if
        (currHour > 17) return new ModelAndView("index", "indexMessage", "evening");
        else
            return new ModelAndView("index", "indexMessage", "afternoon");
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
