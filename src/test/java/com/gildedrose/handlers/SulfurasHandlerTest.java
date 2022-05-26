package com.gildedrose.handlers;

import static org.junit.jupiter.api.Assertions.*;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class SulfurasHandlerTest {

  private final SulfurasHandler sulfurasHandler = new SulfurasHandler();

  @Test
  void qualityWillBeFixedOn80() {
    //When
    Item item = new Item("Sulfuras", 10, 80);

    //When
    sulfurasHandler.tickDay(item);

    //Then
    assertEquals(80, item.quality);
  }

  @Test
  void sellInDateDoesNotChange() {
    //When
    Item item = new Item("Sulfuras", 10, 80);

    //When
    sulfurasHandler.tickDay(item);

    //Then
    assertEquals(10, item.sellIn);
  }

}
