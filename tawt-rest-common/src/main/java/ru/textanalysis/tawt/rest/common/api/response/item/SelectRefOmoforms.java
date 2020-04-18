package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;

import java.io.Serializable;
import java.util.List;

public class SelectRefOmoforms implements ResponseItem, Serializable {
    @ApiModelProperty(value = "Список RefOmoform по заданному слову")
    @JsonProperty
    private List<TransportRefOmoFormItem> refOmoForms;

    public List<TransportRefOmoFormItem> getRefOmoForms() {
        return refOmoForms;
    }

    public void setRefOmoForms(List<TransportRefOmoFormItem> refOmoForms) {
        this.refOmoForms = refOmoForms;
    }
}
