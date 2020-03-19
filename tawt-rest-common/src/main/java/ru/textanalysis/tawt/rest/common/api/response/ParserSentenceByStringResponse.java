package ru.textanalysis.tawt.rest.common.api.response;

import ru.textanalysis.common.rest.domain.response.BaseResponseAbstract;
import ru.textanalysis.tawt.rest.common.api.response.item.ParserSentence;

public class ParserSentenceByStringResponse extends BaseResponseAbstract<ParserSentence> {
    @Override
    public void createEmptyData() {
        data = new ParserSentence();
    }
}
