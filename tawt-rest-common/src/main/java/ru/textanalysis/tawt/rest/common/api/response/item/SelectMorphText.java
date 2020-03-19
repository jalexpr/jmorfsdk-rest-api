package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;
import ru.textanalysis.tawt.ms.storage.ref.RefParagraphList;

import java.io.Serializable;

public class SelectMorphText implements ResponseItem, Serializable {
    @ApiModelProperty(value = "Список MorphText по заданному тексту")
    @JsonProperty
    private RefParagraphList refParagraphList;

    public RefParagraphList getRefParagraphList() {
        return refParagraphList;
    }

    public void setRefParagraphList(RefParagraphList refParagraphList) {
        this.refParagraphList = refParagraphList;
    }
}
