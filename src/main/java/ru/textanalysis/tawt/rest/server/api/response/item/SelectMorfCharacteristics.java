package ru.textanalysis.tawt.rest.server.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;

import java.io.Serializable;
import java.util.List;

public class SelectMorfCharacteristics implements ResponseItem, Serializable {
    @ApiModelProperty(value = "Список омоформ для заданному слову")
    @JsonProperty
    private List<Long> morfCharacteristics;

    public List<Long> getMorfCharacteristics() {
        return morfCharacteristics;
    }

    public void setMorfCharacteristics(List<Long> morfCharacteristics) {
        this.morfCharacteristics = morfCharacteristics;
    }
}
