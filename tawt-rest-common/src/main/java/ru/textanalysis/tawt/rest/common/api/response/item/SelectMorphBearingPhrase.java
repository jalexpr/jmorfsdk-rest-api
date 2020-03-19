package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;
import ru.textanalysis.tawt.ms.storage.ref.RefWordList;

import java.io.Serializable;

public class SelectMorphBearingPhrase implements ResponseItem, Serializable {
    @ApiModelProperty(value = "Список MorphBearingPhrase по заданной несущей фразе")
    @JsonProperty
    private RefWordList refWordList;

    public RefWordList getRefWordList() {
        return refWordList;
    }

    public void setRefWordList(RefWordList refWordList) {
        this.refWordList = refWordList;
    }
}
