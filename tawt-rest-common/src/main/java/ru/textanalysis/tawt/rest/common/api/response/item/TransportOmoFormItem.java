package ru.textanalysis.tawt.rest.common.api.response.item;

import ru.textanalysis.tawt.ms.internal.OmoForm;

import java.io.Serializable;
import java.util.LinkedList;

public class TransportOmoFormItem implements Serializable {
    private Integer initialFormKey;
    private Integer myFormKey;
    private Byte typeOfSpeech;
    private Long morfCharacteristics;
    private LinkedList<OmoForm> myDependent;
    private LinkedList<OmoForm> myMain;
    private Integer typeForm;
    private String stringForm;

    public Integer getTypeForm() {
        return typeForm;
    }

    public void setTypeForm(Integer typeForm) {
        this.typeForm = typeForm;
    }

    public Integer getInitialFormKey() {
        return initialFormKey;
    }

    public void setInitialFormKey(Integer initialFormKey) {
        this.initialFormKey = initialFormKey;
    }

    public Integer getMyFormKey() {
        return myFormKey;
    }

    public void setMyFormKey(Integer myFormKey) {
        this.myFormKey = myFormKey;
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

    public LinkedList<OmoForm> getMyDependent() {
        return myDependent;
    }

    public void setMyDependent(LinkedList<OmoForm> myDependent) {
        this.myDependent = myDependent;
    }

    public LinkedList<OmoForm> getMyMain() {
        return myMain;
    }

    public void setMyMain(LinkedList<OmoForm> myMain) {
        this.myMain = myMain;
    }

    public String getStringForm() {
        return stringForm;
    }

    public void setStringForm(String stringForm) {
        this.stringForm = stringForm;
    }
}
