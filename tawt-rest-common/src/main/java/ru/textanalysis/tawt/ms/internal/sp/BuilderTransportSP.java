package ru.textanalysis.tawt.ms.internal.sp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.textanalysis.tawt.ms.external.sp.BearingPhraseExt;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.rest.common.api.response.item.*;

import java.util.LinkedList;
import java.util.List;

public class BuilderTransportSP {
    private final Logger log = LoggerFactory.getLogger(getClass());

    BuilderTransportRef builderTransportRef = new BuilderTransportRef();

    public TransportBearingPhraseSPItem build(BearingPhraseSP bearingPhraseSP) {
        TransportBearingPhraseSPItem spItem = new TransportBearingPhraseSPItem();

        List<TransportRefOmoFormItem> omoFormSPItems = new LinkedList<>();
        bearingPhraseSP.mainOmoForms.forEach(omoFormSP -> {
            TransportRefOmoFormItem item = builderTransportRef.build(omoFormSP.currencyOmoForm);
            omoFormSPItems.add(item);
        });

        List<TransportWordSpItem> words = new LinkedList<>();
        bearingPhraseSP.words.forEach(wordSP -> {
            TransportWordSpItem word = new TransportWordSpItem();
            List<TransportOmoFormSPItem> omoFormSPList = new LinkedList<>();
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
                omoFormSPItem.setMainCursorsHashcode(mainCursorsHashcode);
                omoFormSPItem.setMainCursorsWordSPHashcode(mainCursorsWordSPHashcode);
                omoFormSPItem.setOmoFormHashCode(integer);
                omoFormSPList.add(omoFormSPItem);
            });
            word.setOmoFormSPList(omoFormSPList);
            word.setOmoFormSPListHashcode(wordSP.hashCode());
            words.add(word);
        });
        spItem.setMainOmoForms(omoFormSPItems);
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
}
