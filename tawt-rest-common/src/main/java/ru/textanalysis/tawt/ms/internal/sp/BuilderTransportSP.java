package ru.textanalysis.tawt.ms.internal.sp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.textanalysis.tawt.ms.external.sp.BearingPhraseExt;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportBearingPhraseExtItem;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportBearingPhraseSPItem;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportOmoFormSPItem;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportRefOmoFormItem;

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

        List<TransportOmoFormSPItem> OmoFormSPList = new LinkedList<>();
        bearingPhraseSP.words.forEach(wordSP -> {
            TransportOmoFormSPItem omoFormSPItem = new TransportOmoFormSPItem();
            List<TransportRefOmoFormItem> items = new LinkedList<>();
            List<Integer> mainCursorsHashcodes = new LinkedList<>();
            List<Integer> mainCursorsWordSPHashcodes = new LinkedList<>();
            wordSP.omoForms.forEach((integer, omoFormSP) -> {
                TransportRefOmoFormItem item = builderTransportRef.build(omoFormSP.currencyOmoForm);
                items.add(item);
                int mainCursorsHashcode = -1;
                int mainCursorsWordSPHashcode = -1;
                try {
                    mainCursorsHashcode = omoFormSP.mainCursors.hashCode;
                } catch (NullPointerException ex) {
                    log.info("NullPointerException in mainCursors on omoFormHashCode: " + integer);
                }
                try {
                    mainCursorsWordSPHashcode = omoFormSP.mainCursors.wordSP.hashCode();
                } catch (NullPointerException ex) {
                    log.info("NullPointerException in mainCursors.wordSP on omoFormHashCode: " + integer);
                }
                mainCursorsHashcodes.add(mainCursorsHashcode);
                mainCursorsWordSPHashcodes.add(mainCursorsWordSPHashcode);
            });
            omoFormSPItem.setCurrencyOmoForm(items);
            omoFormSPItem.setMainCursorsHashcodes(mainCursorsHashcodes);
            omoFormSPItem.setMainCursorsWordSPHashcodes(mainCursorsWordSPHashcodes);
            OmoFormSPList.add(omoFormSPItem);
        });
        spItem.setMainOmoForms(omoFormSPItems);
        spItem.setOmoFormSPList(OmoFormSPList);

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
