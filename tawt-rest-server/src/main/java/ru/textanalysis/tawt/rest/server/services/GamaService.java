package ru.textanalysis.tawt.rest.server.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.tawt.gama.main.Gama;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.ms.storage.ref.RefBearingPhraseList;
import ru.textanalysis.tawt.ms.storage.ref.RefParagraphList;
import ru.textanalysis.tawt.ms.storage.ref.RefSentenceList;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportRefOmoFormItem;

import java.util.LinkedList;
import java.util.List;

@Lazy
@Service
public class GamaService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final BuilderTransportRef builderTransportRef;

    private final Gama gama = new Gama();

    public GamaService(BuilderTransportRef builderTransportRef) {
        this.builderTransportRef = builderTransportRef;
    }

    private void gamaInit () {
        gama.init();
    }

    public ServiceWorksResult<List<TransportRefOmoFormItem>> selectMorphWordByString(String word) {
        List<String> errors = new LinkedList<>();
        /*List<Form> forms = new ArrayList<>();
        RefOmoFormList result = new RefOmoFormList(forms);*/
        List<TransportRefOmoFormItem> result = new LinkedList<>();
        try {
            gamaInit();
            //result = gama.getMorphWord(word);
            gama.getMorphWord(word).copy().forEach(form -> {
                TransportRefOmoFormItem item = builderTransportRef.build(form);
                result.add(item);
            });
        } catch (Throwable ex) {
            String message = "Cannot MorphWord for word: " + String.valueOf(word);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<List<TransportRefOmoFormItem>>> selectMorphBearingPhraseByString(String bearingPhrase) {
        List<String> errors = new LinkedList<>();
        //RefWordList result = new RefWordList();
        List<List<TransportRefOmoFormItem>> result = new LinkedList<>();
        List<TransportRefOmoFormItem> formItems = new LinkedList<>();
        try {
            gamaInit();
            //result = gama.getMorphBearingPhrase(bearingPhrase);
            gama.getMorphBearingPhrase(bearingPhrase).forEach(refOmoFormList -> {
                refOmoFormList.copy().forEach(form -> {
                    TransportRefOmoFormItem item = builderTransportRef.build(form);
                    formItems.add(item);
                });
                result.add(formItems);
            });
        } catch (Throwable ex) {
            String message = "Cannot MorphBearingPhrase for bearingPhrase: " + String.valueOf(bearingPhrase);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<RefSentenceList> selectMorphParagraphByString(String paragraph) {
        List<String> errors = new LinkedList<>();
        RefSentenceList result = new RefSentenceList();
        try {
            gamaInit();
            result = gama.getMorphParagraph(paragraph);
        } catch (Throwable ex) {
            String message = "Cannot MorphParagraph for paragraph: " + String.valueOf(paragraph);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<RefBearingPhraseList> selectMorphSentenceByString(String sentence) {
        List<String> errors = new LinkedList<>();
        RefBearingPhraseList result = new RefBearingPhraseList();
        try {
            gamaInit();
            result = gama.getMorphSentence(sentence);
        } catch (Throwable ex) {
            String message = "Cannot MorphSentence for sentence: " + String.valueOf(sentence);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<RefParagraphList> selectMorphTextByString(String text) {
        List<String> errors = new LinkedList<>();
        RefParagraphList result = new RefParagraphList();
        try {
            gamaInit();
            result = gama.getMorphText(text);
        } catch (Throwable ex) {
            String message = "Cannot MorphText for text: " + String.valueOf(text);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }



}
