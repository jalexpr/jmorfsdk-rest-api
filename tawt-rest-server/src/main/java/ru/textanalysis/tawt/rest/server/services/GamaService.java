package ru.textanalysis.tawt.rest.server.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.tawt.gama.main.Gama;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.ms.internal.ref.RefOmoFormList;
import ru.textanalysis.tawt.ms.storage.ref.RefBearingPhraseList;
import ru.textanalysis.tawt.ms.storage.ref.RefParagraphList;
import ru.textanalysis.tawt.ms.storage.ref.RefSentenceList;
import ru.textanalysis.tawt.ms.storage.ref.RefWordList;
import ru.textanalysis.tawt.rest.common.api.response.item.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GamaService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final BuilderTransportRef builderTransportRef;

    private final Gama gama;

    public GamaService(Gama gama,
                       BuilderTransportRef builderTransportRef) {
        this.gama = gama;
        this.builderTransportRef = builderTransportRef;
    }

    public ServiceWorksResult<List<TransportRefOmoFormItem>> selectMorphWordByString(String word) {
        List<String> errors = new LinkedList<>();
        List<TransportRefOmoFormItem> result;
        try {
            result = buildMorfWord(gama.getMorphWord(word));
        } catch (Throwable ex) {
            String message = "Cannot MorphWord for word: " + word;
            log.warn(message, ex);
            errors.add(message);
            result = new LinkedList<>();
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<MorphWord>> selectMorphBearingPhraseByString(String bearingPhrase) {
        List<String> errors = new LinkedList<>();
        List<MorphWord> result;
        try {
            result = buildMorphBearingPhrase(gama.getMorphBearingPhrase(bearingPhrase));
        } catch (Throwable ex) {
            String message = "Cannot MorphBearingPhrase for bearingPhrase: " + bearingPhrase;
            log.warn(message, ex);
            errors.add(message);
            result = new LinkedList<>();
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<MorphBearingPhrase>> selectMorphSentenceByString(String sentence) {
        List<String> errors = new LinkedList<>();
        List<MorphBearingPhrase> result;
        try {
            result = buildMorphSentence(gama.getMorphSentence(sentence));
        } catch (Throwable ex) {
            String message = "Cannot MorphSentence for sentence: " + String.valueOf(sentence);
            log.warn(message, ex);
            errors.add(message);
            result = new LinkedList<>();
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<MorphSentence>> selectMorphParagraphByString(String paragraph) {
        List<String> errors = new LinkedList<>();
        List<MorphSentence> result;
        try {
            result = buildMorphParagraph(gama.getMorphParagraph(paragraph));
        } catch (Throwable ex) {
            String message = "Cannot MorphParagraph for paragraph: " + paragraph;
            log.warn(message, ex);
            errors.add(message);
            result = new LinkedList<>();
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<MorphParagraph>> selectMorphTextByString(String text) {
        List<String> errors = new LinkedList<>();
        List<MorphParagraph> result;
        try {
            result = buildMorphText(gama.getMorphText(text));
        } catch (Throwable ex) {
            String message = "Cannot MorphText for text: " + text;
            log.warn(message, ex);
            errors.add(message);
            result = new LinkedList<>();
        }
        return new ServiceWorksResult<>(result, errors);
    }

    private List<TransportRefOmoFormItem> buildMorfWord(RefOmoFormList refOmoFormList) {
        return refOmoFormList
                .stream()
                .parallel()
                .map(builderTransportRef::build)
                .collect(Collectors.toList());
    }

    private List<MorphWord> buildMorphBearingPhrase(RefWordList refWordList) {
        return refWordList
                .parallelStream()
                .map(this::buildMorfWord)
                .map(MorphWord::new)
                .collect(Collectors.toList());
    }

    private List<MorphBearingPhrase> buildMorphSentence(RefBearingPhraseList refBearingPhraseList) {
        return refBearingPhraseList
                .parallelStream()
                .map(this::buildMorphBearingPhrase)
                .map(MorphBearingPhrase::new)
                .collect(Collectors.toList());

    }

    private List<MorphSentence> buildMorphParagraph(RefSentenceList refSentenceList) {
        return refSentenceList
                .parallelStream()
                .map(this::buildMorphSentence)
                .map(MorphSentence::new)
                .collect(Collectors.toList());
    }

    private List<MorphParagraph> buildMorphText(RefParagraphList refParagraphList) {
        return refParagraphList
                .parallelStream()
                .map(this::buildMorphParagraph)
                .map(MorphParagraph::new)
                .collect(Collectors.toList());
    }
}
