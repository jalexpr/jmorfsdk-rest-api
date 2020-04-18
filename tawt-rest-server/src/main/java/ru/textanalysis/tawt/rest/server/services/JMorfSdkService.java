package ru.textanalysis.tawt.rest.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.tawt.jmorfsdk.JMorfSdk;
import ru.textanalysis.tawt.jmorfsdk.loader.JMorfSdkFactory;
import ru.textanalysis.tawt.ms.internal.BuilderTransportBase;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportOmoFormItem;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportRefOmoFormItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Lazy
@Service
public class JMorfSdkService implements InitializingBean {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private JMorfSdk jMorfSdk;
    private final BuilderTransportBase builderTransport;
    private final BuilderTransportRef builderTransportRef;

    public JMorfSdkService(BuilderTransportBase builderTransport, BuilderTransportRef builderTransportRef) {
        this.builderTransport = builderTransport;
        this.builderTransportRef = builderTransportRef;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.jMorfSdk = JMorfSdkFactory.loadFullLibrary(false);
    }

    public ServiceWorksResult<List<TransportOmoFormItem>> selectOmoformsByString(String word) {
        List<String> errors = new LinkedList<>();
        List<TransportOmoFormItem> result = new LinkedList<>();
        try {
            jMorfSdk.getAllCharacteristicsOfForm(word).forEach(form -> {
                TransportOmoFormItem item = builderTransport.build(form);
                result.add(item);
            });
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

    public ServiceWorksResult<List<Long>> selectMorphologyCharacteristicsByString(String word) {
        List<String> errors = new LinkedList<>();
        List<Long> result = new ArrayList<>();
        try {
            result = jMorfSdk.getMorphologyCharacteristics(word);
        } catch (Throwable ex) {
            String message = "Cannot AllCharacteristicsOfForm for word: " + String.valueOf(word);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<TransportRefOmoFormItem>> selectRefOmoFormListByString(String word) {
        List<String> errors = new LinkedList<>();
        //List<Form> forms = new ArrayList<>();
        //RefOmoFormList result = new RefOmoFormList(forms);
        List<TransportRefOmoFormItem> result = new LinkedList<>();
        try {
            //result = jMorfSdk.getRefOmoFormList(word);
            jMorfSdk.getRefOmoFormList(word).copy().forEach(form -> {
                TransportRefOmoFormItem item = builderTransportRef.build(form);
                result.add(item);
            });
        } catch (Throwable ex) {
            String message = "Cannot RefOmoFormList for word: " + String.valueOf(word);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }

    public ServiceWorksResult<List<String>> selectStringInitialFormByString(String word) {
        List<String> errors = new LinkedList<>();
        List<String> result = new ArrayList<>();
        try {
            result = jMorfSdk.getStringInitialForm(word);
        } catch (Throwable ex) {
            String message = "Cannot StringInitialForm for word: " + String.valueOf(word);
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
            String message = "Cannot TypeOfSpeeches for word: " + String.valueOf(word);
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
            String message = "Cannot InitialForm for word: " + String.valueOf(word);
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
            String message = "Cannot DerivativeForm For word: " + String.valueOf(word)
                    + " with type of speeches: " + String.valueOf(typeOfSpeeches) +
                    " and morphology characteristics: " + String.valueOf(morphologyCharacteristics);
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
            String message = "Cannot DerivativeForm For word: " + String.valueOf(word)
                    + " with type of speeches: " + String.valueOf(typeOfSpeeches);
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
            String message = "Cannot DerivativeForm For word: " + String.valueOf(word)
                    + " with morphology characteristics: " + String.valueOf(morphologyCharacteristics);
            log.warn(message, ex);
            errors.add(message);
        }
        return new ServiceWorksResult<>(result, errors);
    }
}
