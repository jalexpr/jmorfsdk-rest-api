package ru.textanalysis.tawt.rest.server.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.tawt.gama.main.Gama;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.rest.common.api.response.item.*;

import java.util.LinkedList;
import java.util.List;

@Service
public class GamaService implements InitializingBean {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final BuilderTransportRef builderTransportRef;

    private final Gama gama = new Gama();

    public GamaService(BuilderTransportRef builderTransportRef) {
        this.builderTransportRef = builderTransportRef;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        gamaInit();
    }

    private void gamaInit () {
        gama.init();
    }

    public ServiceWorksResult<List<TransportRefOmoFormItem>> selectMorphWordByString(String word) {
        List<String> errors = new LinkedList<>();
        List<TransportRefOmoFormItem> result = new LinkedList<>();
        try {
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

    public ServiceWorksResult<List<MorphWord>> selectMorphBearingPhraseByString(String bearingPhrase) {
        List<String> errors = new LinkedList<>();
        List<MorphWord> result = new LinkedList<>();
        try {
            gama.getMorphBearingPhrase(bearingPhrase).forEach(refOmoFormList -> {
                MorphWord morphWord = new MorphWord();
                List<TransportRefOmoFormItem> formItems = new LinkedList<>();
                refOmoFormList.copy().forEach(form -> {
                    TransportRefOmoFormItem item = builderTransportRef.build(form);
                    formItems.add(item);
                });
                morphWord.setRefOmoForms(formItems);
                result.add(morphWord);
            });
        } catch (Throwable ex) {
            String message = "Cannot MorphBearingPhrase for bearingPhrase: " + String.valueOf(bearingPhrase);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<MorphSentence>> selectMorphParagraphByString(String paragraph) {
        List<String> errors = new LinkedList<>();
        List<MorphSentence> result = new LinkedList<>();
        try {
            gama.getMorphParagraph(paragraph).forEach(refWordLists -> {
                MorphSentence morphSentence = new MorphSentence();
                List<MorphBearingPhrase> refWordItems = new LinkedList<>();
                refWordLists.forEach(wordList -> {
                    MorphBearingPhrase morphBearingPhrase = new MorphBearingPhrase();
                    List<MorphWord> refOmoFormItems = new LinkedList<>();
                    wordList.forEach(refOmoFormList -> {
                        MorphWord morphWord = new MorphWord();
                        List<TransportRefOmoFormItem> formItems = new LinkedList<>();
                        refOmoFormList.copy().forEach(form -> {
                            TransportRefOmoFormItem item = builderTransportRef.build(form);
                            formItems.add(item);
                        });
                        morphWord.setRefOmoForms(formItems);
                        refOmoFormItems.add(morphWord);
                    });
                    morphBearingPhrase.setMorphWordList(refOmoFormItems);
                    refWordItems.add(morphBearingPhrase);
                });
                morphSentence.setMorphBearingPhraseList(refWordItems);
                result.add(morphSentence);
            });
        } catch (Throwable ex) {
            String message = "Cannot MorphParagraph for paragraph: " + String.valueOf(paragraph);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<MorphBearingPhrase>> selectMorphSentenceByString(String sentence) {
        List<String> errors = new LinkedList<>();
        List<MorphBearingPhrase> result = new LinkedList<>();
        try {
            gama.getMorphSentence(sentence).forEach(wordList -> {
                MorphBearingPhrase morphBearingPhrase = new MorphBearingPhrase();
                List<MorphWord> refOmoFormItems = new LinkedList<>();
                wordList.forEach(refOmoFormList -> {
                    MorphWord morphWord = new MorphWord();
                    List<TransportRefOmoFormItem> formItems = new LinkedList<>();
                    refOmoFormList.copy().forEach(form -> {
                        TransportRefOmoFormItem item = builderTransportRef.build(form);
                        formItems.add(item);
                    });
                    morphWord.setRefOmoForms(formItems);
                    refOmoFormItems.add(morphWord);
                });
                morphBearingPhrase.setMorphWordList(refOmoFormItems);
                result.add(morphBearingPhrase);
            });
        } catch (Throwable ex) {
            String message = "Cannot MorphSentence for sentence: " + String.valueOf(sentence);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<MorphParagraph>> selectMorphTextByString(String text) {
        List<String> errors = new LinkedList<>();
        List<MorphParagraph> result = new LinkedList<>();
        try {
            gama.getMorphText(text).forEach(refBearingPhraseLists -> {
                MorphParagraph morphParagraph = new MorphParagraph();
                List<MorphSentence> refSentenceItems = new LinkedList<>();
                refBearingPhraseLists.forEach(refWordLists -> {
                    MorphSentence morphSentence = new MorphSentence();
                    List<MorphBearingPhrase> refWordItems = new LinkedList<>();
                    refWordLists.forEach(wordList -> {
                        MorphBearingPhrase morphBearingPhrase = new MorphBearingPhrase();
                        List<MorphWord> refOmoFormItems = new LinkedList<>();
                        wordList.forEach(refOmoFormList -> {
                            MorphWord morphWord = new MorphWord();
                            List<TransportRefOmoFormItem> formItems = new LinkedList<>();
                            refOmoFormList.copy().forEach(form -> {
                                TransportRefOmoFormItem item = builderTransportRef.build(form);
                                formItems.add(item);
                            });
                            morphWord.setRefOmoForms(formItems);
                            refOmoFormItems.add(morphWord);
                        });
                        morphBearingPhrase.setMorphWordList(refOmoFormItems);
                        refWordItems.add(morphBearingPhrase);
                    });
                    morphSentence.setMorphBearingPhraseList(refWordItems);
                    refSentenceItems.add(morphSentence);
                });
                morphParagraph.setMorphSentenceList(refSentenceItems);
                result.add(morphParagraph);
            });
        } catch (Throwable ex) {
            String message = "Cannot MorphText for text: " + String.valueOf(text);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }
}
