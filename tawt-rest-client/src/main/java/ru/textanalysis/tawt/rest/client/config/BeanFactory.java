package ru.textanalysis.tawt.rest.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BeanFactory {
    @Lazy
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
