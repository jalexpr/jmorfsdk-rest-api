package ru.textanalysis.tawt.ms.internal.ref.form;

import ru.textanalysis.tawt.ms.internal.form.Form;
import ru.textanalysis.tawt.ms.internal.form.GetCharacteristics;

public class RefForm extends Form {

    private Integer initialFormKey;
    private Long MorphCharacteristics;
    private Byte getTypeOfSpeech;
    private String initialFormString;

    public RefForm (long morphCharacteristics, int formKey) {
        super(morphCharacteristics, formKey);
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

    @Override
    public long getMorphCharacteristics() {
        return MorphCharacteristics;
    }

    public void setMorphCharacteristics(Long morphCharacteristics) {
        MorphCharacteristics = morphCharacteristics;
    }

    @Override
    public byte getTypeOfSpeech() {
        return this.getTypeOfSpeech;
    }

    @Override
    public String getInitialFormString() {
        return this.initialFormString;
    }

    @Override
    public int getInitialFormKey() {
        return this.initialFormKey;
    }

    @Override
    public boolean isInitialForm() {
        return false;
    }

    @Override
    public GetCharacteristics getInitialForm() {
        return null;
    }

}
