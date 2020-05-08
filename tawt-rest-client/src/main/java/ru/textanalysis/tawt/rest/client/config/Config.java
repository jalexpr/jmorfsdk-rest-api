package ru.textanalysis.tawt.rest.client.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private String port;
    private String address;

    public Config(String address, String port) {
        this.port = port;
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
