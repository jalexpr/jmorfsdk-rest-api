package ru.textanalysis.tawt.rest.common.api.response.item;

import java.util.List;

public class TransportWordSpItem {
    private List<TransportOmoFormSPItem> omoFormSPList;

    public List<TransportOmoFormSPItem> getOmoFormSPList() {
        return omoFormSPList;
    }

    public void setOmoFormSPList(List<TransportOmoFormSPItem> omoFormSPList) {
        this.omoFormSPList = omoFormSPList;
    }
}
