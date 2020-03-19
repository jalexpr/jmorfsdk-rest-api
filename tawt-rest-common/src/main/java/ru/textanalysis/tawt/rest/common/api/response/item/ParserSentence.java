package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;

import java.io.Serializable;
import java.util.List;

public class ParserSentence implements ResponseItem, Serializable {
    @ApiModelProperty(value = "ParserSentence по заданному предложению")
    @JsonProperty
    private List<List<String>> stringList;

    public List<List<String>> getStringList() {
        return stringList;
    }

    public void setStringList(List<List<String>> stringList) {
        this.stringList = stringList;
    }
}
