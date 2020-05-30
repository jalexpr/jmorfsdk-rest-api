package ru.textanalysis.tawt.rest.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.tawt.ms.internal.sp.BuilderTransportSP;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportBearingPhraseExtItem;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportBearingPhraseSPItem;
import ru.textanalysis.tawt.sp.api.SyntaxParser;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SyntaxParserService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SyntaxParser sp;
    private final BuilderTransportSP builderTransportSP;

    public SyntaxParserService(SyntaxParser sp,
                               BuilderTransportSP builderTransportSP) {
        this.sp = sp;
        this.builderTransportSP = builderTransportSP;
    }

    public ServiceWorksResult<List<TransportBearingPhraseSPItem>> selectTreeSentenceByString(String text) {
        List<String> errors = new LinkedList<>();
        List<TransportBearingPhraseSPItem> result;
        try {
            result = sp.getTreeSentence(text)
                    .parallelStream()
                    .map(builderTransportSP::build)
                    .collect(Collectors.toList());
        } catch (Throwable ex) {
            String message = "Cannot getTreeSentence for text: " + text;
            log.warn(message, ex);
            errors.add(message);
            result = new LinkedList<>();
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<TransportBearingPhraseExtItem>> selectTreeSentenceWithoutAmbiguity(String text) {
        List<String> errors = new LinkedList<>();
        List<TransportBearingPhraseExtItem> result;
        try {
            result = sp.getTreeSentenceWithoutAmbiguity(text)
                    .parallelStream()
                    .map(builderTransportSP::build)
                    .collect(Collectors.toList());
        } catch (Throwable ex) {
            String message = "Cannot getTreeSentenceWithoutAmbiguity for text: " + text;
            log.warn(message, ex);
            errors.add(message);
            result = new LinkedList<>();
        }
        return new ServiceWorksResult<>(result, errors);
    }
}
