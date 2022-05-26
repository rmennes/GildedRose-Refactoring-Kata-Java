package com.gildedrose.handlers;

import com.gildedrose.Item;

public class BackstagePassesHandler implements ItemHandler {

  @Override
  public void updateQuality(Item item) {
    if (item.sellIn <= 0) {
      item.quality = 0;
    } else if (item.sellIn <= 5) {
      increaseQualityOfItem(item, 3);
    } else if (item.sellIn <= 10) {
      increaseQualityOfItem(item, 2);
    } else {
      increaseQualityOfItem(item, 1);
    }
  }

  @Override
  public void updateSellIn(Item item) {
    --item.sellIn;
  }

  private void increaseQualityOfItem(Item item, int qualityIncrease) {
    item.quality = Math.min(50, item.quality + qualityIncrease);
  }

}
