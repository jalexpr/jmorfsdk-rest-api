package ru.textanalysis.tawt.rest.common.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class SelectByParagraphRequest {
    @ApiModelProperty(value = "Параграф")
    @JsonProperty
    private String paragraph;

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }
}
