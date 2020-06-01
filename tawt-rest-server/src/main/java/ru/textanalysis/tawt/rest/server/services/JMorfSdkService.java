package ru.textanalysis.tawt.rest.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.tawt.jmorfsdk.JMorfSdk;
import ru.textanalysis.tawt.ms.internal.BuilderTransportBase;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportOmoFormItem;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportRefOmoFormItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JMorfSdkService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final JMorfSdk jMorfSdk;
    private final BuilderTransportBase builderTransport;
    private final BuilderTransportRef builderTransportRef;

    public JMorfSdkService(JMorfSdk jMorfSdk,
                           BuilderTransportBase builderTransport,
                           BuilderTransportRef builderTransportRef) {
        this.jMorfSdk = jMorfSdk;
        this.builderTransport = builderTransport;
        this.builderTransportRef = builderTransportRef;
    }

    public ServiceWorksResult<List<TransportOmoFormItem>> selectOmoformsByString(String word) {
        List<String> errors = new LinkedList<>();
        List<TransportOmoFormItem> result;
        try {
            result = jMorfSdk.getAllCharacteristicsOfForm(word)
                    .parallelStream()
                    .map(builderTransport::build)
                    .collect(Collectors.toList());
        } catch (Throwable ex) {
            String message = "Cannot AllCharacteristicsOfForm for word: " + word;
            log.warn(message, ex);
            errors.add(message);
            result = new LinkedList<>();
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<Boolean> isFormExistsInDictionary(String word) {
        List<String> errors = new LinkedList<>();
        Boolean result = null;
        try {
            result = jMorfSdk.isFormExistsInDictionary(word);
        } catch (Throwable ex) {
            String message = "Cannot AllCharacteristicsOfForm for word: " + word;
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<Long>> selectMorphologyCharacteristicsByString(String word) {
        List<String> errors = new LinkedList<>();
        List<Long> result = new ArrayList<>();
        try {
            result = jMorfSdk.getMorphologyCharacteristics(word);
        } catch (Throwable ex) {
            String message = "Cannot AllCharacteristicsOfForm for word: " + word;
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<TransportRefOmoFormItem>> selectRefOmoFormListByString(String word) {
        List<String> errors = new LinkedList<>();
        List<TransportRefOmoFormItem> result;
        try {
            result = jMorfSdk.getRefOmoFormList(word)
                    .stream()
                    .parallel()
                    .map(builderTransportRef::build)
                    .collect(Collectors.toList());
        } catch (Throwable ex) {
            String message = "Cannot RefOmoFormList for word: " + word;
            log.warn(message, ex);
            errors.add(message);
            result = new LinkedList<>();
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<String>> selectStringInitialFormByString(String word) {
        List<String> errors = new LinkedList<>();
        List<String> result = new ArrayList<>();
        try {
            result = jMorfSdk.getStringInitialForm(word);
        } catch (Throwable ex) {
            String message = "Cannot StringInitialForm for word: " + word;
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<Byte>> selectTypeOfSpeechesByString(String word) {
        List<String> errors = new LinkedList<>();
        List<Byte> result = new ArrayList<>();
        try {
            result = jMorfSdk.getTypeOfSpeeches(word);
        } catch (Throwable ex) {
            String message = "Cannot TypeOfSpeeches for word: " + word;
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<Byte> isInitialForm(String word) {
        List<String> errors = new LinkedList<>();
        Byte result = null;
        try {
            result = jMorfSdk.isInitialForm(word);
        } catch (Throwable ex) {
            String message = "Cannot InitialForm for word: " + word;
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<String>> selectDerivativeFormByString(String word, Byte typeOfSpeeches, Long morphologyCharacteristics) {
        List<String> errors = new LinkedList<>();
        List<String> result = new ArrayList<>();
        try {
            result = jMorfSdk.getDerivativeForm(word, typeOfSpeeches, morphologyCharacteristics);
        } catch (Throwable ex) {
            String message = String.format("Cannot DerivativeForm For word: %s " +
                            "with type of speeches: %s " +
                            "and morphology characteristics: %s",
                    word, typeOfSpeeches, morphologyCharacteristics);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<String>> selectDerivativeFormByString(String word, Byte typeOfSpeeches) {
        List<String> errors = new LinkedList<>();
        List<String> result = new ArrayList<>();
        try {
            result = jMorfSdk.getDerivativeForm(word, typeOfSpeeches);
        } catch (Throwable ex) {
            String message = String.format("Cannot DerivativeForm For word: %s with type of speeches: %s",
                    word, typeOfSpeeches);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<String>> selectDerivativeFormByString(String word, Long morphologyCharacteristics) {
        List<String> errors = new LinkedList<>();
        List<String> result = new ArrayList<>();
        try {
            result = jMorfSdk.getDerivativeForm(word, morphologyCharacteristics);
        } catch (Throwable ex) {
            String message = String.format("Cannot DerivativeForm For word: %s with morphology characteristics: %s",
                    word, morphologyCharacteristics);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }
}
