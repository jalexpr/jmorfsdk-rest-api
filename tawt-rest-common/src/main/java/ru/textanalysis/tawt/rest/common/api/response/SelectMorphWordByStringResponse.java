package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.SelectMorphWord;

public class SelectMorphWordByStringResponse extends BaseResponseAbstract<SelectMorphWord> {
    @Override
    public void createEmptyData() {
        data = new SelectMorphWord();
    }
}
