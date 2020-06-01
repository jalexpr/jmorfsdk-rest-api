package ru.textanalysis.tawt.rest.common.api.response.item;

import java.util.List;

public class MorphParagraph {
    private List<MorphSentence> morphSentenceList;

    public MorphParagraph() {
    }

    public MorphParagraph(List<MorphSentence> morphSentenceList) {
        this.morphSentenceList = morphSentenceList;
    }

    public List<MorphSentence> getMorphSentenceList() {
        return morphSentenceList;
    }

    public void setMorphSentenceList(List<MorphSentence> morphSentenceList) {
        this.morphSentenceList = morphSentenceList;
    }
}
