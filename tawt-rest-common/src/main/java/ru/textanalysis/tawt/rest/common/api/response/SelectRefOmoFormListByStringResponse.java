package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.SelectRefOmoforms;

public class SelectRefOmoFormListByStringResponse extends BaseResponseAbstract<SelectRefOmoforms> {
    @Override
    public void createEmptyData() {
        data = new SelectRefOmoforms();
    }
}
