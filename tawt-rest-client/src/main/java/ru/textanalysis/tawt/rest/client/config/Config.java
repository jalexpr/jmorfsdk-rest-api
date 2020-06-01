package ru.textanalysis.tawt.rest.client.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "remote")
@ComponentScan(basePackageClasses = {
        ru.textanalysis.common.rest.services.RestClientService.class,
        ru.textanalysis.tawt.ms.internal.BuilderTransportBase.class,
        ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef.class,
        ru.textanalysis.tawt.ms.internal.sp.BuilderTransportSP.class
})
public class Config implements InitializingBean {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url + "/tawt-rest-api";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (url == null || url.trim().isEmpty()) {
            throw new Exception("url is empty");
        }
    }
}
