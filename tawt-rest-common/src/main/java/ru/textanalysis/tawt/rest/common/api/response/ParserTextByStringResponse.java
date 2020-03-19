package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.ParserText;

public class ParserTextByStringResponse extends BaseResponseAbstract<ParserText> {
    @Override
    public void createEmptyData() {
        data = new ParserText();
    }
}
