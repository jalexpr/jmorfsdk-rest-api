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
import ru.textanalysis.tawt.rest.common.api.response.ParserBasicsPhaseByStringResponse;
import ru.textanalysis.tawt.rest.common.api.response.ParserParagraphByStringResponse;
import ru.textanalysis.tawt.rest.common.api.response.ParserSentenceByStringResponse;
import ru.textanalysis.tawt.rest.common.api.response.ParserTextByStringResponse;
import ru.textanalysis.tawt.rest.server.services.GraphematicParserService;
import ru.textanalysis.tawt.rest.server.services.ValidationService;

import java.util.List;

@RestController(value = "API для выборки parser")
@RequestMapping("/api/gp")
public class GraphematicParserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final GraphematicParserService parserService;
    private final ValidationService validationService;

    @Autowired
    public GraphematicParserController(GraphematicParserService parserService, ValidationService validationService) {
        this.parserService = parserService;
        this.validationService = validationService;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    public ResponseEntity<String> handleAnyError(Throwable e) {
        return WebErrorHelper.handleAnyErrorSync(e);
    }

    @ApiOperation(value = "parserBasicsPhase по заданной фразе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ParserBasicsPhaseByStringResponse.class)})
    @RequestMapping(value = "parser/basics/phase", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> parserBasicsPhase(@RequestBody SelectByStringRequest request) {
        ParserBasicsPhaseByStringResponse result = new ParserBasicsPhaseByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<String>> resultSelect = parserService.parserBasicsPhaseByString(request.getText());
            result.createEmptyData();
            result.getData().setStringList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "parserSentence по заданному предложению")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ParserSentenceByStringResponse.class)})
    @RequestMapping(value = "parser/sentence", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> parserSentence(@RequestBody SelectByStringRequest request) {
        ParserSentenceByStringResponse result = new ParserSentenceByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<List<String>>> resultSelect = parserService.parserSentenceByString(request.getText());
            result.createEmptyData();
            result.getData().setStringList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "parserParagraph по заданному параграфу")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ParserParagraphByStringResponse.class)})
    @RequestMapping(value = "parser/paragraph", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> parserParagraph(@RequestBody SelectByStringRequest request) {
        ParserParagraphByStringResponse result = new ParserParagraphByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<List<List<String>>>> resultSelect = parserService.parserParagraphByString(request.getText());
            result.createEmptyData();
            result.getData().setStringList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }

    @ApiOperation(value = "parserText по заданному тексту")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ParserTextByStringResponse.class)})
    @RequestMapping(value = "parser/text", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> parserText(@RequestBody SelectByStringRequest request) {
        ParserTextByStringResponse result = new ParserTextByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<List<List<List<List<String>>>>> resultSelect = parserService.parserTextByString(request.getText());
            result.createEmptyData();
            result.getData().setStringList(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }
}
