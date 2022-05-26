package com.gildedrose.handlers;

import com.gildedrose.Item;

public class ConjuredItemHandler implements ItemHandler {

  private final ItemHandler itemHandler;

  public ConjuredItemHandler(ItemHandler itemHandler) {
    this.itemHandler = itemHandler;
  }

  @Override
  public void updateQuality(Item item) {
    itemHandler.updateQuality(item);
    itemHandler.updateQuality(item);
  }

  @Override
  public void updateSellIn(Item item) {
    itemHandler.updateSellIn(item);
  }

  public ItemHandler childHandler() {
    return itemHandler;
  }
}
