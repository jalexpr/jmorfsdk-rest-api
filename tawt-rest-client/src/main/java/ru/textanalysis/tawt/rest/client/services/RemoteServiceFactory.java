package ru.textanalysis.tawt.rest.client.services;

import org.springframework.web.client.RestTemplate;
import ru.textanalysis.common.rest.services.RestClientService;
import ru.textanalysis.tawt.ms.internal.BuilderTransportBase;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.ms.internal.sp.BuilderTransportSP;
import ru.textanalysis.tawt.rest.client.config.Config;

public class RemoteServiceFactory {
    private final GraphematicParserRemoteService graphematicParserRemoteService;
    private final JMorfSdkRemoteService jMorfSdkRemoteService;
    private final GamaRemoteService gamaRemoteService;
    private final SyntaxParserRemoteService syntaxParserRemoteService;

    public RemoteServiceFactory(String address, Integer port) {
        Config config = new Config();
        config.setAddress(address);
        config.setPort(port);

        BuilderTransportBase builderTransportBase = new BuilderTransportBase();
        BuilderTransportRef builderTransportRef = new BuilderTransportRef();
        BuilderTransportSP builderTransportSP = new BuilderTransportSP();
        RestClientService restClientService = new RestClientService(new RestTemplate());

        this.graphematicParserRemoteService = new GraphematicParserRemoteService(restClientService, config);
        this.jMorfSdkRemoteService = new JMorfSdkRemoteService(restClientService, builderTransportBase, builderTransportRef, config);
        this.gamaRemoteService = new GamaRemoteService(restClientService, config);
        this.syntaxParserRemoteService = new SyntaxParserRemoteService(restClientService, builderTransportSP, config);
        //todo добавить проверку, что версия сервера совпадает с версией клиента
    }

    public GraphematicParserRemoteService getGraphematicParserRemoteService() {
        return graphematicParserRemoteService;
    }

    public JMorfSdkRemoteService getJMorfSdkRemoteService() {
        return jMorfSdkRemoteService;
    }

    public GamaRemoteService getGamaRemoteService() {
        return gamaRemoteService;
    }

    public SyntaxParserRemoteService getSyntaxParserRemoteService() {
        return syntaxParserRemoteService;
    }
}
