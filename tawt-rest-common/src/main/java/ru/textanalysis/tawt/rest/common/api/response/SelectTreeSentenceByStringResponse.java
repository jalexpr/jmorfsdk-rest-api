package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.SelectTreeSentence;

public class SelectTreeSentenceByStringResponse extends BaseResponseAbstract<SelectTreeSentence> {
    @Override
    public void createEmptyData() {
        data = new SelectTreeSentence();
    }
}
