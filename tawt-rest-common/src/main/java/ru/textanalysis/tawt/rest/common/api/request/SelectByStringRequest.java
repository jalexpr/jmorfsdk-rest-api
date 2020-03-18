package ru.textanalysis.tawt.rest.common.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class SelectByStringRequest {
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
