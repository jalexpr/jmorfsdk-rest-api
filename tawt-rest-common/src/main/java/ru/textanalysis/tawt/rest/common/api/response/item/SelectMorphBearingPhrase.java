package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;

import java.io.Serializable;
import java.util.List;

public class SelectMorphBearingPhrase implements ResponseItem, Serializable {
    @ApiModelProperty(value = "Список MorphBearingPhrase по заданной несущей фразе")
    @JsonProperty
    private List<List<TransportRefOmoFormItem>> refOmoForms;

    public List<List<TransportRefOmoFormItem>> getRefOmoForms() {
        return refOmoForms;
    }

    public void setRefOmoForms(List<List<TransportRefOmoFormItem>> refOmoForms) {
        this.refOmoForms = refOmoForms;
    }
}
