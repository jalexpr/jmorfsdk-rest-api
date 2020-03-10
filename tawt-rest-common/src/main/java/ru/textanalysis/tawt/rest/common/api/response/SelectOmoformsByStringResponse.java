package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.SelectOmoforms;

public class SelectOmoformsByStringResponse  extends BaseResponseAbstract<SelectOmoforms> {
    @Override
    public void createEmptyData() {
        data = new SelectOmoforms();
    }
}
