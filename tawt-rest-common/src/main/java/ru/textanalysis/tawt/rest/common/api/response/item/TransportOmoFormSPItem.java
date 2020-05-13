package ru.textanalysis.tawt.rest.common.api.response.item;

public class TransportOmoFormSPItem {
    private TransportRefOmoFormItem currencyOmoForm;
    private Integer omoFormHashCode;
    private Integer mainCursorsHashcode;
    private Integer mainCursorsWordSPHashcode;

    public TransportRefOmoFormItem getCurrencyOmoForm() {
        return currencyOmoForm;
    }

    public void setCurrencyOmoForm(TransportRefOmoFormItem currencyOmoForm) {
        this.currencyOmoForm = currencyOmoForm;
    }

    public Integer getMainCursorsHashcode() {
        return mainCursorsHashcode;
    }

    public void setMainCursorsHashcode(Integer mainCursorsHashcode) {
        this.mainCursorsHashcode = mainCursorsHashcode;
    }

    public Integer getMainCursorsWordSPHashcode() {
        return mainCursorsWordSPHashcode;
    }

    public void setMainCursorsWordSPHashcode(Integer mainCursorsWordSPHashcode) {
        this.mainCursorsWordSPHashcode = mainCursorsWordSPHashcode;
    }

    public Integer getOmoFormHashCode() {
        return omoFormHashCode;
    }

    public void setOmoFormHashCode(Integer omoFormHashCode) {
        this.omoFormHashCode = omoFormHashCode;
    }
}
