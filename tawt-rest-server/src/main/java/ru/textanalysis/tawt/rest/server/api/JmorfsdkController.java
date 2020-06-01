package ru.textanalysis.tawt.rest.server.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.common.rest.utils.WebErrorHelper;
import ru.textanalysis.common.rest.utils.WebHelper;
import ru.textanalysis.tawt.rest.common.api.request.*;
import ru.textanalysis.tawt.rest.common.api.response.*;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportOmoFormItem;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportRefOmoFormItem;
import ru.textanalysis.tawt.rest.server.services.JMorfSdkService;
import ru.textanalysis.tawt.rest.server.services.ValidationService;

import java.util.List;

@RestController(value = "API для выборки jmorfsdk")
@RequestMapping("/api/jmorfsdk")
public class JmorfsdkController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final JMorfSdkService jMorfSdkService;
    private final ValidationService validationService;

    @Autowired
    public JmorfsdkController(JMorfSdkService jMorfSdkService, ValidationService validationService) {
        this.jMorfSdkService = jMorfSdkService;
        this.validationService = validationService;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    public ResponseEntity<String> handleAnyError(Throwable e) {
        return WebErrorHelper.handleAnyErrorSync(e);
    }

    @ApiOperation(value = "Получение списка омоформ по заданному слову")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectOmoformsByStringResponse.class)})
    @RequestMapping(value = "get/all/characteristics/of/form", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllCharacteristicsOfForm(@RequestBody SelectByWordRequest request) {
        SelectOmoformsByStringResponse result = new SelectOmoformsByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<TransportOmoFormItem>> resultSelect = jMorfSdkService.selectOmoformsByString(request.getWord().trim());
            result.createEmptyData();
            result.getData().setOmoForms(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Получить морфологические характеристики по заданному слову")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectMorfCharacteristicsByStringResponse.class)})
    @RequestMapping(value = "get/morphology/characteristics", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMorphologyCharacteristics(@RequestBody SelectByWordRequest request) {
        SelectMorfCharacteristicsByStringResponse result = new SelectMorfCharacteristicsByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<Long>> resultSelect = jMorfSdkService.selectMorphologyCharacteristicsByString(request.getWord().trim());
            result.createEmptyData();
            result.getData().setMorfCharacteristics(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Получение RefOmoFormList по заданному слову")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectRefOmoFormListByStringResponse.class)})
    @RequestMapping(value = "get/ref/omo/form/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRefOmoFormList(@RequestBody SelectByWordRequest request) {
        SelectRefOmoFormListByStringResponse result = new SelectRefOmoFormListByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<TransportRefOmoFormItem>> resultSelect = jMorfSdkService.selectRefOmoFormListByString(request.getWord().trim());
            result.createEmptyData();
            result.getData().setRefOmoForms(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Получение StringInitialForm по заданному слову")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectStringInitialFormByStringResponse.class)})
    @RequestMapping(value = "get/string/initial/form", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getStringInitialForm(@RequestBody SelectByWordRequest request) {
        SelectStringInitialFormByStringResponse result = new SelectStringInitialFormByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<String>> resultSelect = jMorfSdkService.selectStringInitialFormByString(request.getWord().trim());
            result.createEmptyData();
            result.getData().setStringList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Получение TypeOfSpeeches по заданному слову")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectTypeOfSpeechesByStringResponse.class)})
    @RequestMapping(value = "get/type/of/speeches", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTypeOfSpeeches(@RequestBody SelectByWordRequest request) {
        SelectTypeOfSpeechesByStringResponse result = new SelectTypeOfSpeechesByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<Byte>> resultSelect = jMorfSdkService.selectTypeOfSpeechesByString(request.getWord().trim());
            result.createEmptyData();
            result.getData().setByteList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Получение DerivativeForm по заданному слову с параметрами: часть речи и морфологические характеристики")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectDerivativeFormByStringResponse.class)})
    @RequestMapping(value = "get/derivative/form/with/type/of/speeches/and/morph/characteristics", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDerivativeForm(@RequestBody SelectByStringWithTypeOfSpeechesAndMorphologyCharacteristicsRequest request) {
        SelectDerivativeFormByStringResponse result = new SelectDerivativeFormByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<String>> resultSelect = jMorfSdkService.selectDerivativeFormByString(request.getWord().trim(),
                    request.getTypeOfSpeeches(), request.getMorphologyCharacteristics());
            result.createEmptyData();
            result.getData().setStringList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Получение DerivativeForm по заданному слову с параметром: часть речи")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectDerivativeFormByStringResponse.class)})
    @RequestMapping(value = "get/derivative/form/with/type/of/speeches", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDerivativeForm(@RequestBody SelectByStringWithTypeOfSpeechesRequest request) {
        SelectDerivativeFormByStringResponse result = new SelectDerivativeFormByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<String>> resultSelect = jMorfSdkService.selectDerivativeFormByString(request.getWord().trim(),
                    request.getTypeOfSpeeches());
            result.createEmptyData();
            result.getData().setStringList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Получение DerivativeForm по заданному слову с параметром: морфологические характеристики")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectDerivativeFormByStringResponse.class)})
    @RequestMapping(value = "get/derivative/form/with/morph/characteristics", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDerivativeForm(@RequestBody SelectByStringWithMorphologyCharacteristicsRequest request) {
        SelectDerivativeFormByStringResponse result = new SelectDerivativeFormByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<String>> resultSelect = jMorfSdkService.selectDerivativeFormByString(request.getWord().trim(),
                    request.getMorphologyCharacteristics());
            result.createEmptyData();
            result.getData().setStringList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Проверка на существование формы в словаре")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectOmoformsByStringResponse.class)})
    @RequestMapping(value = "is/form/exists/in/dictionary", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> isFormExistsInDictionary(@RequestBody ExistFormByStringRequest request) {
        ExistFormByStringResponse result = new ExistFormByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<Boolean> resultSelect = jMorfSdkService.isFormExistsInDictionary(request.getWord().trim());
            result.createEmptyData();
            result.getData().setExist(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Проверка на существование InitialForm в словаре")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ExistInitialFormByStringResponse.class)})
    @RequestMapping(value = "is/initial/form", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> isInitialForm(@RequestBody ExistFormByStringRequest request) {
        ExistInitialFormByStringResponse result = new ExistInitialFormByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<Byte> resultSelect = jMorfSdkService.isInitialForm(request.getWord().trim());
            result.createEmptyData();
            result.getData().setExistInitialForm(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }
}
