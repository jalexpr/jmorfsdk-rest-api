package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;

import java.io.Serializable;
import java.util.List;

public class SelectTreeSentenceWithoutAmbiguity implements ResponseItem, Serializable {
    @ApiModelProperty(value = "TreeSentenceWithoutAmbiguity по заданному тексту")
    @JsonProperty
    private List<TransportBearingPhraseExtItem> bearingPhraseExtList;

    public List<TransportBearingPhraseExtItem> getBearingPhraseExtList() {
        return bearingPhraseExtList;
    }

    public void setBearingPhraseExtList(List<TransportBearingPhraseExtItem> bearingPhraseExtList) {
        this.bearingPhraseExtList = bearingPhraseExtList;
    }
}
