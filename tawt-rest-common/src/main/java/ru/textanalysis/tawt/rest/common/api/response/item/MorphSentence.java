package ru.textanalysis.tawt.rest.common.api.response.item;

import java.util.List;

public class MorphSentence {
    private List<MorphBearingPhrase> morphBearingPhraseList;

    public List<MorphBearingPhrase> getMorphBearingPhraseList() {
        return morphBearingPhraseList;
    }

    public void setMorphBearingPhraseList(List<MorphBearingPhrase> morphBearingPhraseList) {
        this.morphBearingPhraseList = morphBearingPhraseList;
    }
}
