package ru.textanalysis.tawt.rest.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.common.rest.classes.ServiceWorksResult;
import ru.textanalysis.common.rest.services.RestClientService;
import ru.textanalysis.tawt.rest.common.api.request.SelectByStringRequest;
import ru.textanalysis.tawt.rest.common.api.response.ParserBasicsPhaseByStringResponse;
import ru.textanalysis.tawt.rest.common.api.response.ParserParagraphByStringResponse;
import ru.textanalysis.tawt.rest.common.api.response.ParserSentenceByStringResponse;
import ru.textanalysis.tawt.rest.common.api.response.ParserTextByStringResponse;
import ru.textanalysis.tawt.rest.common.exception.TawtRestRuntimeException;

import java.util.List;

@Lazy
@Service
public class GraphematicParserRemoteService {
    private final static String SERVICE_NAME = "http://localhost:30002/tawt-rest-api";
    private final static String URN_PARSER_BASICS_PHASE_BY_STRING = "api/gp/parser/basics/phase";
    private final static String URN_PARSER_SENTENCE_BY_STRING = "api/gp/parser/sentence";
    private final static String URN_PARSER_PARAGRAPH_BY_STRING = "api/gp/parser/paragraph";
    private final static String URN_PARSER_TEXT_BY_STRING = "api/gp/parser/text";

    private final RestClientService restClientService;

    @Autowired
    public GraphematicParserRemoteService(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

    /**
     * Получение списка слов по заданной фразе
     * @param text фраза
     * @return ServiceWorksResult<List<String>>
     */
    public ServiceWorksResult<List<String>> parserBasicsPhaseByString(String text) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(text);

        ParserBasicsPhaseByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_PARSER_BASICS_PHASE_BY_STRING,
                        request, ParserBasicsPhaseByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_PARSER_BASICS_PHASE_BY_STRING, text);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getStringList(), response.getErrors());
    }

    /**
     * Получение списка слов по заданному предложению.
     * @param text предложение
     * @return ServiceWorksResult<List<List<String>>>
     */
    public ServiceWorksResult<List<List<String>>> parserSentence(String text) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(text);

        ParserSentenceByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_PARSER_SENTENCE_BY_STRING,
                        request, ParserSentenceByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_PARSER_SENTENCE_BY_STRING, text);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getStringList(), response.getErrors());
    }

    /**
     * Получение списка слов по заданному параграфу.
     * @param text параграф
     * @return ServiceWorksResult<List<List<List<String>>>>
     */
    public ServiceWorksResult<List<List<List<String>>>> parserParagraph(String text) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(text);

        ParserParagraphByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_PARSER_PARAGRAPH_BY_STRING,
                        request, ParserParagraphByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_PARSER_PARAGRAPH_BY_STRING, text);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getStringList(), response.getErrors());
    }

    /**
     * получение списка слов по заданному тексту
     * @param text произвольный текст
     * @return ServiceWorksResult<List<List<List<List<String>>>>>
     */
    public ServiceWorksResult<List<List<List<List<String>>>>> parserText(String text) {
        SelectByStringRequest request = new SelectByStringRequest();
        request.setText(text);

        ParserTextByStringResponse response =
                restClientService.post(SERVICE_NAME, URN_PARSER_TEXT_BY_STRING,
                        request, ParserTextByStringResponse.class);

        if (response == null) {
            String message = String.format("Error connected to http://%s/%s by word = %s",
                    SERVICE_NAME, URN_PARSER_TEXT_BY_STRING, text);
            throw new TawtRestRuntimeException(message);
        }

        return new ServiceWorksResult<>(response.getData().getStringList(), response.getErrors());
    }

}
