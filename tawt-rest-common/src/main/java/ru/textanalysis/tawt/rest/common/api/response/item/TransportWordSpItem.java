package ru.textanalysis.tawt.rest.common.api.response.item;

import java.util.HashMap;
import java.util.Map;

public class TransportWordSpItem {
    private Map<Integer, TransportOmoFormSPItem> omoForms = new HashMap<>();
    private Integer omoFormSPListHashcode;

    public Map<Integer, TransportOmoFormSPItem> getOmoForms() {
        return omoForms;
    }

    public void setOmoForms(Map<Integer, TransportOmoFormSPItem> omoForms) {
        this.omoForms = omoForms;
    }

    public Integer getOmoFormSPListHashcode() {
        return omoFormSPListHashcode;
    }

    public void setOmoFormSPListHashcode(Integer omoFormSPListHashcode) {
        this.omoFormSPListHashcode = omoFormSPListHashcode;
    }
}
