package ru.textanalysis.tawt.rest.server.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.server.api.response.item.SelectTypeOfSpeeches;

public class SelectTypeOfSpeechesByStringResponse extends BaseResponseAbstract<SelectTypeOfSpeeches> {
    @Override
    public void createEmptyData() {
        data = new SelectTypeOfSpeeches();
    }
}
