package ru.textanalysis.tawt.rest.client.services;

import org.springframework.web.client.RestTemplate;
import ru.textanalysis.common.rest.services.RestClientService;
import ru.textanalysis.tawt.ms.internal.BuilderTransportBase;
import ru.textanalysis.tawt.ms.internal.form.BuilderForm;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.ms.internal.sp.BuilderTransportSP;
import ru.textanalysis.tawt.rest.client.config.Config;

public class RemoteServiceFactory {
    private final GraphematicParserRemoteService graphematicParserRemoteService;
    private final JMorfSdkRemoteService jMorfSdkRemoteService;
    private final GamaRemoteService gamaRemoteService;
    private final SyntaxParserRemoteService syntaxParserRemoteService;
    private final ValidationService validationService;

    public RemoteServiceFactory(String url) {
        Config config = new Config();
        config.setUrl(url);

        BuilderTransportBase builderTransportBase = new BuilderTransportBase();
        BuilderForm builderForm = new BuilderForm();
        BuilderTransportRef builderTransportRef = new BuilderTransportRef(builderForm);
        BuilderTransportSP builderTransportSP = new BuilderTransportSP(builderTransportRef);
        RestClientService restClientService = new RestClientService(new RestTemplate());

        this.graphematicParserRemoteService = new GraphematicParserRemoteService(restClientService, config);
        this.gamaRemoteService = new GamaRemoteService(builderTransportRef, restClientService, config);
        this.jMorfSdkRemoteService = new JMorfSdkRemoteService(builderTransportBase, builderTransportRef, restClientService, config);
        this.syntaxParserRemoteService = new SyntaxParserRemoteService(builderTransportSP, restClientService, config);
        this.validationService = new ValidationService(new RestTemplate(), config);
        this.validationService.afterPropertiesSet();
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
