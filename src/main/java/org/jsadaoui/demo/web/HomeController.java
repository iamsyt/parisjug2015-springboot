package org.jsadaoui.demo.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HomeController {

    @RequestMapping(value = "/")
    public String home() {
        return "Hello, world.";
    }
}