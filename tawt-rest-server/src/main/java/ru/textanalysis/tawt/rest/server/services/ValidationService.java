package ru.textanalysis.tawt.rest.server.services;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.textanalysis.tawt.rest.common.api.request.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class ValidationService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public Collection<? extends String> validationRequest(SelectByStringRequest request) {
        List<String> errors = new LinkedList<>();
        if (request == null) {
            String message = "Request is null | Запрос не задан";
            log.warn(message);
            errors.add(message);
        } else if (StringUtils.isBlank(request.getText())) {
            String message = "Field 'text' is null or empty | Поле 'text' не задано, либо пустое";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }

    public Collection<? extends String> validationRequest(ExistFormByStringRequest request) {
        List<String> errors = new LinkedList<>();
        if (request == null) {
            String message = "Request is null | Запрос не задан";
            log.warn(message);
            errors.add(message);
        } else if (StringUtils.isBlank(request.getWord())) {
            String message = "Field 'word' is null or empty | Поле 'word' не задано, либо пустое";
            log.warn(message);
            errors.add(message);
        } else if (!request.getWord().trim().matches("^\\S+$")) {
            String message = "Field 'word' must contain one word | Поле 'word' должно содержать только одно слово";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }

    public Collection<? extends String> validationRequest(SelectByWordRequest request) {
        List<String> errors = new LinkedList<>();
        if (request == null) {
            String message = "Request is null | Запрос не задан";
            log.warn(message);
            errors.add(message);
        } else if (StringUtils.isBlank(request.getWord())) {
            String message = "Field 'word' is null or empty | Поле 'word' не задано, либо пустое";
            log.warn(message);
            errors.add(message);
        } else if (!request.getWord().trim().matches("^\\S+$")) {
            String message = "Field 'word' must contain one word | Поле 'word' должно содержать только одно слово";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }

    public Collection<? extends String> validationRequest(SelectByBearingPhraseRequest request) {
        List<String> errors = new LinkedList<>();
        if (request == null) {
            String message = "Request is null | Запрос не задан";
            log.warn(message);
            errors.add(message);
        } else if (StringUtils.isBlank(request.getBearingPhrase())) {
            String message = "Field 'bearingPhrase' is null or empty | Поле 'bearingPhrase' не задано, либо пустое";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }

    public Collection<? extends String> validationRequest(SelectByParagraphRequest request) {
        List<String> errors = new LinkedList<>();
        if (request == null) {
            String message = "Request is null | Запрос не задан";
            log.warn(message);
            errors.add(message);
        } else if (StringUtils.isBlank(request.getParagraph())) {
            String message = "Field 'paragraph' is null or empty | Поле 'paragraph' не задано, либо пустое";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }

    public Collection<? extends String> validationRequest(SelectBySentenceRequest request) {
        List<String> errors = new LinkedList<>();
        if (request == null) {
            String message = "Request is null | Запрос не задан";
            log.warn(message);
            errors.add(message);
        } else if (StringUtils.isBlank(request.getSentence())) {
            String message = "Field 'sentence' is null or empty | Поле 'sentence' не задано, либо пустое";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }

    public Collection<? extends String> validationRequest(SelectByStringWithTypeOfSpeechesAndMorphologyCharacteristicsRequest request) {
        List<String> errors = new LinkedList<>();
        if (request != null) {
            if (StringUtils.isBlank(request.getWord())) {
                String message = "Field 'word' is null or empty | Поле 'word' не задано, либо пустое";
                log.warn(message);
                errors.add(message);
            } else if (!request.getWord().trim().matches("^\\S+$")) {
                String message = "Field 'word' must contain one word | Поле 'word' должно содержать только одно слово";
                log.warn(message);
                errors.add(message);
            }
            if (request.getTypeOfSpeeches() == null) {
                String message = "Field 'typeOfSpeeches' is null | Поле 'typeOfSpeeches' не задано";
                log.warn(message);
                errors.add(message);
            }
            if (request.getMorphologyCharacteristics() == null) {
                String message = "Field 'morphologyCharacteristics' is null | Поле 'morphologyCharacteristics' не задано";
                log.warn(message);
                errors.add(message);
            }
        } else {
            String message = "Request is null | Запрос не задан";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }

    public Collection<? extends String> validationRequest(SelectByStringWithTypeOfSpeechesRequest request) {
        List<String> errors = new LinkedList<>();
        if (request != null) {
            if (StringUtils.isBlank(request.getWord())) {
                String message = "Field 'word' is null or empty | Поле 'word' не задано, либо пустое";
                log.warn(message);
                errors.add(message);
            } else if (!request.getWord().trim().matches("^\\S+$")) {
                String message = "Field 'word' must contain one word | Поле 'word' должно содержать только одно слово";
                log.warn(message);
                errors.add(message);
            }
            if (request.getTypeOfSpeeches() == null) {
                String message = "Field 'typeOfSpeeches' is null | Поле 'typeOfSpeeches' не задано";
                log.warn(message);
                errors.add(message);
            }
        } else {
            String message = "Request is null | Запрос не задан";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }

    public Collection<? extends String> validationRequest(SelectByStringWithMorphologyCharacteristicsRequest request) {
        List<String> errors = new LinkedList<>();
        if (request != null) {
            if (StringUtils.isBlank(request.getWord())) {
                String message = "Field 'word' is null or empty | Поле 'word' не задано, либо пустое";
                log.warn(message);
                errors.add(message);
            } else if (!request.getWord().trim().matches("^\\S+$")) {
                String message = "Field 'word' must contain one word | Поле 'word' должно содержать только одно слово";
                log.warn(message);
                errors.add(message);
            }
            if (request.getMorphologyCharacteristics() == null) {
                String message = "Field 'morphologyCharacteristics' is null | Поле 'morphologyCharacteristics' не задано";
                log.warn(message);
                errors.add(message);
            }
        } else {
            String message = "Request is null | Запрос не задан";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }
}
