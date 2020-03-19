package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;
import ru.textanalysis.tawt.ms.storage.ref.RefSentenceList;

import java.io.Serializable;

public class SelectMorphParagraph implements ResponseItem, Serializable {
    @ApiModelProperty(value = "Список MorphParagraph по заданному параграфу")
    @JsonProperty
    private RefSentenceList refSentenceList;

    public RefSentenceList getRefSentenceList() {
        return refSentenceList;
    }

    public void setRefSentenceList(RefSentenceList refSentenceList) {
        this.refSentenceList = refSentenceList;
    }
}
