package ru.textanalysis.tawt.rest.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.common.rest.services.RestClientService;
import ru.textanalysis.tawt.ms.internal.form.Form;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.ms.internal.ref.RefOmoFormList;
import ru.textanalysis.tawt.ms.storage.ref.RefBearingPhraseList;
import ru.textanalysis.tawt.ms.storage.ref.RefParagraphList;
import ru.textanalysis.tawt.ms.storage.ref.RefSentenceList;
import ru.textanalysis.tawt.ms.storage.ref.RefWordList;
import ru.textanalysis.tawt.rest.client.config.Config;
import ru.textanalysis.tawt.rest.common.api.request.*;
import ru.textanalysis.tawt.rest.common.api.response.*;
import ru.textanalysis.tawt.rest.common.exception.TawtRestRuntimeException;

import java.util.ArrayList;
import java.util.List;

@Lazy
@Service
@SuppressWarnings("FieldCanBeLocal")
public class GamaRemoteService {
    private final String SERVICE_URL;
    private final String URN_SELECT_MORPH_WORD_BY_STRING = "api/gama/get/morph/word";
    private final String URN_SELECT_MORPH_BEARING_PHRASE_BY_STRING = "api/gama/get/morph/bearing/phrase";
    private final String URN_SELECT_MORPH_SENTENCE_BY_STRING = "api/gama/get/morph/sentence";
    private final String URN_SELECT_MORPH_PARAGRAPH_BY_STRING = "api/gama/get/morph/paragraph";
    private final String URN_SELECT_MORPH_TEXT_BY_STRING = "api/gama/get/morph/text";

    private final RestClientService restClientService;
    private final BuilderTransportRef builderTransportRef;

    @Autowired
    GamaRemoteService(BuilderTransportRef builderTransportRef,
                      RestClientService restClientService,
                      Config config) {
        this.restClientService = restClientService;
        this.builderTransportRef = builderTransportRef;
        this.SERVICE_URL = config.getUrl();
    }

    /**
     * Получение списка рефомоформ по заданному слову
     *
     * @param word слово
     * @return список рефомоформ
     */
    public ServiceWorksResult<RefOmoFormList> getMorphWord(String word) {
        SelectByWordRequest request = new SelectByWordRequest();
        request.setWord(word);

        SelectMorphWordByStringResponse response =
                restClientService.post(SERVICE_URL, URN_SELECT_MORPH_WORD_BY_STRING,
                        request, SelectMorphWordByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_URL, URN_SELECT_MORPH_WORD_BY_STRING, word);
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

    /**
     * Получение списка рефомоформ по заданному опорному обороту.
     *
     * @param bearingPhrase опорный оборот
     * @return список рефомоформ
     */
    public ServiceWorksResult<RefWordList> getMorphBearingPhrase(String bearingPhrase) {
        SelectByBearingPhraseRequest request = new SelectByBearingPhraseRequest();
        request.setBearingPhrase(bearingPhrase);

        SelectMorphBearingPhraseByStringResponse response =
                restClientService.post(SERVICE_URL, URN_SELECT_MORPH_BEARING_PHRASE_BY_STRING,
                        request, SelectMorphBearingPhraseByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by bearingPhrase = %s",
                    SERVICE_URL, URN_SELECT_MORPH_BEARING_PHRASE_BY_STRING, bearingPhrase);
            throw new TawtRestRuntimeException(message);
        }

        RefWordList result = new RefWordList();
        response.getData().getRefWordList().forEach(item -> {
            List<Form> forms = new ArrayList<>();
            item.getRefOmoForms().forEach(item2 ->{
                Form form = builderTransportRef.build(item2);
                forms.add(form);
            });
            result.add(new RefOmoFormList(forms));
        });

        return new ServiceWorksResult<>(result, response.getErrors());
    }

    /**
     * Получение списка рефомоформ по заданному предложению.
     *
     * @param sentence предложение
     * @return список рефомоформ
     */
    public ServiceWorksResult<RefBearingPhraseList> getMorphSentence(String sentence) {
        SelectBySentenceRequest request = new SelectBySentenceRequest();
        request.setSentence(sentence);

        SelectMorphSentenceByStringResponse response =
                restClientService.post(SERVICE_URL, URN_SELECT_MORPH_SENTENCE_BY_STRING,
                        request, SelectMorphSentenceByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by sentence = %s",
                    SERVICE_URL, URN_SELECT_MORPH_SENTENCE_BY_STRING, sentence);
            throw new TawtRestRuntimeException(message);
        }

