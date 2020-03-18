package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;
import ru.textanalysis.tawt.ms.internal.ref.RefOmoFormList;

import java.io.Serializable;

public class SelectRefOmoformsList implements ResponseItem, Serializable {
    @ApiModelProperty(value = "Список RefOmoform по заданному слову")
    @JsonProperty
    private RefOmoFormList refOmoFormList;

    public RefOmoFormList getRefOmoFormList() {
        return refOmoFormList;
    }

    public void setRefOmoFormList(RefOmoFormList refOmoFormList) {
        this.refOmoFormList = refOmoFormList;
    }
}
