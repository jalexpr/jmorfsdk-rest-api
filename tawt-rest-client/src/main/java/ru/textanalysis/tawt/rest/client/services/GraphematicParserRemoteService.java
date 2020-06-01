package ru.textanalysis.tawt.rest.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.common.rest.services.RestClientService;
import ru.textanalysis.tawt.rest.client.config.Config;
import ru.textanalysis.tawt.rest.common.api.request.SelectByBearingPhraseRequest;
import ru.textanalysis.tawt.rest.common.api.request.SelectByParagraphRequest;
import ru.textanalysis.tawt.rest.common.api.request.SelectBySentenceRequest;
import ru.textanalysis.tawt.rest.common.api.request.SelectByStringRequest;
import ru.textanalysis.tawt.rest.common.api.response.ParserBasicsPhaseByStringResponse;
import ru.textanalysis.tawt.rest.common.api.response.ParserParagraphByStringResponse;
import ru.textanalysis.tawt.rest.common.api.response.ParserSentenceByStringResponse;
import ru.textanalysis.tawt.rest.common.api.response.ParserTextByStringResponse;
import ru.textanalysis.tawt.rest.common.exception.TawtRestRuntimeException;

import java.util.List;

@Lazy
@Service
@SuppressWarnings("FieldCanBeLocal")
public class GraphematicParserRemoteService {
    private final String SERVICE_URL;
    private final String URN_PARSER_BASICS_PHASE_BY_STRING = "api/gp/parser/basics/phase";
    private final String URN_PARSER_SENTENCE_BY_STRING = "api/gp/parser/sentence";
    private final String URN_PARSER_PARAGRAPH_BY_STRING = "api/gp/parser/paragraph";
    private final String URN_PARSER_TEXT_BY_STRING = "api/gp/parser/text";

    private final RestClientService restClientService;

    @Autowired
    GraphematicParserRemoteService(RestClientService restClientService,
                                   Config config) {
        this.restClientService = restClientService;
        this.SERVICE_URL = config.getUrl();
    }

    /**
     * Получение списка слов по заданной фразе
     *
     * @param basicsPhase фраза
     * @return список слов
     */
    public ServiceWorksResult<List<String>> parserBasicsPhaseByString(String basicsPhase) {
        SelectByBearingPhraseRequest request = new SelectByBearingPhraseRequest();
        request.setBearingPhrase(basicsPhase);

        ParserBasicsPhaseByStringResponse response =
                restClientService.post(SERVICE_URL, URN_PARSER_BASICS_PHASE_BY_STRING,
                        request, ParserBasicsPhaseByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by basicsPhase = %s",
                    SERVICE_URL, URN_PARSER_BASICS_PHASE_BY_STRING, basicsPhase);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getStringList(), response.getErrors());
    }

    /**
     * Получение списка слов по заданному предложению.
     *
     * @param sentence предложение
     * @return список слов
     */
    public ServiceWorksResult<List<List<String>>> parserSentence(String sentence) {
        SelectBySentenceRequest request = new SelectBySentenceRequest();
        request.setSentence(sentence);

        ParserSentenceByStringResponse response =
                restClientService.post(SERVICE_URL, URN_PARSER_SENTENCE_BY_STRING,
                        request, ParserSentenceByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by sentence = %s",
                    SERVICE_URL, URN_PARSER_SENTENCE_BY_STRING, sentence);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getStringList(), response.getErrors());
    }

    /**
     * Получение списка слов по заданному параграфу.
     *
     * @param paragraph параграф
     * @return список слов
     */
    public ServiceWorksResult<List<List<List<String>>>> parserParagraph(String paragraph) {
        SelectByParagraphRequest request = new SelectByParagraphRequest();
        request.setParagraph(paragraph);

        ParserParagraphByStringResponse response =
                restClientService.post(SERVICE_URL, URN_PARSER_PARAGRAPH_BY_STRING,
                        request, ParserParagraphByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by paragraph = %s",
                    SERVICE_URL, URN_PARSER_PARAGRAPH_BY_STRING, paragraph);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getStringList(), response.getErrors());
    }

    /**
     * получение списка слов по заданному тексту
     *
     * @param text произвольный текст
     * @return список слов
     */
    public ServiceWorksResult<List<List<List<List<String>>>>> parserText(String text) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(text);

        ParserTextByStringResponse response =
                restClientService.post(SERVICE_URL, URN_PARSER_TEXT_BY_STRING,
                        request, ParserTextByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by text = %s",
                    SERVICE_URL, URN_PARSER_TEXT_BY_STRING, text);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getStringList(), response.getErrors());
    }
}
