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
import ru.textanalysis.tawt.rest.common.api.request.SelectByStringRequest;
import ru.textanalysis.tawt.rest.common.api.response.SelectTreeSentenceByStringResponse;
import ru.textanalysis.tawt.rest.common.api.response.SelectTreeSentenceWithoutAmbiguityByStringResponse;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportBearingPhraseExtItem;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportBearingPhraseSPItem;
import ru.textanalysis.tawt.rest.server.services.SyntaxParserService;
import ru.textanalysis.tawt.rest.server.services.ValidationService;

import java.util.List;

@RestController(value = "API для выборки sp")
@RequestMapping("/api/sp")
public class SyntaxParserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SyntaxParserService spService;
    private final ValidationService validationService;

    @Autowired
    public SyntaxParserController(SyntaxParserService spService, ValidationService validationService) {
        this.spService = spService;
        this.validationService = validationService;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    public ResponseEntity<String> handleAnyError(Throwable e) {
        return WebErrorHelper.handleAnyErrorSync(e);
    }

    @ApiOperation(value = "Получение TreeSentence по заданному тексту")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectTreeSentenceByStringResponse.class)})
    @RequestMapping(value = "get/tree/sentence", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTreeSentence(@RequestBody SelectByStringRequest request) {
        SelectTreeSentenceByStringResponse result = new SelectTreeSentenceByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<TransportBearingPhraseSPItem>> resultSelect = spService.selectTreeSentenceByString(request.getText());
            result.createEmptyData();
            result.getData().setBearingPhraseSPList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "Получение TreeSentenceWithoutAmbiguity по заданному тексту")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectTreeSentenceWithoutAmbiguityByStringResponse.class)})
    @RequestMapping(value = "get/tree/sentence/without/ambiguity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTreeSentenceWithoutAmbiguity(@RequestBody SelectByStringRequest request) {
        SelectTreeSentenceWithoutAmbiguityByStringResponse result = new SelectTreeSentenceWithoutAmbiguityByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<TransportBearingPhraseExtItem>> resultSelect = spService.selectTreeSentenceWithoutAmbiguity(request.getText());
            result.createEmptyData();
            result.getData().setBearingPhraseExtList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }
}
