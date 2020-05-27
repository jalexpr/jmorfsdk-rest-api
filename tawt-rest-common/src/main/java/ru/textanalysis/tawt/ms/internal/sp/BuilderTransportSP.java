package ru.textanalysis.tawt.ms.internal.sp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.textanalysis.tawt.ms.external.sp.BearingPhraseExt;
import ru.textanalysis.tawt.ms.internal.form.Form;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.ms.internal.ref.RefOmoForm;
import ru.textanalysis.tawt.ms.internal.ref.RefOmoFormList;
import ru.textanalysis.tawt.ms.storage.ref.RefWordList;
import ru.textanalysis.tawt.rest.common.api.response.item.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BuilderTransportSP {
    private final Logger log = LoggerFactory.getLogger(getClass());

    BuilderTransportRef builderTransportRef = new BuilderTransportRef();

    public TransportBearingPhraseSPItem build(BearingPhraseSP bearingPhraseSP) {
        TransportBearingPhraseSPItem spItem = new TransportBearingPhraseSPItem();

        List<TransportOmoFormSPItem> transportOmoFormSPItems = new LinkedList<>();
        bearingPhraseSP.mainOmoForms.forEach(omoFormSP -> {
            TransportRefOmoFormItem item = builderTransportRef.build(omoFormSP.currencyOmoForm);
            List<CursorToFormInWordItem> cursorToFormInWordItems = new LinkedList<>();
            omoFormSP.dependentCursors.forEach(cursorToFormInWord -> {
                CursorToFormInWordItem cursorToFormInWordItem1 = new CursorToFormInWordItem();
                cursorToFormInWordItem1.setMainCursorsHashcode(cursorToFormInWord.hashCode);
                cursorToFormInWordItem1.setMainCursorsWordSPHashcode(cursorToFormInWord.wordSP.hashCode());
                cursorToFormInWordItems.add(cursorToFormInWordItem1);
            });
            TransportOmoFormSPItem transportOmoFormSPItem = new TransportOmoFormSPItem();
            transportOmoFormSPItem.setCurrencyOmoForm(item);
            transportOmoFormSPItem.setDependentCursors(cursorToFormInWordItems);
            transportOmoFormSPItems.add(transportOmoFormSPItem);
        });

        List<TransportWordSpItem> words = new LinkedList<>();
        bearingPhraseSP.words.forEach(wordSP -> {
            TransportWordSpItem word = new TransportWordSpItem();
            Map<Integer, TransportOmoFormSPItem> omoForms = new HashMap<>();
            wordSP.omoForms.forEach((integer, omoFormSP) -> {
                TransportOmoFormSPItem omoFormSPItem = new TransportOmoFormSPItem();
                TransportRefOmoFormItem item = builderTransportRef.build(omoFormSP.currencyOmoForm);
                Integer mainCursorsHashcode;
                Integer mainCursorsWordSPHashcode;
                if (omoFormSP.mainCursors != null) {
                    mainCursorsHashcode = omoFormSP.mainCursors.hashCode;
                    mainCursorsWordSPHashcode = omoFormSP.mainCursors.wordSP.hashCode();
                } else {
                    mainCursorsHashcode = null;
                    mainCursorsWordSPHashcode = null;
                }
                omoFormSPItem.setCurrencyOmoForm(item);
                CursorToFormInWordItem cursorToFormInWordItem = new CursorToFormInWordItem();
                cursorToFormInWordItem.setMainCursorsHashcode(mainCursorsHashcode);
                cursorToFormInWordItem.setMainCursorsWordSPHashcode(mainCursorsWordSPHashcode);
                omoFormSPItem.setMainCursors(cursorToFormInWordItem);
                List<CursorToFormInWordItem> cursorToFormInWordItems = new LinkedList<>();
                omoFormSP.dependentCursors.forEach(cursorToFormInWord -> {
                    CursorToFormInWordItem cursorToFormInWordItem1 = new CursorToFormInWordItem();
                    cursorToFormInWordItem1.setMainCursorsHashcode(cursorToFormInWord.hashCode);
                    cursorToFormInWordItem1.setMainCursorsWordSPHashcode(cursorToFormInWord.wordSP.hashCode());
                    cursorToFormInWordItems.add(cursorToFormInWordItem1);
                });
                omoFormSPItem.setDependentCursors(cursorToFormInWordItems);
                omoForms.put(integer, omoFormSPItem);
            });
            word.setOmoForms(omoForms);
            word.setOmoFormSPListHashcode(wordSP.hashCode());
            words.add(word);
        });
        spItem.setMainOmoForms(transportOmoFormSPItems);
        spItem.setWords(words);

        return spItem;
    }

    public TransportBearingPhraseExtItem build(BearingPhraseExt bearingPhraseExt) {
        TransportBearingPhraseExtItem extItem = new TransportBearingPhraseExtItem();

        List<TransportRefOmoFormItem> formItems = new LinkedList<>();
        bearingPhraseExt.getMainOmoForms().forEach(omoFormExt -> {
            TransportRefOmoFormItem item = builderTransportRef.build(omoFormExt.getCurrencyOmoForm());
            formItems.add(item);
        });
        extItem.setMainOmoForms(formItems);

        return extItem;
    }

    public BearingPhraseSP build(TransportBearingPhraseSPItem transportBearingPhraseSPItem) {

        RefWordList refWordList = new RefWordList();
        transportBearingPhraseSPItem.getWords().forEach(transportWordSpItem -> {
            List<Form> forms = new LinkedList<>();
            transportWordSpItem.getOmoForms().forEach((integer, transportOmoFormSPItem) -> {
                Form form = builderTransportRef.build(transportOmoFormSPItem.getCurrencyOmoForm());
                forms.add(form);
            });
            RefOmoFormList refOmoFormList = new RefOmoFormList(forms);
            refWordList.add(refOmoFormList);
        });

        /*BearingPhraseSP result = new BearingPhraseSP(refWordList);
        result.words.forEach(wordSP -> {
            wordSP.omoForms.forEach((integer, omoFormSP) -> {
                transportBearingPhraseSPItem.getWords().forEach(transportWordSpItem -> {
                    transportWordSpItem.getOmoFormSPList().forEach(transportOmoFormSPItem -> {
                        if (integer.equals(transportOmoFormSPItem.getOmoFormHashCode())) {
                            List<Form> forms = new LinkedList<>();
                            transportBearingPhraseSPItem.getWords().forEach(transportWordSpItem1 -> {
                                if (transportWordSpItem1.getOmoFormSPListHashcode().equals(transportOmoFormSPItem.getMainCursorsWordSPHashcode())) {
                                    Form form = builderTransportRef.build(transportOmoFormSPItem.getCurrencyOmoForm());
                                    forms.add(form);
                                }
                            });
                            RefOmoFormList refOmoFormList = new RefOmoFormList(forms);
                            if (refOmoFormList.isDetected()) {
                                CursorToFormInWord cursorToFormInWord = new CursorToFormInWord(new WordSP(refOmoFormList), transportOmoFormSPItem.getMainCursorsHashcode());
                                omoFormSP.mainCursors = cursorToFormInWord;
                            }
                        }
                    });
                });
            });
        });*/
        BearingPhraseSP result = new BearingPhraseSP(refWordList);
        transportBearingPhraseSPItem.getMainOmoForms().forEach(transportOmoFormSPItem -> {
            result.mainOmoForms.add(new OmoFormSP(
                    new RefOmoForm(builderTransportRef.build(transportOmoFormSPItem.getCurrencyOmoForm()))));
        });

        AtomicInteger count_a = new AtomicInteger();
        result.words.forEach(wordSP -> {
            AtomicInteger count_b = new AtomicInteger();
            count_a.getAndIncrement();
            wordSP.omoForms.forEach((integer, omoFormSP) -> {
                transportBearingPhraseSPItem.getWords().forEach(transportWordSpItem -> {
                    count_b.getAndIncrement();
                    transportWordSpItem.getOmoForms().forEach((integer1, transportOmoFormSPItem) -> {
                        if (count_a.get() == count_b.get()) {

                            transportBearingPhraseSPItem.getWords().forEach(transportWordSpItem1 -> {
                                if (transportWordSpItem1.getOmoFormSPListHashcode().equals(transportOmoFormSPItem.getMainCursors().getMainCursorsWordSPHashcode())) {
                                    List<Form> forms = new LinkedList<>();
                                    transportWordSpItem1.getOmoForms().forEach((integer2, transportOmoFormSPItem1) -> {
                                        Form form = builderTransportRef.build(transportOmoFormSPItem1.getCurrencyOmoForm());
                                        forms.add(form);
                                    });
                                    RefOmoFormList refOmoFormList = new RefOmoFormList(forms);
                                    omoFormSP.mainCursors = new CursorToFormInWord(new WordSP(refOmoFormList),
                                            transportOmoFormSPItem.getMainCursors().getMainCursorsHashcode());

                                }
                            });

                        }
                    });
                });
            });
        });



        return result;
    }
}
