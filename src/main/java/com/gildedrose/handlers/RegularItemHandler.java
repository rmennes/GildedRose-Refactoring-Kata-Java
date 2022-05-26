package com.gildedrose.handlers;

import com.gildedrose.Item;

public class RegularItemHandler implements ItemHandler {

  @Override
  public void updateQuality(Item item) {
    int qualityDecrease = (item.sellIn <= 0 ? 2 : 1);
    item.quality = Math.max(0, item.quality - qualityDecrease);
  }

  @Override
  public void updateSellIn(Item item) {
    --item.sellIn;
  }
}
