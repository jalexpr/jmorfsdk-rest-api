package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.SelectMorphSentence;

public class SelectMorphSentenceByStringResponse extends BaseResponseAbstract<SelectMorphSentence> {
    @Override
    public void createEmptyData() {
        data = new SelectMorphSentence();
    }
}
