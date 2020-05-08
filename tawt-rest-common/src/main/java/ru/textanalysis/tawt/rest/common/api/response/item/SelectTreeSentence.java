package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;

import java.io.Serializable;
import java.util.List;

public class SelectTreeSentence implements ResponseItem, Serializable {
    @ApiModelProperty(value = "TreeSentence по заданному тексту")
    @JsonProperty
    private List<TransportBearingPhraseSPItem> bearingPhraseSPList;

    public List<TransportBearingPhraseSPItem> getBearingPhraseSPList() {
        return bearingPhraseSPList;
    }

    public void setBearingPhraseSPList(List<TransportBearingPhraseSPItem> bearingPhraseSPList) {
        this.bearingPhraseSPList = bearingPhraseSPList;
    }
}
