package ru.textanalysis.tawt.rest.common.api.response.item;

import java.util.List;

public class MorphSentence {
    private List<MorphBearingPhrase> morphBearingPhraseList;

    public MorphSentence() {
    }

    public MorphSentence(List<MorphBearingPhrase> morphBearingPhraseList) {
        this.morphBearingPhraseList = morphBearingPhraseList;
    }

    public List<MorphBearingPhrase> getMorphBearingPhraseList() {
        return morphBearingPhraseList;
    }

    public void setMorphBearingPhraseList(List<MorphBearingPhrase> morphBearingPhraseList) {
        this.morphBearingPhraseList = morphBearingPhraseList;
    }
}
