package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.SelectMorfCharacteristics;

public class SelectMorfCharacteristicsByStringResponse extends BaseResponseAbstract<SelectMorfCharacteristics> {
    @Override
    public void createEmptyData() {
        data = new SelectMorfCharacteristics();
    }
}
