package ru.textanalysis.tawt.rest.common.api.response.item;

import java.util.List;

public class TransportBearingPhraseSPItem {
    List<TransportRefOmoFormItem> mainOmoForms;
    List<List<TransportRefOmoFormItem>> wordSP;

    public List<List<TransportRefOmoFormItem>> getWordSP() {
        return wordSP;
    }

    public void setWordSP(List<List<TransportRefOmoFormItem>> wordSP) {
        this.wordSP = wordSP;
    }

    public List<TransportRefOmoFormItem> getMainOmoForms() {
        return mainOmoForms;
    }

    public void setMainOmoForms(List<TransportRefOmoFormItem> mainOmoForms) {
        this.mainOmoForms = mainOmoForms;
    }
}
