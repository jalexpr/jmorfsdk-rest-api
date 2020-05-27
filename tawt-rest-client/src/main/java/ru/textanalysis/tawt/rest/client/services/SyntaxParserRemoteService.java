package ru.textanalysis.tawt.rest.client.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.common.rest.services.RestClientService;
import ru.textanalysis.tawt.ms.internal.sp.BearingPhraseSP;
import ru.textanalysis.tawt.ms.internal.sp.BuilderTransportSP;
import ru.textanalysis.tawt.rest.client.config.Config;
import ru.textanalysis.tawt.rest.common.api.request.SelectByStringRequest;
import ru.textanalysis.tawt.rest.common.api.response.SelectTreeSentenceByStringResponse;
import ru.textanalysis.tawt.rest.common.exception.TawtRestRuntimeException;

import java.util.LinkedList;
import java.util.List;

@Lazy
@Service
@SuppressWarnings("FieldCanBeLocal")
public class SyntaxParserRemoteService {
    private final String SERVICE_NAME;
    private final String URN_SELECT_TREE_SENTENCE_BY_STRING = "api/sp/get/tree/sentence";

    private final RestClientService restClientService;
    private final BuilderTransportSP builderTransportSP;

    SyntaxParserRemoteService(RestClientService restClientService, BuilderTransportSP builderTransportSP, Config config) {
        this.restClientService = restClientService;
        this.builderTransportSP = builderTransportSP;
        this.SERVICE_NAME = String.format("%s:%s/tawt-rest-api", config.getAddress(), config.getPort());
    }

    public ServiceWorksResult<List<BearingPhraseSP>> getTreeSentence(String text) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(text);

        SelectTreeSentenceByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_SELECT_TREE_SENTENCE_BY_STRING,
                        request, SelectTreeSentenceByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_SELECT_TREE_SENTENCE_BY_STRING, text);
            throw new TawtRestRuntimeException(message);
        }

        List<BearingPhraseSP> result = new LinkedList<>();
        response.getData().getBearingPhraseSPList().forEach(transportBearingPhraseSPItem -> {
            result.add(builderTransportSP.build(transportBearingPhraseSPItem));
        });

        return new ServiceWorksResult<>(result, response.getErrors());
    }
}
