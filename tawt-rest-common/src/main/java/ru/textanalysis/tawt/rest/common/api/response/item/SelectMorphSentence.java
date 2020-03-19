package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;
import ru.textanalysis.tawt.ms.storage.ref.RefBearingPhraseList;

import java.io.Serializable;

public class SelectMorphSentence implements ResponseItem, Serializable {
    @ApiModelProperty(value = "Список MorphSentence по заданному предложению")
    @JsonProperty
    private RefBearingPhraseList refBearingPhraseList;

    public RefBearingPhraseList getRefBearingPhraseList() {
        return refBearingPhraseList;
    }

    public void setRefBearingPhraseList(RefBearingPhraseList refBearingPhraseList) {
        this.refBearingPhraseList = refBearingPhraseList;
    }
}
