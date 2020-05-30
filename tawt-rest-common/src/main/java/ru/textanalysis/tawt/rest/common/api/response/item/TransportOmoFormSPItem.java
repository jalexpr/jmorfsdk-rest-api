package ru.textanalysis.tawt.rest.common.api.response.item;

import java.util.LinkedList;
import java.util.List;

public class TransportOmoFormSPItem {
    private TransportRefOmoFormItem currencyOmoForm;
    private CursorToFormInWordItem mainCursors;
    private List<CursorToFormInWordItem> dependentCursors = new LinkedList<>();

    public CursorToFormInWordItem getMainCursors() {
        return mainCursors;
    }

    public void setMainCursors(CursorToFormInWordItem mainCursors) {
        this.mainCursors = mainCursors;
    }

    public List<CursorToFormInWordItem> getDependentCursors() {
        return dependentCursors;
    }

    public void setDependentCursors(List<CursorToFormInWordItem> dependentCursors) {
        this.dependentCursors = dependentCursors;
    }

    public TransportRefOmoFormItem getCurrencyOmoForm() {
        return currencyOmoForm;
    }

    public void setCurrencyOmoForm(TransportRefOmoFormItem currencyOmoForm) {
        this.currencyOmoForm = currencyOmoForm;
    }



}
