package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;

import java.io.Serializable;
import java.util.List;

public class ParserParagraph implements ResponseItem, Serializable {
    @ApiModelProperty(value = "ParserParagraph по заданному параграфу")
    @JsonProperty
    private List<List<List<String>>> stringList;

    public List<List<List<String>>> getStringList() {
        return stringList;
    }

    public void setStringList(List<List<List<String>>> stringList) {
        this.stringList = stringList;
    }
}
