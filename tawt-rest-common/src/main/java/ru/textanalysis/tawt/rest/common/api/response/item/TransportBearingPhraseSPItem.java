package ru.textanalysis.tawt.rest.common.api.response.item;

import java.io.Serializable;
import java.util.List;

public class TransportBearingPhraseSPItem implements Serializable {
    private List<TransportRefOmoFormItem> mainOmoForms;
    private List<TransportWordSpItem> words;

    public List<TransportWordSpItem> getWords() {
        return words;
    }

    public void setWords(List<TransportWordSpItem> words) {
        this.words = words;
    }

    public List<TransportRefOmoFormItem> getMainOmoForms() {
        return mainOmoForms;
    }

    public void setMainOmoForms(List<TransportRefOmoFormItem> mainOmoForms) {
        this.mainOmoForms = mainOmoForms;
    }
}
