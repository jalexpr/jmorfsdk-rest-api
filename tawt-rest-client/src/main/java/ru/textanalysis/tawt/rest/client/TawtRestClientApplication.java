package ru.textanalysis.tawt.rest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.textanalysis.tawt.rest.client.services.GamaRemoteService;
import ru.textanalysis.tawt.rest.client.services.GraphematicParserRemoteService;
import ru.textanalysis.tawt.rest.client.services.JMorfSdkRemoteService;
import ru.textanalysis.tawt.rest.client.services.RemoteServiceFactory;

@SpringBootApplication
public class TawtRestClientApplication {
    private static final Logger logger = LoggerFactory.getLogger(TawtRestClientApplication.class);

//    public static void main(String[] args) {
//        SpringApplication.run(TawtRestClientApplication.class, args);
//    }

    public static void main(String[] args) {
        try {
            RemoteServiceFactory factory = new RemoteServiceFactory("http://boberpul2.asuscomm.com", 8093);
            JMorfSdkRemoteService jMorfSdkRemoteService = factory.getJMorfSdkRemoteService();
            GraphematicParserRemoteService graphematicParserRemoteService = factory.getGraphematicParserRemoteService();
            GamaRemoteService gamaRemoteService = factory.getGamaRemoteService();

            System.out.println(jMorfSdkRemoteService.getRefOmoFormList("село").getResult());
            System.out.println(graphematicParserRemoteService.parserSentence("я ходил гулять").getResult());
            System.out.println(gamaRemoteService.getMorphSentence("я ходил гулять").getResult());
        } catch (Exception ex) {
            logger.warn("Ошибка!", ex);
        }
    }
}
