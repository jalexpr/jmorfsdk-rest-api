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
import ru.textanalysis.tawt.rest.server.api.request.ExistFormByStringRequest;
import ru.textanalysis.tawt.rest.server.api.response.ExistFormByStringResponse;
import ru.textanalysis.tawt.rest.server.api.response.SelectOmoformsByStringResponse;
import ru.textanalysis.tawt.rest.server.services.JMorfSdkService;
import ru.textanalysis.tawt.rest.server.services.ValidationService;

@RestController(value = "API для проверки")
@RequestMapping("/api/exist")
public class JmorfsdkExistControllerImpl {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final JMorfSdkService jMorfSdkService;
    private final ValidationService validationService;

    @Autowired
    public JmorfsdkExistControllerImpl(JMorfSdkService jMorfSdkService, ValidationService validationService) {
        this.jMorfSdkService = jMorfSdkService;
        this.validationService = validationService;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    public ResponseEntity<String> handleAnyError(Throwable e) {
        return WebErrorHelper.handleAnyErrorSync(e);
    }

    @ApiOperation(value = "Проверка на существование формы в словаре")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SelectOmoformsByStringResponse.class)})
    @RequestMapping(value = "is/form/exists/in/dictionary", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> isFormExistsInDictionary(@RequestBody ExistFormByStringRequest request) {
        ExistFormByStringResponse result = new ExistFormByStringResponse();

        result.getErrors().addAll(validationService.validationRequest(request));

        if (result.getErrors().isEmpty()) {
            ServiceWorksResult<Boolean> resultSelect = jMorfSdkService.isFormExistsInDictionary(request.getWord());
            result.createEmptyData();
            result.getData().setExist(resultSelect.getResult());
            if (!resultSelect.getErrorMessage().isEmpty()) {
                result.getErrors().addAll(resultSelect.getErrorMessage());
            }
        }

        result.setSuccess(result.getErrors().isEmpty());
        return WebHelper.makeSuccessResult(result);
    }
}
