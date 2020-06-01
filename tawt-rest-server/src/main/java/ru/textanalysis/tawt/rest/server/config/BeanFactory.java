package ru.textanalysis.tawt.rest.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.textanalysis.tawt.gama.main.Gama;
import ru.textanalysis.tawt.graphematic.parser.text.GParserImpl;
import ru.textanalysis.tawt.graphematic.parser.text.GraphematicParser;
import ru.textanalysis.tawt.jmorfsdk.JMorfSdk;
import ru.textanalysis.tawt.jmorfsdk.loader.JMorfSdkFactory;
import ru.textanalysis.tawt.sp.api.SyntaxParser;

@Component
public class BeanFactory {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GraphematicParser graphematicParser() {
        return new GParserImpl();
    }

    @Bean
    public JMorfSdk jMorfSdk() {
        return JMorfSdkFactory.loadFullLibrary(false);
    }

    @Bean
    public Gama gama() {
        Gama gama = new Gama();
        gama.init();
        return gama;
    }

    @Bean
    public SyntaxParser syntaxParser() {
        SyntaxParser sp = new SyntaxParser();
        sp.init();
        return sp;
    }
}
