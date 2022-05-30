package com.gildedrose;

import com.gildedrose.handlers.AgedBrieHandler;
import com.gildedrose.handlers.BackstagePassesHandler;
import com.gildedrose.handlers.ConjuredItemHandler;
import com.gildedrose.handlers.ItemHandler;
import com.gildedrose.handlers.RegularItemHandler;
import com.gildedrose.handlers.SulfurasHandler;
import java.util.List;
import java.util.Optional;

public class HandlerFactory {

  private record NamedItemHanlder(String name, ItemHandler itemHandler) {

  }

  private static final List<NamedItemHanlder> SPECIAL_ITEM_HANDLERS = List.of(
        new NamedItemHanlder("Aged Brie", new AgedBrieHandler()),
        new NamedItemHanlder("Sulfuras", new SulfurasHandler()),
        new NamedItemHanlder("Backstage passes", new BackstagePassesHandler())
  );

  private static final ItemHandler DEFAULT_ITEM_HANDLER = new RegularItemHandler();

  public ItemHandler getItemHandlerForItem(String itemName) {
    final boolean conjuredItem = itemName.startsWith("Conjured");
    if (conjuredItem) {
      itemName = itemName.substring(8).stripLeading();
    }

    ItemHandler itemHandler = findSpecialItemHandler(itemName).orElse(DEFAULT_ITEM_HANDLER);
    if (conjuredItem) {
      return conjure(itemHandler);
    }
    return itemHandler;
  }

  private Optional<ItemHandler> findSpecialItemHandler(String name) {
    return SPECIAL_ITEM_HANDLERS.stream()
        .filter(e -> name.startsWith(e.name())).map(NamedItemHanlder::itemHandler).findFirst();
  }

  private ItemHandler conjure(ItemHandler itemHandler) {
    return new ConjuredItemHandler(itemHandler);
  }

}
