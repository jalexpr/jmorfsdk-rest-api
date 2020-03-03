package ru.textanalysis.tawt.rest.server.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.server.api.response.item.SelectStringInitialForm;

public class SelectStringInitialFormByStringResponse extends BaseResponseAbstract<SelectStringInitialForm> {
    @Override
    public void createEmptyData() {
        data = new SelectStringInitialForm();
    }
}
