package com.gildedrose.handlers;

import com.gildedrose.Item;

public class AgedBrieHandler implements ItemHandler {

  @Override
  public void updateQuality(Item item) {
    int qualityIncrease = item.sellIn <= 0 ? 2 : 1;
    item.quality = Math.min(50, item.quality + qualityIncrease);
  }

  @Override
  public void updateSellIn(Item item) {
    --item.sellIn;
  }
}
