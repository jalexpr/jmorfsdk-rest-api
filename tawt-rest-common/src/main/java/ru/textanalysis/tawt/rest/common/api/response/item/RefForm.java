package ru.textanalysis.tawt.rest.common.api.response.item;

import ru.textanalysis.tawt.ms.internal.TypeForms;
import ru.textanalysis.tawt.ms.internal.form.Form;
import ru.textanalysis.tawt.ms.internal.form.GetCharacteristics;

public class RefForm extends Form {
    private Integer initialFormKey;
    private Long MorphCharacteristics;
    private Byte getTypeOfSpeech;
    private String initialFormString;
    private TypeForms typeForm;

    public RefForm (long morphCharacteristics, int formKey) {
        super(morphCharacteristics, formKey);
    }

    public void setTypeForm(TypeForms typeForm) {
        this.typeForm = typeForm;
    }

    public void setInitialFormKey(Integer initialFormKey) {
        this.initialFormKey = initialFormKey;
    }

    public void setGetTypeOfSpeech(Byte getTypeOfSpeech) {
        this.getTypeOfSpeech = getTypeOfSpeech;
    }

    public void setInitialFormString(String initialFormString) {
        this.initialFormString = initialFormString;
    }

    public long getMorphCharacteristics() {
        return MorphCharacteristics;
    }

    public void setMorphCharacteristics(Long morphCharacteristics) {
        MorphCharacteristics = morphCharacteristics;
    }

    public byte getTypeOfSpeech() {
        return this.getTypeOfSpeech;
    }

    public String getInitialFormString() {
        return this.initialFormString;
    }

    public int getInitialFormKey() {
        return this.initialFormKey;
    }

    public boolean isInitialForm() {
        return false;
    }

    public GetCharacteristics getInitialForm() {
        return null;
    }

    @Override
    public TypeForms getTypeForm() {
        return typeForm;
    }
}