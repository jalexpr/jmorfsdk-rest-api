package ru.textanalysis.tawt.rest.common.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class SelectByStringWithTypeOfSpeechesAndMorphologyCharacteristicsRequest {
    @ApiModelProperty(value = "Слово", example = "слово")
    @JsonProperty
    private String word;

    @ApiModelProperty(value = "Часть речи")
    @JsonProperty
    private Byte typeOfSpeeches;

    @ApiModelProperty(value = "Морфологические характеристики")
    @JsonProperty
    private Long morphologyCharacteristics;

    public String getWord() {
        return word;
    }

    public Byte getTypeOfSpeeches() {
        return typeOfSpeeches;
    }

    public Long getMorphologyCharacteristics() {
        return morphologyCharacteristics;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setTypeOfSpeeches(Byte typeOfSpeeches) {
        this.typeOfSpeeches = typeOfSpeeches;
    }

    public void setMorphologyCharacteristics(Long morphologyCharacteristics) {
        this.morphologyCharacteristics = morphologyCharacteristics;
    }
}
