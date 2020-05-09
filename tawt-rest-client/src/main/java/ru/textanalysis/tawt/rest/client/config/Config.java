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
    private String address;
    private Integer port;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (address == null || address.trim().isEmpty() || port == null || port < 0 || port > 65535) {
            throw new Exception("Address or port is empty");
        }
    }
}
