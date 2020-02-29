package ru.textanalysis.tawt.rest.server.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.server.api.response.item.SelectMorfCharacteristics;

public class SelectMorfCharacteristicsByStringResponse extends BaseResponseAbstract<SelectMorfCharacteristics> {
    @Override
    public void createEmptyData() {
        data = new SelectMorfCharacteristics();
    }
}
