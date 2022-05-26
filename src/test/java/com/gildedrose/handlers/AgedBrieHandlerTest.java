package com.gildedrose.handlers;

import static org.junit.jupiter.api.Assertions.*;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class AgedBrieHandlerTest {

  private final AgedBrieHandler agedBrieHandler = new AgedBrieHandler();

  @Test
  void agedBrieQualityIncreases() {
    //Given
    Item agedBrie = new Item("Aged Brie", 10, 15);

    //When
    agedBrieHandler.tickDay(agedBrie);

    //Then
    assertEquals(16, agedBrie.quality);
  }

  @Test
  void agedBrieQualityIncreasesTwiceAsFastAfterDatePass() {
    //Given
    Item agedBrie = new Item("Aged Brie", 0, 15);

    //When
    agedBrieHandler.tickDay(agedBrie);

    //Then
    assertEquals(17, agedBrie.quality);
  }

  @Test
  void agedBrieDecreasesSellInDate() {
    //Given
    Item agedBrie = new Item("Aged Brie", 10, 15);

    //When
    agedBrieHandler.tickDay(agedBrie);

    //Then
    assertEquals(9, agedBrie.sellIn);
  }

  @Test
  void agedBrieQualityCannotPass50() {
    //Given
    Item agedBrie = new Item("Aged Brie", 10, 50);

    //When
    agedBrieHandler.tickDay(agedBrie);

    //Then
    assertEquals(50, agedBrie.quality);
    assertEquals(9, agedBrie.sellIn);
  }

}
