package ru.textanalysis.tawt.rest.common.api.response.item;

import java.util.List;

public class TransportBearingPhraseExtItem {
    private List<TransportRefOmoFormItem> mainOmoForms;

    public List<TransportRefOmoFormItem> getMainOmoForms() {
        return mainOmoForms;
    }

    public void setMainOmoForms(List<TransportRefOmoFormItem> mainOmoForms) {
        this.mainOmoForms = mainOmoForms;
    }
}
