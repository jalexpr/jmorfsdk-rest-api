package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;

import java.io.Serializable;
import java.util.List;

public class SelectMorphText implements ResponseItem, Serializable {
    @ApiModelProperty(value = "Список MorphText по заданному тексту")
    @JsonProperty
    private List<List<List<List<List<TransportRefOmoFormItem>>>>> refParagraphList;

    public List<List<List<List<List<TransportRefOmoFormItem>>>>> getRefParagraphList() {
        return refParagraphList;
    }

    public void setRefParagraphList(List<List<List<List<List<TransportRefOmoFormItem>>>>> refParagraphList) {
        this.refParagraphList = refParagraphList;
    }
}
