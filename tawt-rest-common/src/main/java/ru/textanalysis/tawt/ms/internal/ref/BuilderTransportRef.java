package ru.textanalysis.tawt.ms.internal.ref;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.textanalysis.tawt.ms.internal.form.BuilderForm;
import ru.textanalysis.tawt.ms.internal.form.Form;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportRefOmoFormItem;

@Service
public class BuilderTransportRef {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final BuilderForm builderForm;

    public BuilderTransportRef(BuilderForm builderForm) {
        this.builderForm = builderForm;
    }

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
        item.setOrder(refOmoFormform.getForm().getOrder());
        item.setOrderInitialForm(((Form) refOmoFormform.form.getInitialForm()).getOrder());
        return item;
    }

    public Form build(TransportRefOmoFormItem item) {
        return builderForm.build(item);
    }
}
