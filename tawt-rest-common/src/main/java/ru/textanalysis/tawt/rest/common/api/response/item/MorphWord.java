package ru.textanalysis.tawt.rest.common.api.response.item;

import java.util.List;

public class MorphWord {
    private List<TransportRefOmoFormItem> refOmoForms;

    public List<TransportRefOmoFormItem> getRefOmoForms() {
        return refOmoForms;
    }

    public void setRefOmoForms(List<TransportRefOmoFormItem> refOmoForms) {
        this.refOmoForms = refOmoForms;
    }
}
