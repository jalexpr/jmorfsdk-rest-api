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
        TransportRefOmoFormItem item = new TransportRefOmoFormItem();

        //item.setForm(refOmoFormform.form);
        item.setInitialFormKey(refOmoFormform.getForm().getInitialForm().getInitialFormKey());
        item.setMorfCharacteristics(refOmoFormform.getForm().getMorphCharacteristics());
        item.setTypeOfSpeech(refOmoFormform.getForm().getInitialForm().getTypeOfSpeech());
        item.setInitialFormString(refOmoFormform.getForm().getInitialForm().getInitialFormString());
        item.setFormKeyInBD(refOmoFormform.getForm().getMyFormKey());
        item.setTypeForm(refOmoFormform.getForm().getInitialForm().getTypeForm());
        item.setInitialForm(refOmoFormform.getForm().getInitialForm().isInitialForm());

        return item;
    }

    public RefOmoForm build(TransportRefOmoFormItem item) {
        RefForm form = new RefForm(item.getMorfCharacteristics(), item.getFormKeyInBD());
        form.setInitialFormString(item.getInitialFormString());
        form.setGetTypeOfSpeech(item.getTypeOfSpeech());
        form.setInitialFormKey(item.getInitialFormKey());
        form.setTypeForm(item.getTypeForm());
        form.setMorphCharacteristics(item.getMorfCharacteristics());

/*        Form form = new Form(item.getMorfCharacteristics(), item.getFormKeyInBD()) {
            private Boolean isInitialForm = item.isInitialForm();
            private String initialFormString = item.getInitialFormString();
            private Byte typeOfSpeech = item.getTypeOfSpeech();
            private TypeForms typeForm = item.getTypeForm();
            private Integer initialFormKey = item.getInitialFormKey();

            @Override
            public byte getTypeOfSpeech() {
                return typeOfSpeech;
            }

            @Override
            public String getInitialFormString() {
                return initialFormString;
            }

            @Override
            public int getInitialFormKey() {
                return initialFormKey;
            }

            @Override
            public boolean isInitialForm() {
                return isInitialForm;
            }

            @Override
            public GetCharacteristics getInitialForm() {
                return getInitialForm();
            }

            @Override
            public TypeForms getTypeForm() {
                return typeForm;
            }
        };*/

/*        form.setInitialFormKey(item.getInitialFormKey());
        form.setMorphCharacteristics(item.getMorfCharacteristics());
        form.setGetTypeOfSpeech(item.getTypeOfSpeech());
        form.setInitialFormString(item.getInitialFormString());*/

        return new RefOmoForm(form);
        //return null;
    }

}
