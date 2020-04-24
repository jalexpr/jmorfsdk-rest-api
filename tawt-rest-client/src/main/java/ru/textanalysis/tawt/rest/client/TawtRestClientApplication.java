package ru.textanalysis.tawt.rest.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.textanalysis.common.rest.services.RestClientService;
import ru.textanalysis.tawt.ms.internal.BuilderTransportBase;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
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
            GraphematicParserRemoteService graphematicParserRemoteService = new GraphematicParserRemoteService(restClientService);
            BuilderTransportBase builderTransport = new BuilderTransportBase();
            BuilderTransportRef builderTransportRef = new BuilderTransportRef();
            JMorfSdkRemoteService jMorfSdkRemoteService = new JMorfSdkRemoteService(restClientService, builderTransport, builderTransportRef);
            //GraphematicParserRemoteService graphematicParserRemoteService = new GraphematicParserRemoteService(restClientService);
            GamaRemoteService gamaRemoteService = new GamaRemoteService(restClientService, builderTransportRef);

            //System.out.println(jMorfSdkRemoteService.getAllCharacteristicsOfForm("дерево").getResult());
            System.out.println(jMorfSdkRemoteService.getRefOmoFormList("село").getResult());
            //System.out.println(gamaRemoteService.getMorphWord("селом").getResult());
            //System.out.println(gamaRemoteService.getMorphBearingPhrase("маленькая кошка").getResult());
        } catch (Exception ex) {
            logger.warn("Ошибка!", ex);
        }
    }
}
