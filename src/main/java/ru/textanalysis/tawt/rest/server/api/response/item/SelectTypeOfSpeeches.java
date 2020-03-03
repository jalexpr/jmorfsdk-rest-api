package ru.textanalysis.tawt.rest.server.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;

import java.io.Serializable;
import java.util.List;

public class SelectTypeOfSpeeches implements ResponseItem, Serializable {
    @ApiModelProperty(value = "Список TypeOfSpeeches по заданному слову")
    @JsonProperty
    private List<Byte> byteList;

    public List<Byte> getByteList() {
        return byteList;
    }

    public void setByteList(List<Byte> byteList) {
        this.byteList = byteList;
    }
}
