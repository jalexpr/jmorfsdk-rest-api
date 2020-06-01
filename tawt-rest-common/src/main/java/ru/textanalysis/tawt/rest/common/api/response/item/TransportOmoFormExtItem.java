package ru.textanalysis.tawt.rest.common.api.response.item;

import java.io.Serializable;
import java.util.List;

public class TransportOmoFormExtItem implements Serializable {
    private TransportRefOmoFormItem currencyOmoForm;
    private Integer mainWord;
    private List<Integer> dependentWords;

    public TransportRefOmoFormItem getCurrencyOmoForm() {
        return currencyOmoForm;
    }

    public void setCurrencyOmoForm(TransportRefOmoFormItem currencyOmoForm) {
        this.currencyOmoForm = currencyOmoForm;
    }

    public Integer getMainWord() {
        return mainWord;
    }

    public void setMainWord(Integer mainWord) {
        this.mainWord = mainWord;
    }

    public List<Integer> getDependentWords() {
        return dependentWords;
    }

    public void setDependentWords(List<Integer> dependentWords) {
        this.dependentWords = dependentWords;
    }
}
