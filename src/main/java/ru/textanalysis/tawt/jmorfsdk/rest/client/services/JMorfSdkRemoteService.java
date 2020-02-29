package ru.textanalysis.tawt.jmorfsdk.rest.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.common.rest.services.RestClientService;
import ru.textanalysis.tawt.jmorfsdk.rest.api.selection.request.SelectByStringRequest;
import ru.textanalysis.tawt.jmorfsdk.rest.api.selection.response.SelectOmoformsByStringResponse;
import ru.textanalysis.tawt.jmorfsdk.rest.exception.JMorfSdkRestRuntimeException;
import ru.textanalysis.tawt.ms.storage.OmoFormList;

@Lazy
@Service
public class JMorfSdkRemoteService {
    private final static String SERVICE_NAME = "http://localhost:30002/jmorfsdk-rest-api";//todo читать из проперти
    private final static String URN_SELECT_OMOFORMS_BY_STRING = "api/get/all/characteristics/of/form";

    private final RestClientService restClientService;

    @Autowired
    public JMorfSdkRemoteService(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

    public ServiceWorksResult<OmoFormList> getAllCharacteristicsOfForm(String word) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setWord(word);

        SelectOmoformsByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_SELECT_OMOFORMS_BY_STRING,
                        request, SelectOmoformsByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_SELECT_OMOFORMS_BY_STRING, word);
            throw new JMorfSdkRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getOmoForms(), response.getErrors());
    }
}
