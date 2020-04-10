package ru.textanalysis.tawt.rest.common.api.response.item;

import ru.textanalysis.tawt.ms.internal.IOmoForm;
import ru.textanalysis.tawt.ms.internal.OmoForm;

import java.io.Serializable;

public class IOmoFormItem extends OmoForm implements Serializable {
    private Boolean isNumber;

    public IOmoFormItem() {
    }

    public IOmoFormItem(IOmoForm form) {
        this.initialFormKey = form.getInitialFormKey();
        this.myFormKey = form.getMyFormKey();
        this.typeOfSpeech = form.getTypeOfSpeech();
        this.morfCharacteristics = form.getAllMorfCharacteristics();
        this.isNumber = form.isNumber();
    }

    public boolean isNumber() {
        return isNumber;
    }

    public Boolean getNumber() {
        return isNumber;
    }

    public void setNumber(Boolean number) {
        isNumber = number;
    }
}
