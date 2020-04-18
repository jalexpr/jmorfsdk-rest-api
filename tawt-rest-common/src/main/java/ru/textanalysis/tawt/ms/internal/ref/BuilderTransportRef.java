package ru.textanalysis.tawt.ms.internal.ref;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.textanalysis.tawt.rest.common.api.response.item.RefForm;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportRefOmoFormItem;

@Service
public class BuilderTransportRef {
    private Logger log = LoggerFactory.getLogger(getClass());

    public TransportRefOmoFormItem build(RefOmoForm refOmoFormform) {
        //RefOmoForm refForm = (RefOmoForm) refOmoFormform;
        TransportRefOmoFormItem item = new TransportRefOmoFormItem();

        //item.setForm(refOmoFormform.getForm());
        item.setInitialFormKey(refOmoFormform.getForm().getInitialFormKey());
        item.setMorfCharacteristics(refOmoFormform.getForm().getMorphCharacteristics());
        item.setTypeOfSpeech(refOmoFormform.getForm().getTypeOfSpeech());
        item.setInitialFormString(refOmoFormform.getForm().getInitialFormString());
        item.setFormKeyInBD(refOmoFormform.getForm().getMyFormKey());

        return item;
    }

    public RefOmoForm build(TransportRefOmoFormItem item) {
        RefForm form = new RefForm(item.getMorfCharacteristics(), item.getFormKeyInBD());

        form.setInitialFormKey(item.getInitialFormKey());
        form.setMorphCharacteristics(item.getMorfCharacteristics());
        form.setGetTypeOfSpeech(item.getTypeOfSpeech());
        form.setInitialFormString(item.getInitialFormString());

//        return new RefOmoForm(form);
        return null;
    }

}
