package ru.textanalysis.tawt.rest.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.tawt.ms.internal.sp.BuilderTransportSP;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportBearingPhraseExtItem;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportBearingPhraseSPItem;
import ru.textanalysis.tawt.sp.api.SyntaxParser;

import java.util.LinkedList;
import java.util.List;

@Lazy
@Service
public class SyntaxParserService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SyntaxParser sp = new SyntaxParser();
    private final BuilderTransportSP builderTransportSP = new BuilderTransportSP();

    private void spInit () {
        sp.init();
    }

    public ServiceWorksResult<List<TransportBearingPhraseSPItem>> selectTreeSentenceByString(String text) {
        List<String> errors = new LinkedList<>();
        List<TransportBearingPhraseSPItem> result = new LinkedList<>();
        try {
            spInit();
            sp.getTreeSentence(text).forEach(bearingPhraseSP -> {
                TransportBearingPhraseSPItem item = builderTransportSP.build(bearingPhraseSP);
                result.add(item);
            });
        } catch (Throwable ex) {
            String message = "Cannot getTreeSentence for text: " + String.valueOf(text);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<TransportBearingPhraseExtItem>> selectTreeSentenceWithoutAmbiguity(String text) {
        List<String> errors = new LinkedList<>();
        List<TransportBearingPhraseExtItem> result = new LinkedList<>();
        try {
            spInit();
            sp.getTreeSentenceWithoutAmbiguity(text).forEach(bearingPhraseExt -> {
                TransportBearingPhraseExtItem item = builderTransportSP.build(bearingPhraseExt);
                result.add(item);
            });
        } catch (Throwable ex) {
            String message = "Cannot getTreeSentenceWithoutAmbiguity for text: " + String.valueOf(text);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }
}
