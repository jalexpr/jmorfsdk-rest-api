package ru.textanalysis.tawt.rest.common.api.response.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.textanalysis.common.rest.domain.response.item.ResponseItem;

import java.io.Serializable;

public class ExistInitialForm implements ResponseItem, Serializable {
    @ApiModelProperty(value = "если (isContainsInitialForm && isContainsNotInitialForm), то возвращает 0;" +
            " если isContainsInitialForm, то возвращает 1;" +
            " если isContainsNotInitialForm, то возвращает -1;" +
            " в любых других случаях возвращает -2.")
    @JsonProperty
    private Byte existInitialForm;

    public Byte getExistInitialForm() {
        return existInitialForm;
    }

    public void setExistInitialForm(Byte existInitialForm) {
        this.existInitialForm = existInitialForm;
    }
}
