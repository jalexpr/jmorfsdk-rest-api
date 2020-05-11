package ru.textanalysis.tawt.rest.common.api.response.item;

import java.util.List;

public class TransportBearingPhraseSPItem {
    private List<TransportRefOmoFormItem> mainOmoForms;
    private List<List<TransportRefOmoFormItem>> wordSP;
    private List<List<Integer>> mainCursorsHashcodes;
    private List<List<Integer>> mainCursorsWordSPHashcodes;
    private List<List<Integer>> omoFormSPHashcodes;

    public List<List<Integer>> getOmoFormSPHashcodes() {
        return omoFormSPHashcodes;
    }

    public void setOmoFormSPHashcodes(List<List<Integer>> omoFormSPHashcodes) {
        this.omoFormSPHashcodes = omoFormSPHashcodes;
    }

    public List<List<Integer>> getMainCursorsHashcodes() {
        return mainCursorsHashcodes;
    }

    public void setMainCursorsHashcodes(List<List<Integer>> mainCursorsHashcodes) {
        this.mainCursorsHashcodes = mainCursorsHashcodes;
    }

    public List<List<Integer>> getMainCursorsWordSPHashcodes() {
        return mainCursorsWordSPHashcodes;
    }

    public void setMainCursorsWordSPHashcodes(List<List<Integer>> mainCursorsWordSPHashcodes) {
        this.mainCursorsWordSPHashcodes = mainCursorsWordSPHashcodes;
    }

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
