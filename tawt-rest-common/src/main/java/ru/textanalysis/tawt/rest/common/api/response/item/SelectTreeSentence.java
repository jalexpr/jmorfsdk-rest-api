package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;
import ru.textanalysis.tawt.ms.internal.sp.BearingPhraseSP;

import java.io.Serializable;
import java.util.List;

public class SelectTreeSentence implements ResponseItem, Serializable {
    @ApiModelProperty(value = "TreeSentence по заданному тексту")
    @JsonProperty
    private List<BearingPhraseSP> bearingPhraseSPList;

    public List<BearingPhraseSP> getBearingPhraseSPList() {
        return bearingPhraseSPList;
    }

    public void setBearingPhraseSPList(List<BearingPhraseSP> bearingPhraseSPList) {
        this.bearingPhraseSPList = bearingPhraseSPList;
    }
}
