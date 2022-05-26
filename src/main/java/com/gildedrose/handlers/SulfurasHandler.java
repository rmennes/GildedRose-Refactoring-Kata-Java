package com.gildedrose.handlers;

import com.gildedrose.Item;

public class SulfurasHandler implements ItemHandler {

  @Override
  public void updateQuality(Item item) {
    item.quality = 80;
  }

  @Override
  public void updateSellIn(Item item) {

  }
}
