package com.gildedrose;

import com.gildedrose.handlers.AgedBrieHandler;
import com.gildedrose.handlers.BackstagePassesHandler;
import com.gildedrose.handlers.ConjuredItemHandler;
import com.gildedrose.handlers.ItemHandler;
import com.gildedrose.handlers.RegularItemHandler;
import com.gildedrose.handlers.SulfurasHandler;
import java.util.Map;
import java.util.Optional;

public class HandlerFactory {

  private static final Map<String, ItemHandler> SPECIAL_ITEM_HANDLERS = Map.of(
        "Aged Brie", new AgedBrieHandler(),
        "Sulfuras", new SulfurasHandler(),
        "Backstage passes", new BackstagePassesHandler()
  );

  private static final ItemHandler DEFAULT_ITEM_HANDLER = new RegularItemHandler();

  public ItemHandler getItemHandlerForItem(String itemName) {
    boolean conjuredItem = false;
    if (itemName.startsWith("Conjured")) {
      conjuredItem = true;
      itemName = itemName.substring(8).stripLeading();
    }

    ItemHandler itemHandler = findSpecialItemHandler(itemName).orElse(DEFAULT_ITEM_HANDLER);
    if (conjuredItem) {
      return conjure(itemHandler);
    }
    return itemHandler;
  }

  private Optional<ItemHandler> findSpecialItemHandler(String name) {
    return SPECIAL_ITEM_HANDLERS.entrySet().stream()
        .filter(e -> name.startsWith(e.getKey())).map(Map.Entry::getValue).findFirst();
  }

  private ItemHandler conjure(ItemHandler itemHandler) {
    return new ConjuredItemHandler(itemHandler);
  }

}
