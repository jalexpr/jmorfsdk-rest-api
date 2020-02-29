package ru.textanalysis.tawt.rest.server.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;

import java.io.Serializable;

public class ExistForm implements ResponseItem, Serializable {
    @ApiModelProperty(value = "truue если существует, false если не существует")
    @JsonProperty
    private Boolean exist;

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }
}
