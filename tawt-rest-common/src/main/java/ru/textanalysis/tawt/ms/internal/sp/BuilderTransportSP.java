package ru.textanalysis.tawt.ms.internal.sp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.textanalysis.tawt.ms.external.sp.BearingPhraseExt;
import ru.textanalysis.tawt.ms.internal.form.Form;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.ms.internal.ref.RefOmoFormList;
import ru.textanalysis.tawt.ms.storage.ref.RefWordList;
import ru.textanalysis.tawt.rest.common.api.response.item.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Lazy
@Service
public class BuilderTransportSP {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final BuilderTransportRef builderTransportRef;

    public BuilderTransportSP(BuilderTransportRef builderTransportRef) {
        this.builderTransportRef = builderTransportRef;
    }

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

    public BearingPhraseSP build(TransportBearingPhraseSPItem transportBearingPhraseSPItem) {
        return buildSP(transportBearingPhraseSPItem);
    }

    public TransportBearingPhraseExtItem build(BearingPhraseExt bearingPhraseExt) {
        TransportBearingPhraseExtItem extItem = new TransportBearingPhraseExtItem();

        List<TransportOmoFormExtItem> formItems = new LinkedList<>();
        bearingPhraseExt.getMainOmoForms().forEach(omoFormExt -> {
            TransportOmoFormExtItem transportOmoFormExtItem = new TransportOmoFormExtItem();
            TransportRefOmoFormItem item = builderTransportRef.build(omoFormExt.getCurrencyOmoForm());
            transportOmoFormExtItem.setCurrencyOmoForm(item);
            if (omoFormExt.getMainWord() != null) {
                transportOmoFormExtItem.setMainWord(omoFormExt.getMainWord().hashCode());
            } else {
                transportOmoFormExtItem.setMainWord(null);
            }
            List<Integer> integers = new LinkedList<>();
            omoFormExt.getDependentWords().forEach(omoFormExt1 -> {
                integers.add(omoFormExt1.hashCode());
            });
            transportOmoFormExtItem.setDependentWords(integers);
            formItems.add(transportOmoFormExtItem);
        });
        extItem.setMainOmoForms(formItems);

        return extItem;
    }

    public BearingPhraseExt buildExt(TransportBearingPhraseSPItem transportBearingPhraseSPItem) {
        return buildSP(transportBearingPhraseSPItem).toExt();
    }

    private BearingPhraseSP buildSP(TransportBearingPhraseSPItem transportBearingPhraseSPItem) {
        RefWordList refWordList = new RefWordList();
        Map<Integer, TransportOmoFormSPItem> mapTransportOmoForms = new HashMap<>();
        transportBearingPhraseSPItem.getWords().forEach(transportWordSpItem -> {
            List<Form> forms = new LinkedList<>();
            transportWordSpItem.getOmoForms().values().forEach(transportOmoFormSPItem -> {
                Form form = builderTransportRef.build(transportOmoFormSPItem.getCurrencyOmoForm());
                forms.add(form);
                mapTransportOmoForms.put(form.getOrder(), transportOmoFormSPItem);
            });
            RefOmoFormList refOmoFormList = new RefOmoFormList(forms);
            refWordList.add(refOmoFormList);
        });

        BearingPhraseSP result = new BearingPhraseSP(refWordList);
        Map<Integer, WordSP> integerWordSpMap = new HashMap<>();
        for (int i = 0; i < transportBearingPhraseSPItem.getWords().size(); i++) {
            Integer key = transportBearingPhraseSPItem.getWords().get(i).getOmoFormSPListHashcode();
            WordSP value = result.words.get(i);
            integerWordSpMap.put(key, value);
        }

        result.words.forEach(wordSP -> {
            wordSP.omoForms.values().forEach(omoFormSP -> {
                TransportOmoFormSPItem transportOmoFormSPItem = mapTransportOmoForms.get(omoFormSP.hashCode());
                if (transportOmoFormSPItem.getMainCursors().getMainCursorsWordSPHashcode() != null
                        && transportOmoFormSPItem.getMainCursors().getMainCursorsHashcode() != null) {
                    WordSP mainWordSP = integerWordSpMap.get(transportOmoFormSPItem.getMainCursors().getMainCursorsWordSPHashcode());
                    Integer mainHashcode = transportOmoFormSPItem.getMainCursors().getMainCursorsHashcode();
                    omoFormSP.mainCursors = new CursorToFormInWord(mainWordSP, mainHashcode);
                } else {
                    transportBearingPhraseSPItem.getMainOmoForms().forEach(transportOmoFormSPItem1 -> {
                        if (omoFormSP.hashCode() == transportOmoFormSPItem1.getCurrencyOmoForm().getOrder()) {
                            result.mainOmoForms.add(omoFormSP);
                        }
                    });
                }
                if (!transportOmoFormSPItem.getDependentCursors().isEmpty()) {
                    List<CursorToFormInWordItem> dependentCursors = transportOmoFormSPItem.getDependentCursors();
                    List<CursorToFormInWord> cursorToFormInWords = new LinkedList<>();
                    dependentCursors.forEach(cursorToFormInWordItem -> {
                        WordSP mainWordSP = integerWordSpMap.get(cursorToFormInWordItem.getMainCursorsWordSPHashcode());
                        Integer mainHashcode = cursorToFormInWordItem.getMainCursorsHashcode();
                        CursorToFormInWord cursorToFormInWord = new CursorToFormInWord(mainWordSP, mainHashcode);
                        cursorToFormInWords.add(cursorToFormInWord);
                    });
                    omoFormSP.dependentCursors = cursorToFormInWords;
                }
            });
        });
        return result;
    }
}
