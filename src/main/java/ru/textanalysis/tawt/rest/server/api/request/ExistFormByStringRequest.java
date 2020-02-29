package ru.textanalysis.tawt.rest.server.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class ExistFormByStringRequest {
    @ApiModelProperty(value = "Слово")
    @JsonProperty
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
