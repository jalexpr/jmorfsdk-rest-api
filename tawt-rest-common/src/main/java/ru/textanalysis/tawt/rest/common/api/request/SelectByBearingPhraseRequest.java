package ru.textanalysis.tawt.rest.common.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class SelectByBearingPhraseRequest {
    @ApiModelProperty(value = "Опорный оборот", example = "опорный оборот")
    @JsonProperty
    private String bearingPhrase;

    public String getBearingPhrase() {
        return bearingPhrase;
    }

    public void setBearingPhrase(String bearingPhrase) {
        this.bearingPhrase = bearingPhrase;
    }
}
