package ru.textanalysis.tawt.rest.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.common.rest.services.RestClientService;
import ru.textanalysis.tawt.ms.internal.BuilderTransportBase;
import ru.textanalysis.tawt.ms.internal.IOmoForm;
import ru.textanalysis.tawt.ms.internal.form.Form;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.ms.internal.ref.RefOmoFormList;
import ru.textanalysis.tawt.ms.storage.OmoFormList;
import ru.textanalysis.tawt.rest.common.api.request.*;
import ru.textanalysis.tawt.rest.common.api.response.*;
import ru.textanalysis.tawt.rest.common.exception.TawtRestRuntimeException;

import java.util.LinkedList;
import java.util.List;

@Lazy
@Service
public class JMorfSdkRemoteService {
    private final static String SERVICE_NAME = "http://localhost:30002/tawt-rest-api";
    private final static String URN_SELECT_OMOFORMS_BY_STRING = "api/jmorfsdk/get/all/characteristics/of/form";
    private final static String URN_SELECT_MORPHOLOGY_CHARACTERISTICS_BY_STRING = "api/jmorfsdk/get/morphology/characteristics";
    private final static String URN_SELECT_REFOMOFORMLIST_BY_STRING = "api/jmorfsdk/get/ref/omo/form/list";
    private final static String URN_SELECT_STRING_INITIAL_FORM_BY_STRING = "api/jmorfsdk/get/string/initial/form";
    private final static String URN_SELECT_TYPE_OF_SPEECHES_BY_STRING = "api/jmorfsdk/get/type/of/speeches";
    private final static String URN_SELECT_DERIVATIVE_FORM_WITH_TYPE_OF_SPEECHES_AND_MORPH_CHARACTERISTICS_BY_STRING = "api/jmorfsdk/get/derivative/form/with/type/of/speeches/and/morph/characteristics";
    private final static String URN_SELECT_DERIVATIVE_FORM_WITH_TYPE_OF_SPEECHES_BY_STRING = "api/jmorfsdk/get/derivative/form/with/type/of/speeches";
    private final static String URN_SELECT_DERIVATIVE_FORM_WITH_MORPH_CHARACTERISTICS_BY_STRING = "api/jmorfsdk/get/derivative/form/with/morph/characteristics";
    private final static String URN_IS_FORM_EXISTS_IN_DICTIONARY_BY_STRING = "api/jmorfsdk/is/form/exists/in/dictionary";
    private final static String URN_IS_INITIAL_FORM_BY_STRING = "api/jmorfsdk/is/initial/form";

    private final RestClientService restClientService;
    private final BuilderTransportBase builderTransport = new BuilderTransportBase();
    private final BuilderTransportRef builderTransportRef = new BuilderTransportRef();

