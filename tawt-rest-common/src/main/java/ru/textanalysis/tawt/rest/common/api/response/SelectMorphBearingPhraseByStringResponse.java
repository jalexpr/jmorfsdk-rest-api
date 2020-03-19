package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.SelectMorphBearingPhrase;

public class SelectMorphBearingPhraseByStringResponse extends BaseResponseAbstract<SelectMorphBearingPhrase> {
    @Override
    public void createEmptyData() {
        data = new SelectMorphBearingPhrase();
    }
}
