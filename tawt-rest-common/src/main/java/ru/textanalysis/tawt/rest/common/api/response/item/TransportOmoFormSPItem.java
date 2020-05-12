package ru.textanalysis.tawt.rest.common.api.response.item;

import java.util.List;

public class TransportOmoFormSPItem {
    private List<TransportRefOmoFormItem> currencyOmoForm;
    private List<Integer> mainCursorsHashcodes;
    private List<Integer> mainCursorsWordSPHashcodes;

    public List<TransportRefOmoFormItem> getCurrencyOmoForm() {
        return currencyOmoForm;
    }

    public void setCurrencyOmoForm(List<TransportRefOmoFormItem> currencyOmoForm) {
        this.currencyOmoForm = currencyOmoForm;
    }

    public List<Integer> getMainCursorsHashcodes() {
        return mainCursorsHashcodes;
    }

    public void setMainCursorsHashcodes(List<Integer> mainCursorsHashcodes) {
        this.mainCursorsHashcodes = mainCursorsHashcodes;
    }

    public List<Integer> getMainCursorsWordSPHashcodes() {
        return mainCursorsWordSPHashcodes;
    }

    public void setMainCursorsWordSPHashcodes(List<Integer> mainCursorsWordSPHashcodes) {
        this.mainCursorsWordSPHashcodes = mainCursorsWordSPHashcodes;
    }
}
