package ru.textanalysis.tawt.rest.common.api.response.item;

import java.io.Serializable;

public class TransportRefOmoFormItem implements Serializable {
    private Integer initialFormKey;
    private String initialFormString;
    private Byte typeOfSpeech;
    private Long morfCharacteristics;
    private Integer formKeyInBD;

    public Integer getFormKeyInBD() {
        return formKeyInBD;
    }

    public void setFormKeyInBD(Integer formKeyInBD) {
        this.formKeyInBD = formKeyInBD;
    }

    public Integer getInitialFormKey() {
        return initialFormKey;
    }

    public void setInitialFormKey(Integer initialFormKey) {
        this.initialFormKey = initialFormKey;
    }

    public String getInitialFormString() {
        return initialFormString;
    }

    public void setInitialFormString(String initialFormString) {
        this.initialFormString = initialFormString;
    }

    public Byte getTypeOfSpeech() {
        return typeOfSpeech;
    }

    public void setTypeOfSpeech(Byte typeOfSpeech) {
        this.typeOfSpeech = typeOfSpeech;
    }

    public Long getMorfCharacteristics() {
        return morfCharacteristics;
    }

    public void setMorfCharacteristics(Long morfCharacteristics) {
        this.morfCharacteristics = morfCharacteristics;
    }


}
