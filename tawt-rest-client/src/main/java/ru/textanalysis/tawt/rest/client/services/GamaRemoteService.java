package ru.textanalysis.tawt.rest.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.common.rest.services.RestClientService;
import ru.textanalysis.tawt.ms.internal.ref.RefOmoFormList;
import ru.textanalysis.tawt.rest.common.api.request.SelectByStringRequest;
import ru.textanalysis.tawt.rest.common.api.response.SelectMorphWordByStringResponse;
import ru.textanalysis.tawt.rest.common.exception.TawtRestRuntimeException;

@Lazy
@Service
public class GamaRemoteService {
    private final static String SERVICE_NAME = "http://localhost:30002/tawt-rest-api";
    private final static String URN_SELECT_MORPH_WORD_BY_STRING = "/api/gama/get/morph/word";

    private final RestClientService restClientService;

    @Autowired
    public GamaRemoteService(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

    //todo Не работает
    public ServiceWorksResult<RefOmoFormList> getMorphWord(String text) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(text);

        SelectMorphWordByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_SELECT_MORPH_WORD_BY_STRING,
                        request, SelectMorphWordByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_SELECT_MORPH_WORD_BY_STRING, text);
            throw new TawtRestRuntimeException(message);
        }


        return new ServiceWorksResult<>(response.getData().getRefOmoFormList(), response.getErrors());
    }
}
