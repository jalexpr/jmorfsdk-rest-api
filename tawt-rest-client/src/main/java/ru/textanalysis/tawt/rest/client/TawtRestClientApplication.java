package ru.textanalysis.tawt.rest.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.textanalysis.common.rest.services.RestClientService;
import ru.textanalysis.tawt.rest.client.config.BeanFactory;
import ru.textanalysis.tawt.rest.client.services.GamaRemoteService;
import ru.textanalysis.tawt.rest.client.services.GraphematicParserRemoteService;
import ru.textanalysis.tawt.rest.client.services.JMorfSdkRemoteService;

public class TawtRestClientApplication {
    static Logger logger = LoggerFactory.getLogger(TawtRestClientApplication.class);

    public static void main(String[] args) {
        try {
            BeanFactory beanFactory = new BeanFactory();
            RestClientService restClientService = new RestClientService(beanFactory.restTemplate());
            JMorfSdkRemoteService jMorfSdkRemoteService = new JMorfSdkRemoteService(restClientService);
            GraphematicParserRemoteService graphematicParserRemoteService = new GraphematicParserRemoteService(restClientService);
            GamaRemoteService gamaRemoteService = new GamaRemoteService(restClientService);

            System.out.println(jMorfSdkRemoteService.getAllCharacteristicsOfForm("дерево").getResult());
        } catch (Exception ex) {
            logger.warn("Ошибка!", ex);
        }
    }
}
