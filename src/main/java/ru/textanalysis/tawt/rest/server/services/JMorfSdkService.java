package ru.textanalysis.tawt.rest.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.tawt.jmorfsdk.JMorfSdk;
import ru.textanalysis.tawt.jmorfsdk.loader.JMorfSdkFactory;
import ru.textanalysis.tawt.ms.storage.OmoFormList;

import java.util.LinkedList;
import java.util.List;

@Lazy
@Service
public class JMorfSdkService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final JMorfSdk jMorfSdk;

    public JMorfSdkService() {
        this.jMorfSdk = JMorfSdkFactory.loadFullLibrary();
    }

    public ServiceWorksResult<OmoFormList> selectOmoformsByString(String word) {
        List<String> errors = new LinkedList<>();
        OmoFormList result = new OmoFormList();
        try {
            result = jMorfSdk.getAllCharacteristicsOfForm(word);
        } catch (Throwable ex) {
            String message = "Cannot AllCharacteristicsOfForm for word: " + String.valueOf(word);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<Boolean> isFormExistsInDictionary(String word) {
        List<String> errors = new LinkedList<>();
        Boolean result = null;
        try {
            result = jMorfSdk.isFormExistsInDictionary(word);
        } catch (Throwable ex) {
            String message = "Cannot AllCharacteristicsOfForm for word: " + String.valueOf(word);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }
}
