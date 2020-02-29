package ru.textanalysis.tawt.rest.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "Welcome to TAWT REST API <br>" +
                "<a href='/tawt-rest-api/swagger-ui.html'>API in swagger</a>";
        //todo переделать в html страничку
    }
}

