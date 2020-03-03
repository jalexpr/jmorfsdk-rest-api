package ru.textanalysis.tawt.rest.server.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.server.api.response.item.ExistInitialForm;

public class ExistInitialFormByStringResponse extends BaseResponseAbstract<ExistInitialForm> {
    @Override
    public void createEmptyData() {
        data = new ExistInitialForm();
    }
}
