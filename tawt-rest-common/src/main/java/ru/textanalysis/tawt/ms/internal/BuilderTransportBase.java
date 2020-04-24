package ru.textanalysis.tawt.ms.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportOmoFormItem;
import ru.textanalysis.tawt.rest.common.exception.TawtRestRuntimeException;

@Service
public class BuilderTransportBase {
    private Logger log = LoggerFactory.getLogger(getClass());

    public TransportOmoFormItem build(IOmoForm iOmoForm) {
        OmoForm form = (OmoForm) iOmoForm;
        TransportOmoFormItem item = new TransportOmoFormItem();
        item.setInitialFormKey(form.initialFormKey);
        item.setMyFormKey(form.myFormKey);
        item.setTypeOfSpeech(form.typeOfSpeech);
        item.setMorfCharacteristics(form.morfCharacteristics);
        item.setTypeForm(form.getTypeForm().getId());
        item.setMyMain(form.myMain);
        item.setMyDependent(form.myDependent);
        item.setStringForm(form.getMyFormString());
        switch (form.getTypeForm()) {
            case NUMBER:
            case UNFAMILIAR:
                item.setStringForm(iOmoForm.getInitialFormString());
                break;
        }
        return item;
    }

    public IOmoForm build(TransportOmoFormItem item) {
        OmoForm form;
        TypeForms typeForm = IEnumWithLongValue.getEnumById(TypeForms.class, item.getTypeForm());
        switch (typeForm) {
            case INITIAL:
            case WORD:
                form = new OmoForm();
                break;
            case NUMBER:
                form = new NumberOmoForm();
                ((NumberOmoForm) form).str = item.getStringForm();
                break;
            case UNFAMILIAR:
                form = new UnfamiliarOmoForm();
                ((UnfamiliarOmoForm) form).str = item.getStringForm();
                break;
            default:
                log.warn("Cannot impl for {}", typeForm);
                throw new TawtRestRuntimeException("Cannot impl for {}" + typeForm);
        }
        form.initialFormKey = item.getInitialFormKey();
        form.myDependent = item.getMyDependent();
        form.myMain = item.getMyMain();
        form.myFormKey = item.getMyFormKey();
        form.morfCharacteristics = item.getMorfCharacteristics();
        form.typeOfSpeech = item.getTypeOfSpeech();
        return form;
    }
}
