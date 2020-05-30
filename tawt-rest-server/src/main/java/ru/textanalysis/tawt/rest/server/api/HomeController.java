package ru.textanalysis.tawt.rest.server.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.textanalysis.common.rest.utils.JsonHelper;
import ru.textanalysis.tawt.rest.common.api.response.VersionResponse;

@RestController
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "Welcome to TAWT REST API <br>" +
                "<a href='/tawt-rest-api/swagger-ui.html'>API in swagger</a>";
    }

    @ApiOperation(value = "Получить версию сервиса")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = VersionResponse.class)})
    @RequestMapping(value = "/api/version", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> version() {
        VersionResponse versionResponse = new VersionResponse();
        versionResponse.setVersion(getClass().getPackage().getImplementationVersion());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(JsonHelper.writeAsString(versionResponse), headers, HttpStatus.OK);
    }
}

