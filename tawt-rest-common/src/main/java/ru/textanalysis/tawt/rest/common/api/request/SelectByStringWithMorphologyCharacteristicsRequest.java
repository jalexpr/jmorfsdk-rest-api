package ru.textanalysis.tawt.rest.common.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class SelectByStringWithMorphologyCharacteristicsRequest {
    @ApiModelProperty(value = "Слово", example = "слово")
    @JsonProperty
    private String word;

    @ApiModelProperty(value = "Морфологические характеристики")
    @JsonProperty
    private Long morphologyCharacteristics;

    public String getWord() {
        return word;
    }

    public Long getMorphologyCharacteristics() {
        return morphologyCharacteristics;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setMorphologyCharacteristics(Long morphologyCharacteristics) {
        this.morphologyCharacteristics = morphologyCharacteristics;
    }
}
