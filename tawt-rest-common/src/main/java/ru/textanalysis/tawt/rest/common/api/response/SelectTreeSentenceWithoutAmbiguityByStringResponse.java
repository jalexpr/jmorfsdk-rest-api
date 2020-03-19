package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.SelectTreeSentenceWithoutAmbiguity;

public class SelectTreeSentenceWithoutAmbiguityByStringResponse extends BaseResponseAbstract<SelectTreeSentenceWithoutAmbiguity> {
    @Override
    public void createEmptyData() {
        data = new SelectTreeSentenceWithoutAmbiguity();
    }
}
