package ru.textanalysis.tawt.jmorfsdk.rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "Welcome to JMorfSdk Rest API <br>" +
                "<a href='/jmorfsdk-rest-api/swagger-ui.html'>API in swagger</a>";
        //todo переделать в html страничку
    }
}

