package ru.textanalysis.tawt.rest.common.api.response.item;

import java.io.Serializable;
import java.util.List;

public class TransportBearingPhraseExtItem implements Serializable {
    private List<TransportOmoFormExtItem> mainOmoForms;

    public List<TransportOmoFormExtItem> getMainOmoForms() {
        return mainOmoForms;
    }

    public void setMainOmoForms(List<TransportOmoFormExtItem> mainOmoForms) {
        this.mainOmoForms = mainOmoForms;
    }
}
