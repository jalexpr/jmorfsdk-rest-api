package ru.textanalysis.tawt.rest.client.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.common.rest.services.RestClientService;
import ru.textanalysis.tawt.ms.external.sp.BearingPhraseExt;
import ru.textanalysis.tawt.ms.internal.sp.BearingPhraseSP;
import ru.textanalysis.tawt.ms.internal.sp.BuilderTransportSP;
import ru.textanalysis.tawt.rest.client.config.Config;
import ru.textanalysis.tawt.rest.common.api.request.SelectByStringRequest;
import ru.textanalysis.tawt.rest.common.api.response.SelectTreeSentenceByStringResponse;
import ru.textanalysis.tawt.rest.common.exception.TawtRestRuntimeException;

import java.util.List;
import java.util.stream.Collectors;

@Lazy
@Service
@SuppressWarnings("FieldCanBeLocal")
public class SyntaxParserRemoteService {
    private final String SERVICE_URL;
    private final String URN_SELECT_TREE_SENTENCE_BY_STRING = "api/sp/get/tree/sentence";

    private final RestClientService restClientService;
    private final BuilderTransportSP builderTransportSP;

    SyntaxParserRemoteService(BuilderTransportSP builderTransportSP,
                              RestClientService restClientService,
                              Config config) {
        this.restClientService = restClientService;
        this.builderTransportSP = builderTransportSP;
        this.SERVICE_URL = config.getUrl();
    }

    public ServiceWorksResult<List<BearingPhraseSP>> getTreeSentence(String text) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(text);

        SelectTreeSentenceByStringResponse response =
                restClientService.post(SERVICE_URL, URN_SELECT_TREE_SENTENCE_BY_STRING,
                        request, SelectTreeSentenceByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by text = %s",
                    SERVICE_URL, URN_SELECT_TREE_SENTENCE_BY_STRING, text);
            throw new TawtRestRuntimeException(message);
        }

        List<BearingPhraseSP> result = response.getData().getBearingPhraseSPList()
                .parallelStream()
                .map(builderTransportSP::build)
                .collect(Collectors.toList());

        return new ServiceWorksResult<>(result, response.getErrors());
    }

    public ServiceWorksResult<List<BearingPhraseExt>> selectTreeSentenceWithoutAmbiguity(String text) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(text);

        SelectTreeSentenceByStringResponse response =
                restClientService.post(SERVICE_URL, URN_SELECT_TREE_SENTENCE_BY_STRING,
                        request, SelectTreeSentenceByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by text = %s",
                    SERVICE_URL, URN_SELECT_TREE_SENTENCE_BY_STRING, text);
            throw new TawtRestRuntimeException(message);
        }

        List<BearingPhraseExt> result = response.getData().getBearingPhraseSPList()
                .parallelStream()
                .map(builderTransportSP::buildExt)
                .collect(Collectors.toList());

        return new ServiceWorksResult<>(result, response.getErrors());
    }
}
