package ru.textanalysis.tawt.ms.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportOmoFormItem;

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
        item.setNumber(form.isNumber());
        item.setMyMain(form.myMain);
        item.setMyDependent(form.myDependent);
        if (form.isNumber()) {
            item.setStrNumber(((NumberOmoForm) iOmoForm).strNumber);
        }
        return item;
    }

    public IOmoForm build(TransportOmoFormItem item) {
        OmoForm form;
        if (item.getNumber()) {
            form = new NumberOmoForm(item.getStrNumber());
        } else {
            form = new OmoForm();
        }
        form.initialFormKey = item.getInitialFormKey();
        form.myFormKey = item.getMyFormKey();
        form.typeOfSpeech = item.getTypeOfSpeech();
        form.morfCharacteristics = item.getMorfCharacteristics();
        form.myMain = item.getMyMain();
        form.myDependent = item.getMyDependent();
        return form;
    }
}
