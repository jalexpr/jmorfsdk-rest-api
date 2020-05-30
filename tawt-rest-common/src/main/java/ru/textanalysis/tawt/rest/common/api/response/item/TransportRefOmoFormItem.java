package ru.textanalysis.tawt.rest.common.api.response.item;

import ru.textanalysis.tawt.ms.internal.TypeForms;

import java.io.Serializable;

public class TransportRefOmoFormItem implements Serializable {
    private Integer initialFormKey;
    private String initialFormString;
    private Byte typeOfSpeech;
    private Long morfCharacteristics;
    private Integer formKeyInBD;
    private Integer typeFormId;
    private TypeForms typeForm;
    private Boolean isInitialForm;
    private String myString;
    private Integer order;
    private Integer orderInitialForm;

    public Integer getOrderInitialForm() {
        return orderInitialForm;
    }

    public void setOrderInitialForm(Integer orderInitialForm) {
        this.orderInitialForm = orderInitialForm;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public TypeForms getTypeForm() {
        return typeForm;
    }

    public void setTypeForm(TypeForms typeForm) {
        this.typeForm = typeForm;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myformKey) {
        this.myString = myformKey;
    }

    public Boolean isInitialForm() {
        return isInitialForm;
    }

    public void setInitialForm(Boolean initialForm) {
        isInitialForm = initialForm;
    }

    public Integer getTypeFormId() {
        return typeFormId;
    }

    public void setTypeFormId(Integer typeFormId) {
        this.typeFormId = typeFormId;
    }

    public Integer getFormKeyInBD() {
        return formKeyInBD;
    }

    public void setFormKeyInBD(Integer formKeyInBD) {
        this.formKeyInBD = formKeyInBD;
    }

    public int getInitialFormKey() {
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
