package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;

import java.io.Serializable;
import java.util.List;

public class SelectMorphParagraph implements ResponseItem, Serializable {
    @ApiModelProperty(value = "Список MorphParagraph по заданному параграфу")
    @JsonProperty
    private List<List<List<List<TransportRefOmoFormItem>>>> refSentenceList;

    public List<List<List<List<TransportRefOmoFormItem>>>> getRefSentenceList() {
        return refSentenceList;
    }

    public void setRefSentenceList(List<List<List<List<TransportRefOmoFormItem>>>> refSentenceList) {
        this.refSentenceList = refSentenceList;
    }
}
