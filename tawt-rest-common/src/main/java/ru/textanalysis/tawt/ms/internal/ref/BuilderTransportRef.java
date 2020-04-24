package ru.textanalysis.tawt.ms.internal.ref;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.textanalysis.tawt.jmorfsdk.form.InitialForm;
import ru.textanalysis.tawt.jmorfsdk.form.NumberForm;
import ru.textanalysis.tawt.jmorfsdk.form.UnfamiliarForm;
import ru.textanalysis.tawt.jmorfsdk.form.WordForm;
import ru.textanalysis.tawt.ms.internal.IEnumWithLongValue;
import ru.textanalysis.tawt.ms.internal.TypeForms;
import ru.textanalysis.tawt.ms.internal.form.Form;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportRefOmoFormItem;
import ru.textanalysis.tawt.rest.common.exception.TawtRestRuntimeException;

@Service
public class BuilderTransportRef {
    private Logger log = LoggerFactory.getLogger(getClass());

    public TransportRefOmoFormItem build(RefOmoForm refOmoFormform) {
        TransportRefOmoFormItem item = new TransportRefOmoFormItem();

        item.setInitialFormKey(refOmoFormform.getForm().getInitialFormKey());
        item.setMorfCharacteristics(refOmoFormform.getForm().getMorphCharacteristics());
        item.setTypeOfSpeech(refOmoFormform.getForm().getTypeOfSpeech());
        item.setInitialFormString(refOmoFormform.getForm().getInitialFormString());
        item.setFormKeyInBD(refOmoFormform.getForm().getMyFormKey());
        item.setTypeFormId(refOmoFormform.getForm().getTypeForm().getId());
        item.setInitialForm(refOmoFormform.getForm().isInitialForm());
        item.setMyString(refOmoFormform.getForm().getMyString());
        item.setTypeForm(refOmoFormform.getTypeForm());

        return item;
    }

    public Form build(TransportRefOmoFormItem item) {

        TypeForms typeForm = IEnumWithLongValue.getEnumById(TypeForms.class, item.getTypeFormId());
        Form form;
        switch (typeForm) {
            case NUMBER:
                form = new NumberForm(item.getInitialFormString());
                break;
            case INITIAL:
                form = new InitialForm(item.getFormKeyInBD(), item.getTypeOfSpeech(), item.getMorfCharacteristics());
                break;
            case WORD:
                form = new WordForm(item.getFormKeyInBD(), item.getMorfCharacteristics(),
                        new InitialForm(item.getInitialFormKey(), item.getTypeOfSpeech(), item.getMorfCharacteristics()));
                break;
            case UNFAMILIAR:
                form = new UnfamiliarForm(item.getInitialFormString());
                break;
            default:
                log.warn("Cannot impl for {}", typeForm);
                throw new TawtRestRuntimeException("Cannot impl for {}" + typeForm);
        }
        return form;
    }

}
