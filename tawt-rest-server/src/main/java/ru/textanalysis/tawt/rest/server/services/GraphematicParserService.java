package ru.textanalysis.tawt.rest.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.tawt.graphematic.parser.text.GParserImpl;
import ru.textanalysis.tawt.graphematic.parser.text.GraphematicParser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class GraphematicParserService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final GraphematicParser parser = new GParserImpl();

    public ServiceWorksResult<List<String>> parserBasicsPhaseByString(String sentence) {
        List<String> errors = new LinkedList<>();
        List<String> result = new ArrayList<>();
        try {
            result = parser.parserBasicsPhase(sentence);
        } catch (Throwable ex) {
            String message = "Cannot parserBasicsPhase for sentence: " + String.valueOf(sentence);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<List<String>>> parserSentenceByString(String sentence) {
        List<String> errors = new LinkedList<>();
        List<List<String>> result = new ArrayList<>();
        try {
            result = parser.parserSentence(sentence);
        } catch (Throwable ex) {
            String message = "Cannot parserSentence for sentence: " + String.valueOf(sentence);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<List<List<String>>>> parserParagraphByString(String sentence) {
        List<String> errors = new LinkedList<>();
        List<List<List<String>>> result = new ArrayList<>();
        try {
            result = parser.parserParagraph(sentence);
        } catch (Throwable ex) {
            String message = "Cannot parserParagraph for sentence: " + String.valueOf(sentence);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<List<List<List<String>>>>> parserTextByString(String sentence) {
        List<String> errors = new LinkedList<>();
        List<List<List<List<String>>>> result = new ArrayList<>();
        try {
            result = parser.parserText(sentence);
        } catch (Throwable ex) {
            String message = "Cannot parserText for sentence: " + String.valueOf(sentence);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }
}
