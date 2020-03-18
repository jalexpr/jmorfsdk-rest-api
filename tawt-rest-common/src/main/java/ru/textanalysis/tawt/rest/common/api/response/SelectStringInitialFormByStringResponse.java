package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.SelectStringInitialForm;

public class SelectStringInitialFormByStringResponse extends BaseResponseAbstract<SelectStringInitialForm> {
    @Override
    public void createEmptyData() {
        data = new SelectStringInitialForm();
    }
}
