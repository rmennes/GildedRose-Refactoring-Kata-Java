package com.gildedrose.handlers;

import static org.junit.jupiter.api.Assertions.*;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class RegularItemHandlerTest {

  @Test
  void qualityDecreases() {
    //Given
    RegularItemHandler regularItemHandler = new RegularItemHandler();
    Item item = new Item("Some Item", 10, 15);

    //When
    regularItemHandler.tickDay(item);

    //Then
    assertEquals(14, item.quality);
  }

  @Test
  void qualityDecreasesTwiceAsFastAfterSellDate() {
    //Given
    RegularItemHandler regularItemHandler = new RegularItemHandler();
    Item item = new Item("Some Item", 0, 15);

    //When
    regularItemHandler.tickDay(item);

    //Then
    assertEquals(13, item.quality);
  }

  @Test
  void decreaseSellIn() {
    //Given
    RegularItemHandler regularItemHandler = new RegularItemHandler();
    Item item = new Item("Some Item", 15, 10);

    //When
    regularItemHandler.tickDay(item);

    //Then
    assertEquals(14, item.sellIn);
  }

}
