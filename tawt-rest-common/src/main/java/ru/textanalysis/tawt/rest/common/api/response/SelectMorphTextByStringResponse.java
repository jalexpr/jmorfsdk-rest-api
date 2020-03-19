package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.SelectMorphText;

public class SelectMorphTextByStringResponse extends BaseResponseAbstract<SelectMorphText> {
    @Override
    public void createEmptyData() {
        data = new SelectMorphText();
    }
}
