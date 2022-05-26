package com.gildedrose.handlers;

import com.gildedrose.Item;

public interface ItemHandler {

  default void tickDay(Item item)  {
    updateQuality(item);
    updateSellIn(item);
  }

  void updateQuality(Item item);

  void updateSellIn(Item item);

}
