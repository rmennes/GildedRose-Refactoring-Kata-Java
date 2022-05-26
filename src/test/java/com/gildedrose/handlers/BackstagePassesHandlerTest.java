package com.gildedrose.handlers;

import static org.junit.jupiter.api.Assertions.*;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BackstagePassesHandlerTest {

  private final BackstagePassesHandler backstagePassesHandler = new BackstagePassesHandler();

  @ParameterizedTest
  @CsvSource({"15,1","11,1", "10,2", "6,2", "5,3", "3,3", "1,3"})
  void backStageQualityIncrease(int sellInDays, int expectedIncrease) {
    //Given
    int quality = 10;
    Item item = new Item("Backstage pass", sellInDays, quality);

    //When
    backstagePassesHandler.tickDay(item);

    //Then
    assertEquals(quality + expectedIncrease, item.quality);
  }

  @Test
  void sellInDecrease() {
    //Given
    Item item = new Item("Backstage pass", 10, 15);

    //When
    backstagePassesHandler.tickDay(item);

    //Then
    assertEquals(9, item.sellIn);
  }

  @Test
  void backstageQualityCannotPass50() {
    //Given
    Item item = new Item("Backstage pass", 14, 50);

    //When
    backstagePassesHandler.tickDay(item);

    //Then
    assertEquals(13, item.sellIn);
    assertEquals(50, item.quality);
  }

  @Test
  void backstagePassQualityBecome0AfterConcert() {
    //Given
    Item item = new Item("Backstage pass", 0, 50);

    //When
    backstagePassesHandler.tickDay(item);

    //Then
    assertEquals(-1, item.sellIn);
    assertEquals(0, item.quality);
  }

}
