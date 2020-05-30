package ru.textanalysis.tawt.rest.common.api.response.item;

public class CursorToFormInWordItem {
    private Integer mainCursorsHashcode;
    private Integer mainCursorsWordSPHashcode;

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
}
