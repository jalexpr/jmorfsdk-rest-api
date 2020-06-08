package ru.textanalysis.tawt.rest.common.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class SelectByStringWithTypeOfSpeechesRequest {
    @ApiModelProperty(value = "Слово", example = "слово")
    @JsonProperty
    private String word;

    @ApiModelProperty(value = "Часть речи")
    @JsonProperty
    private Byte typeOfSpeeches;

    public String getWord() {
        return word;
    }

    public Byte getTypeOfSpeeches() {
        return typeOfSpeeches;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setTypeOfSpeeches(Byte typeOfSpeeches) {
        this.typeOfSpeeches = typeOfSpeeches;
    }

}
