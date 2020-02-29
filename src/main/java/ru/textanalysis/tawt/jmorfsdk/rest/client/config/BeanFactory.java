package ru.textanalysis.tawt.jmorfsdk.rest.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.textanalysis.common.rest.services.RestClientService;

@Component
public class BeanFactory {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public RestClientService restClientService(RestTemplate restTemplate) {
        return new RestClientService(restTemplate);
    }
}