        RefBearingPhraseList result = new RefBearingPhraseList();
        response.getData().getRefBearingPhraseList().forEach(refBearingPhraseList -> {
            RefWordList refWordList = new RefWordList();
            refBearingPhraseList.getMorphWordList().forEach(refOmoFormItems -> {
                List<Form> forms = new ArrayList<>();
                refOmoFormItems.getRefOmoForms().forEach(items -> {
                    Form form = builderTransportRef.build(items);
                    forms.add(form);
                });
                refWordList.add(new RefOmoFormList(forms));
            });
            result.add(refWordList);
        });

        return new ServiceWorksResult<>(result, response.getErrors());
    }

    /**
     * Получение списка рефомоформ по заданному параграфу.
     *
     * @param paragraph параграф
     * @return список рефомоформ
     */
    public ServiceWorksResult<RefSentenceList> getMorphParagraph(String paragraph) {
        SelectByParagraphRequest request = new SelectByParagraphRequest();
        request.setParagraph(paragraph);

        SelectMorphParagraphByStringResponse response =
                restClientService.post(SERVICE_URL, URN_SELECT_MORPH_PARAGRAPH_BY_STRING,
                        request, SelectMorphParagraphByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by paragraph = %s",
                    SERVICE_URL, URN_SELECT_MORPH_PARAGRAPH_BY_STRING, paragraph);
            throw new TawtRestRuntimeException(message);
        }

        RefSentenceList result = new RefSentenceList();
        response.getData().getRefSentenceList().forEach(refSentenceList -> {
            RefBearingPhraseList refBearingPhraseList = new RefBearingPhraseList();
            refSentenceList.getMorphBearingPhraseList().forEach(refBearingPhraseItems -> {
                RefWordList refWordList = new RefWordList();
                refBearingPhraseItems.getMorphWordList().forEach(refOmoFormItems -> {
                    List<Form> forms = new ArrayList<>();
                    refOmoFormItems.getRefOmoForms().forEach(items -> {
                        Form form = builderTransportRef.build(items);
                        forms.add(form);
                    });
                    refWordList.add(new RefOmoFormList(forms));
                });
                refBearingPhraseList.add(refWordList);
            });
            result.add(refBearingPhraseList);
        });

        return new ServiceWorksResult<>(result, response.getErrors());
    }

    /**
     * Получение списка рефомоформ по заданному тексту.
     *
     * @param text произвольный текст
     * @return список рефомоформ
     */
    public ServiceWorksResult<RefParagraphList> getMorphText(String text) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(text);

        SelectMorphTextByStringResponse response =
                restClientService.post(SERVICE_URL, URN_SELECT_MORPH_TEXT_BY_STRING,
                        request, SelectMorphTextByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by text = %s",
                    SERVICE_URL, URN_SELECT_MORPH_TEXT_BY_STRING, text);
            throw new TawtRestRuntimeException(message);
        }

        RefParagraphList result = new RefParagraphList();
        response.getData().getRefParagraphList().forEach(refParagraphList -> {
            RefSentenceList refSentenceList = new RefSentenceList();
            refParagraphList.getMorphSentenceList().forEach(refSentenceItems -> {
                RefBearingPhraseList refBearingPhraseList = new RefBearingPhraseList();
                refSentenceItems.getMorphBearingPhraseList().forEach(refBearingPhraseItems -> {
                    RefWordList refWordList = new RefWordList();
                    refBearingPhraseItems.getMorphWordList().forEach(refOmoFormItems -> {
                        List<Form> forms = new ArrayList<>();
                        refOmoFormItems.getRefOmoForms().forEach(items -> {
                            Form form = builderTransportRef.build(items);
                            forms.add(form);
                        });
                        refWordList.add(new RefOmoFormList(forms));
                    });
                    refBearingPhraseList.add(refWordList);
                });
                refSentenceList.add(refBearingPhraseList);
            });
            result.add(refSentenceList);
        });

        return new ServiceWorksResult<>(result, response.getErrors());
    }
}
