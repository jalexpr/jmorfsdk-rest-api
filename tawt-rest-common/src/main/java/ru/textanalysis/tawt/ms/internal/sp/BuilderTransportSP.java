package ru.textanalysis.tawt.ms.internal.sp;

import ru.textanalysis.tawt.ms.external.sp.BearingPhraseExt;
import ru.textanalysis.tawt.ms.internal.ref.BuilderTransportRef;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportBearingPhraseExtItem;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportBearingPhraseSPItem;
import ru.textanalysis.tawt.rest.common.api.response.item.TransportRefOmoFormItem;

import java.util.LinkedList;
import java.util.List;

public class BuilderTransportSP {

    BuilderTransportRef builderTransportRef = new BuilderTransportRef();

    public TransportBearingPhraseSPItem build(BearingPhraseSP bearingPhraseSP) {
        TransportBearingPhraseSPItem spItem = new TransportBearingPhraseSPItem();

        List<TransportRefOmoFormItem> omoFormSPItems = new LinkedList<>();
        bearingPhraseSP.mainOmoForms.forEach(omoFormSP -> {
            TransportRefOmoFormItem item = builderTransportRef.build(omoFormSP.currencyOmoForm);
            omoFormSPItems.add(item);
        });
        List<List<TransportRefOmoFormItem>> wordSPItems = new LinkedList<>();
        bearingPhraseSP.words.forEach(wordSP -> {
            List<TransportRefOmoFormItem> items = new LinkedList<>();
            wordSP.omoForms.forEach((integer, omoFormSP) -> {
                TransportRefOmoFormItem item = builderTransportRef.build(omoFormSP.currencyOmoForm);
                items.add(item);
            });
            wordSPItems.add(items);
        });
        spItem.setMainOmoForms(omoFormSPItems);
        spItem.setWordSP(wordSPItems);

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
