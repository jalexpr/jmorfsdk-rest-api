package ru.textanalysis.tawt.rest.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.common.rest.services.RestClientService;
import ru.textanalysis.tawt.ms.internal.form.Form;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.ms.internal.ref.RefOmoFormList;
import ru.textanalysis.tawt.ms.storage.ref.RefWordList;
import ru.textanalysis.tawt.rest.common.api.request.SelectByStringRequest;
import ru.textanalysis.tawt.rest.common.api.response.SelectMorphBearingPhraseByStringResponse;
import ru.textanalysis.tawt.rest.common.api.response.SelectMorphWordByStringResponse;
import ru.textanalysis.tawt.rest.common.exception.TawtRestRuntimeException;

import java.util.ArrayList;
import java.util.List;

@Lazy
@Service
public class GamaRemoteService {
    private final static String SERVICE_NAME = "http://localhost:30002/tawt-rest-api";
    private final static String URN_SELECT_MORPH_WORD_BY_STRING = "/api/gama/get/morph/word";
    private final static String URN_SELECT_MORPH_BEARING_PHRASE_BY_STRING = "/api/gama/get/morph/bearing/phrase";

    private final RestClientService restClientService;
    private final BuilderTransportRef builderTransportRef;

    @Autowired
    public GamaRemoteService(RestClientService restClientService, BuilderTransportRef builderTransportRef) {
        this.restClientService = restClientService;
        this.builderTransportRef = builderTransportRef;
    }

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

        List<Form> forms = new ArrayList<>();
        response.getData().getRefOmoForms().forEach(item -> {
            Form form = builderTransportRef.build(item);
            forms.add(form);
        });
        RefOmoFormList result = new RefOmoFormList(forms);


        return new ServiceWorksResult<>(result, response.getErrors());
    }

    public ServiceWorksResult<RefWordList> getMorphBearingPhrase(String text) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(text);

        SelectMorphBearingPhraseByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_SELECT_MORPH_BEARING_PHRASE_BY_STRING,
                        request, SelectMorphBearingPhraseByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_SELECT_MORPH_BEARING_PHRASE_BY_STRING, text);
            throw new TawtRestRuntimeException(message);
        }

        RefWordList result = new RefWordList();
        List<Form> forms = new ArrayList<>();
        response.getData().getRefOmoForms().forEach(item -> {
            item.forEach(item2 ->{
                Form form = builderTransportRef.build(item2);
                forms.add(form);
            });
            result.add(new RefOmoFormList(forms));
        });
       /* RefOmoFormList refOmoFormList = new RefOmoFormList(forms);

        result.add(refOmoFormList);*/

        return new ServiceWorksResult<>(result, response.getErrors());
    }
}
