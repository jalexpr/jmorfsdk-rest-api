package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.SelectRefOmoformsList;

public class SelectRefOmoFormListByStringResponse extends BaseResponseAbstract<SelectRefOmoformsList> {
    @Override
    public void createEmptyData() {
        data = new SelectRefOmoformsList();
    }
}
