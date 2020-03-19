package ru.textanalysis.tawt.rest.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.tawt.ms.external.sp.BearingPhraseExt;
import ru.textanalysis.tawt.ms.internal.sp.BearingPhraseSP;
import ru.textanalysis.tawt.sp.api.SyntaxParser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Lazy
@Service
public class SyntaxParserService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SyntaxParser sp = new SyntaxParser();

    private void spInit () {
        sp.init();
    }

    public ServiceWorksResult<List<BearingPhraseSP>> selectTreeSentenceByString(String text) {
        List<String> errors = new LinkedList<>();
        List<BearingPhraseSP> result = new ArrayList<>();
        try {
            spInit();
            result = sp.getTreeSentence(text);
        } catch (Throwable ex) {
            String message = "Cannot getTreeSentence for text: " + String.valueOf(text);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<BearingPhraseExt>> selectTreeSentenceWithoutAmbiguity(String text) {
        List<String> errors = new LinkedList<>();
        List<BearingPhraseExt> result = new ArrayList<>();
        try {
            spInit();
            result = sp.getTreeSentenceWithoutAmbiguity(text);
        } catch (Throwable ex) {
            String message = "Cannot getTreeSentenceWithoutAmbiguity for text: " + String.valueOf(text);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }
}
