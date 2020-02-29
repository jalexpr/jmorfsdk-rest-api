package ru.textanalysis.tawt.jmorfsdk.rest.api.selection.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;
import ru.textanalysis.tawt.ms.storage.OmoFormList;

import java.io.Serializable;

public class SelectOmoforms implements ResponseItem, Serializable {
    @ApiModelProperty(value = "Список омоформ для заданному слову")
    @JsonProperty
    private OmoFormList omoForms;

    public OmoFormList getOmoForms() {
        return omoForms;
    }

    public void setOmoForms(OmoFormList omoForms) {
        this.omoForms = omoForms;
    }
}
