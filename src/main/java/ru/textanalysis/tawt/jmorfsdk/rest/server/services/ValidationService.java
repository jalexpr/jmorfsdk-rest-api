package ru.textanalysis.tawt.jmorfsdk.rest.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.textanalysis.tawt.jmorfsdk.rest.api.selection.request.SelectByStringRequest;

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
        }
        //todo доделать
        return errors;
    }
}
