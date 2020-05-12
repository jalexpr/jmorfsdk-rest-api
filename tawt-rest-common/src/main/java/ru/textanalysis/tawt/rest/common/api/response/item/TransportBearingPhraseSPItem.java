package ru.textanalysis.tawt.rest.common.api.response.item;

import java.util.List;

public class TransportBearingPhraseSPItem {
    private List<TransportRefOmoFormItem> mainOmoForms;
    private List<TransportOmoFormSPItem> OmoFormSPList;

    public List<TransportOmoFormSPItem> getOmoFormSPList() {
        return OmoFormSPList;
    }

    public void setOmoFormSPList(List<TransportOmoFormSPItem> omoFormSPList) {
        OmoFormSPList = omoFormSPList;
    }

    public List<TransportRefOmoFormItem> getMainOmoForms() {
        return mainOmoForms;
    }

    public void setMainOmoForms(List<TransportRefOmoFormItem> mainOmoForms) {
        this.mainOmoForms = mainOmoForms;
    }
}
