package ru.textanalysis.tawt.rest.common.api.response.item;

import java.util.List;

public class MorphBearingPhrase {
    private List<MorphWord> morphWordList;

    public List<MorphWord> getMorphWordList() {
        return morphWordList;
    }

    public void setMorphWordList(List<MorphWord> morphWordList) {
        this.morphWordList = morphWordList;
    }
}
