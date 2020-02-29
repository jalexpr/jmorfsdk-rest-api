package ru.textanalysis.tawt.rest.server.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.server.api.response.item.SelectOmoforms;

public class SelectOmoformsByStringResponse  extends BaseResponseAbstract<SelectOmoforms> {
    @Override
    public void createEmptyData() {
        data = new SelectOmoforms();
    }
}
