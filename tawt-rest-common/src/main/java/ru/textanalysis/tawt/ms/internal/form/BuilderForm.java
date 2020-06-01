package ru.textanalysis.tawt.ms.internal.form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.tawt.ms.internal.IEnumWithLongValue;
import ru.textanalysis.tawt.ms.internal.TypeForms;
import ru.textanalysis.tawt.ms.internal.jmorfsdk.InitialForm;
import ru.textanalysis.tawt.ms.internal.jmorfsdk.NumberForm;
import ru.textanalysis.tawt.ms.internal.jmorfsdk.UnfamiliarForm;
import ru.textanalysis.tawt.ms.internal.jmorfsdk.WordForm;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportRefOmoFormItem;
import ru.textanalysis.tawt.rest.common.exception.TawtRestRuntimeException;

@Lazy
@Service
public class BuilderForm {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public Form build(TransportRefOmoFormItem item) {
        TypeForms typeForm = IEnumWithLongValue.getEnumById(TypeForms.class, item.getTypeFormId());
        Form form;
        Form.formCount = item.getOrder() - 1;
        switch (typeForm) {
            case NUMBER:
                form = new NumberForm(item.getInitialFormString());
                break;
            case INITIAL:
                form = new InitialForm(item.getFormKeyInBD(), item.getTypeOfSpeech(), item.getMorfCharacteristics());
                break;
            case WORD:
                Form.formCount = item.getOrderInitialForm() - 1;
                InitialForm initialForm = new InitialForm(item.getInitialFormKey(), item.getTypeOfSpeech(), item.getMorfCharacteristics());
                Form.formCount = item.getOrder() - 1;
                form = new WordForm(item.getFormKeyInBD(), item.getMorfCharacteristics(), initialForm);
                break;
            case UNFAMILIAR:
                form = new UnfamiliarForm(item.getMyString());
                break;
            default:
                log.warn("Cannot impl for {}", typeForm);
                throw new TawtRestRuntimeException("Cannot impl for {}" + typeForm);
        }
        return form;
    }
}
