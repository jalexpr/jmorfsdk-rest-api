package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.SelectTypeOfSpeeches;

public class SelectTypeOfSpeechesByStringResponse extends BaseResponseAbstract<SelectTypeOfSpeeches> {
    @Override
    public void createEmptyData() {
        data = new SelectTypeOfSpeeches();
    }
}