    @Autowired
    public JMorfSdkRemoteService(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

    /**
     * Получение списка омоформ по заданному слову.
     * @param word слово
     * @return ServiceWorksResult<OmoFormList>
     */
    public ServiceWorksResult<OmoFormList> getAllCharacteristicsOfForm(String word) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(word);

        SelectOmoformsByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_SELECT_OMOFORMS_BY_STRING,
                        request, SelectOmoformsByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_SELECT_OMOFORMS_BY_STRING, word);
            throw new TawtRestRuntimeException(message);
        }

        OmoFormList result = new OmoFormList();
        response.getData().getOmoForms().forEach(item -> {
            IOmoForm iOmoForm = builderTransport.build(item);
            result.add(iOmoForm);
        });

        return new ServiceWorksResult<>(result, response.getErrors());
    }

    /**
     * Получение морфологических характеристик по заданному слову
     * @param word слово
     * @return ServiceWorksResult<List<Long>>
     */
    public ServiceWorksResult<List<Long>> getMorphologyCharacteristics(String word) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(word);

        SelectMorfCharacteristicsByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_SELECT_MORPHOLOGY_CHARACTERISTICS_BY_STRING,
                        request, SelectMorfCharacteristicsByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_SELECT_MORPHOLOGY_CHARACTERISTICS_BY_STRING, word);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getMorfCharacteristics(), response.getErrors());
    }

    /**
     * Получение списка рефомоформ по заданному слову.
     * @param word слово
     * @return ServiceWorksResult<RefOmoFormList>
     */
    public ServiceWorksResult<RefOmoFormList> getRefOmoFormList(String word) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(word);

        SelectRefOmoFormListByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_SELECT_REFOMOFORMLIST_BY_STRING,
                        request, SelectRefOmoFormListByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_SELECT_REFOMOFORMLIST_BY_STRING, word);
            throw new TawtRestRuntimeException(message);
        }

        List<Form> forms = new LinkedList<>();
        response.getData().getRefOmoForms().forEach(item -> {
            Form form = builderTransportRef.build(item);
            forms.add(form);
        });
        RefOmoFormList result = new RefOmoFormList(forms);

        return new ServiceWorksResult<>(result, response.getErrors());
    }

    /**
     * Получение списка начальных форм по заданному слову.
     * @param word слово
     * @return ServiceWorksResult<List<String>>
     */
    public ServiceWorksResult<List<String>> getStringInitialForm(String word) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(word);

        SelectStringInitialFormByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_SELECT_STRING_INITIAL_FORM_BY_STRING,
                        request, SelectStringInitialFormByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_SELECT_STRING_INITIAL_FORM_BY_STRING, word);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getStringList(), response.getErrors());
    }

    /**
     * Получение списка частей речи по заданному слову.
     * @param word слово
     * @return ServiceWorksResult<List<Byte>>
     */
    public ServiceWorksResult<List<Byte>> getTypeOfSpeeches(String word) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(word);

        SelectTypeOfSpeechesByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_SELECT_TYPE_OF_SPEECHES_BY_STRING,
                        request, SelectTypeOfSpeechesByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_SELECT_TYPE_OF_SPEECHES_BY_STRING, word);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getByteList(), response.getErrors());
    }

    /**
     * Получение списка производных форм по заданному слову, части речи и морфологическим характеристикам
     * @param word слово
     * @param typeOfSpeeches часть речи
     * @param morphologyCharacteristics морфологические характеристики
     * @return ServiceWorksResult<List<String>>
     */
    public ServiceWorksResult<List<String>> getDerivativeForm(String word, Byte typeOfSpeeches, Long morphologyCharacteristics) {
        SelectByStringWithTypeOfSpeechesAndMorphologyCharacteristicsRequest request = new SelectByStringWithTypeOfSpeechesAndMorphologyCharacteristicsRequest();
        request.setWord(word);
        request.setTypeOfSpeeches(typeOfSpeeches);
        request.setMorphologyCharacteristics(morphologyCharacteristics);

        SelectDerivativeFormByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_SELECT_DERIVATIVE_FORM_WITH_TYPE_OF_SPEECHES_AND_MORPH_CHARACTERISTICS_BY_STRING,
                        request, SelectDerivativeFormByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_SELECT_DERIVATIVE_FORM_WITH_TYPE_OF_SPEECHES_AND_MORPH_CHARACTERISTICS_BY_STRING, word);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getStringList(), response.getErrors());
    }

    /**
     * Получение списка производных форм по заданному слову и части речи
     * @param word слово
     * @param typeOfSpeeches чать речи
     * @return ServiceWorksResult<List<String>>
     */
    public ServiceWorksResult<List<String>> getDerivativeForm(String word, Byte typeOfSpeeches) {
        SelectByStringWithTypeOfSpeechesRequest request = new SelectByStringWithTypeOfSpeechesRequest();
        request.setWord(word);
        request.setTypeOfSpeeches(typeOfSpeeches);

        SelectDerivativeFormByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_SELECT_DERIVATIVE_FORM_WITH_TYPE_OF_SPEECHES_BY_STRING,
                        request, SelectDerivativeFormByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_SELECT_DERIVATIVE_FORM_WITH_TYPE_OF_SPEECHES_BY_STRING, word);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getStringList(), response.getErrors());
    }

    /**
     * Получение списка производных форм по заданному слову и морфологическим характеристикам
     * @param word слово
     * @param morphologyCharacteristics морфологические характеристики
     * @return ServiceWorksResult<List<String>>
     */
    public ServiceWorksResult<List<String>> getDerivativeForm(String word, Long morphologyCharacteristics) {
        SelectByStringWithMorphologyCharacteristicsRequest request = new SelectByStringWithMorphologyCharacteristicsRequest();
        request.setWord(word);
        request.setMorphologyCharacteristics(morphologyCharacteristics);

        SelectDerivativeFormByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_SELECT_DERIVATIVE_FORM_WITH_MORPH_CHARACTERISTICS_BY_STRING,
                        request, SelectDerivativeFormByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_SELECT_DERIVATIVE_FORM_WITH_MORPH_CHARACTERISTICS_BY_STRING, word);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getStringList(), response.getErrors());
    }

    /**
     * Проверка на существование формы в словаре.
     * @param word слово
     * @return ServiceWorksResult<Boolean>
     */
    public ServiceWorksResult<Boolean> isFormExistsInDictionary(String word) {
        ExistFormByStringRequest request = new ExistFormByStringRequest();
        request.setWord(word);

        ExistFormByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_IS_FORM_EXISTS_IN_DICTIONARY_BY_STRING,
                        request, ExistFormByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_IS_FORM_EXISTS_IN_DICTIONARY_BY_STRING, word);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getExist(), response.getErrors());
    }

    /**
     * Проверка на начальную форму заданного слова.
     * @param word слово
     * @return ServiceWorksResult<Byte>
     */
    public ServiceWorksResult<Byte> isInitialForm(String word) {
        ExistFormByStringRequest request = new ExistFormByStringRequest();
        request.setWord(word);

        ExistInitialFormByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_IS_INITIAL_FORM_BY_STRING,
                        request, ExistInitialFormByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_IS_INITIAL_FORM_BY_STRING, word);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getExistInitialForm(), response.getErrors());
    }
}
