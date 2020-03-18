package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.SelectDerivativeForm;

public class SelectDerivativeFormByStringResponse extends BaseResponseAbstract<SelectDerivativeForm> {
    @Override
    public void createEmptyData() {
        data = new SelectDerivativeForm();
    }
}
