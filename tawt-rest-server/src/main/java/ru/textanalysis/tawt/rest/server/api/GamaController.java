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
import ru.textanalysis.tawt.ms.internal.ref.RefOmoFormList;
import ru.textanalysis.tawt.ms.storage.ref.RefBearingPhraseList;
import ru.textanalysis.tawt.ms.storage.ref.RefParagraphList;
import ru.textanalysis.tawt.ms.storage.ref.RefSentenceList;
import ru.textanalysis.tawt.ms.storage.ref.RefWordList;
import ru.textanalysis.tawt.rest.common.api.request.SelectByStringRequest;
import ru.textanalysis.tawt.rest.common.api.response.*;
import ru.textanalysis.tawt.rest.server.services.GamaService;
import ru.textanalysis.tawt.rest.server.services.ValidationService;

@RestController(value = "API для выборки gama")
@RequestMapping("/api/gama")
public class GamaController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final GamaService gamaService;
    private final ValidationService validationService;

    @Autowired
    public GamaController(GamaService gamaService, ValidationService validationService) {
        this.gamaService = gamaService;
        this.validationService = validationService;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    public ResponseEntity<String> handleAnyError(Throwable e) {
        return WebErrorHelper.handleAnyErrorSync(e);
    }

    @ApiOperation(value = "Получение MorphWord по заданному слову")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectMorphWordByStringResponse.class)})
    @RequestMapping(value = "get/morph/word", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMorphWord(@RequestBody SelectByStringRequest request) {
        SelectMorphWordByStringResponse result = new SelectMorphWordByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<RefOmoFormList> resultSelect = gamaService.selectMorphWordByString(request.getText());
            result.createEmptyData();
            result.getData().setRefOmoFormList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Получение MorphBearingPhrase по заданной несущей фразе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectMorphBearingPhraseByStringResponse.class)})
    @RequestMapping(value = "get/morph/bearing/phrase", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMorphBearingPhrase(@RequestBody SelectByStringRequest request) {
        SelectMorphBearingPhraseByStringResponse result = new SelectMorphBearingPhraseByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<RefWordList> resultSelect = gamaService.selectMorphBearingPhraseByString(request.getText());
            result.createEmptyData();
            result.getData().setRefWordList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Получение MorphParagraph по заданному параграфу")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectMorphParagraphByStringResponse.class)})
    @RequestMapping(value = "get/morph/paragraph", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMorphParagraph(@RequestBody SelectByStringRequest request) {
        SelectMorphParagraphByStringResponse result = new SelectMorphParagraphByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<RefSentenceList> resultSelect = gamaService.selectMorphParagraphByString(request.getText());
            result.createEmptyData();
            result.getData().setRefSentenceList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Получение MorphSentence по заданному предложению")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectMorphSentenceByStringResponse.class)})
    @RequestMapping(value = "get/morph/sentence", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMorphSentence(@RequestBody SelectByStringRequest request) {
        SelectMorphSentenceByStringResponse result = new SelectMorphSentenceByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<RefBearingPhraseList> resultSelect = gamaService.selectMorphSentenceByString(request.getText());
            result.createEmptyData();
            result.getData().setRefBearingPhraseList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Получение MorphText по заданному тексту")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectMorphTextByStringResponse.class)})
    @RequestMapping(value = "get/morph/text", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMorphText(@RequestBody SelectByStringRequest request) {
        SelectMorphTextByStringResponse result = new SelectMorphTextByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<RefParagraphList> resultSelect = gamaService.selectMorphTextByString(request.getText());
            result.createEmptyData();
            result.getData().setRefParagraphList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }


}
