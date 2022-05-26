package com.gildedrose;

import com.gildedrose.handlers.ItemHandler;
import java.util.Arrays;

class GildedRose {

    private static final HandlerFactory handlerFactory = new HandlerFactory();

    final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).forEach(item -> {
            ItemHandler itemHandler = handlerFactory.getItemHandlerForItem(item.name);
            itemHandler.tickDay(item);
        });
    }
}