package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.ParserBasicsPhase;

public class ParserBasicsPhaseByStringResponse extends BaseResponseAbstract<ParserBasicsPhase> {
    @Override
    public void createEmptyData() {
        data = new ParserBasicsPhase();
    }
}
