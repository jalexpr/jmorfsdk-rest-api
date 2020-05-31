package ru.textanalysis.tawt.rest.client.services;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.textanalysis.tawt.rest.client.config.Config;
import ru.textanalysis.tawt.rest.common.api.response.VersionResponse;
import ru.textanalysis.tawt.rest.common.exception.TawtRestRuntimeException;

import java.util.Objects;

@Service
@SuppressWarnings("FieldCanBeLocal")
public class ValidationService implements InitializingBean {
    private final String version = "508bd1f04f";
    private final String SERVICE_URL;
    private final String URN_CHECK_VERSION_SERVICE = "/api/version";
    private final RestTemplate restTemplate;

    public ValidationService(RestTemplate restTemplate,
                             Config config) {
        this.SERVICE_URL = config.getUrl();
        this.restTemplate = restTemplate;
    }

    @Override
    public void afterPropertiesSet() throws TawtRestRuntimeException {
        String url = SERVICE_URL + URN_CHECK_VERSION_SERVICE;

        ResponseEntity<VersionResponse> response = restTemplate.getForEntity(url, VersionResponse.class);

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            String message = String.format("Error connected to %s", url);
            throw new TawtRestRuntimeException(message);
        }

        if (!Objects.equals(getClass().getPackage().getImplementationVersion(), response.getBody().getVersion())
                && !Objects.equals(version, response.getBody().getVersion())) {
            throw new TawtRestRuntimeException(String.format("Please, test version client because version client {%s} and version server {%s}",
                    getClass().getPackage().getImplementationVersion(), response.getBody().getVersion()));
        }
    }
}
