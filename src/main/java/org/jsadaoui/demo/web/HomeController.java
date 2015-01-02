package org.jsadaoui.demo.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "Hello ParisJUG!";
    }
}