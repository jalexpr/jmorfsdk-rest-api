package ru.textanalysis.tawt.jmorfsdk.rest.api.selection.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.jmorfsdk.rest.api.selection.response.item.SelectOmoforms;

public class SelectOmoformsByStringResponse  extends BaseResponseAbstract<SelectOmoforms> {
    @Override
    public void createEmptyData() {
        data = new SelectOmoforms();
    }
}
