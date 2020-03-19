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
            String message = "Request is null";
            log.warn(message);
            errors.add(message);
        } else if (StringUtils.isBlank(request.getText())) {
            String message = "Field 'text' is null or empty";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }

    public Collection<? extends String> validationRequest(ExistFormByStringRequest request) {
        List<String> errors = new LinkedList<>();
        if (request == null) {
            String message = "Request is null";
            log.warn(message);
            errors.add(message);
        } else if (StringUtils.isBlank(request.getWord())) {
            String message = "Field 'text' is null or empty";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }

    public Collection<? extends String> validationRequest(SelectByStringWithTypeOfSpeechesAndMorphologyCharacteristicsRequest request) {
        List<String> errors = new LinkedList<>();
        if (request == null) {
            String message = "Request is null";
            log.warn(message);
            errors.add(message);
        } else if (StringUtils.isBlank(request.getWord())) {
            String message = "Field 'word' is null or empty";
            log.warn(message);
            errors.add(message);
        } else if (request.getTypeOfSpeeches() == null) {
            String message = "Field 'typeOfSpeeches' is null";
            log.warn(message);
            errors.add(message);
        } else if (request.getMorphologyCharacteristics() == null) {
            String message = "Field 'morphologyCharacteristics' is null";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }

    public Collection<? extends String> validationRequest(SelectByStringWithTypeOfSpeechesRequest request) {
        List<String> errors = new LinkedList<>();
        if (request == null) {
            String message = "Request is null";
            log.warn(message);
            errors.add(message);
        } else if (StringUtils.isBlank(request.getWord())) {
            String message = "Field 'word' is null or empty";
            log.warn(message);
            errors.add(message);
        } else if (request.getTypeOfSpeeches() == null) {
            String message = "Field 'typeOfSpeeches' is null";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }

    public Collection<? extends String> validationRequest(SelectByStringWithMorphologyCharacteristicsRequest request) {
        List<String> errors = new LinkedList<>();
        if (request == null) {
            String message = "Request is null";
            log.warn(message);
            errors.add(message);
        } else if (StringUtils.isBlank(request.getWord())) {
            String message = "Field 'word' is null or empty";
            log.warn(message);
            errors.add(message);
        } else if (request.getMorphologyCharacteristics() == null) {
            String message = "Field 'morphologyCharacteristics' is null";
            log.warn(message);
            errors.add(message);
        }
        return errors;
    }
}
