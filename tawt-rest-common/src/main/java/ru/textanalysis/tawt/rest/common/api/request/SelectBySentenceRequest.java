package ru.textanalysis.tawt.rest.common.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class SelectBySentenceRequest {
    @ApiModelProperty(value = "Предложение")
    @JsonProperty
    private String sentence;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
