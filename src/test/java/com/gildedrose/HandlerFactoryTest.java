package com.gildedrose;

import static org.assertj.core.api.Assertions.*;

import com.gildedrose.handlers.AgedBrieHandler;
import com.gildedrose.handlers.BackstagePassesHandler;
import com.gildedrose.handlers.ConjuredItemHandler;
import com.gildedrose.handlers.ItemHandler;
import com.gildedrose.handlers.RegularItemHandler;
import com.gildedrose.handlers.SulfurasHandler;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HandlerFactoryTest {

  private final HandlerFactory handlerFactory = new HandlerFactory();

  @ParameterizedTest
  @ValueSource(strings = {"Some product", ""})
  void unrecognisedItemsUseRegularItemHandler(String itemName) {
    //When
    ItemHandler itemHandler = handlerFactory.getItemHandlerForItem(itemName);

    //Then
    assertThat(itemHandler).isExactlyInstanceOf(RegularItemHandler.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {"Backstage passes to a TAFKAL80ETC concert", "Backstage passes to another concert", "Backstage passes"})
  void namesStartWithBackstagePassUseBackstagePassesHandler(String itemName) {
    //When
    ItemHandler itemHandler = handlerFactory.getItemHandlerForItem(itemName);

    //Then
    assertThat(itemHandler).isExactlyInstanceOf(BackstagePassesHandler.class);
  }

  @Test
  void namesStartWithAgedBrieUseAgedBrieHandler() {
    //When
    ItemHandler itemHandler = handlerFactory.getItemHandlerForItem("Aged Brie");

    //Then
    assertThat(itemHandler).isExactlyInstanceOf(AgedBrieHandler.class);
  }

  @Test
  void namesStartWithSulfurasUseSulfurasHandler() {
    //When
    ItemHandler itemHandler = handlerFactory.getItemHandlerForItem("Sulfuras");

    //Then
    assertThat(itemHandler).isExactlyInstanceOf(SulfurasHandler.class);
  }

  @Nested
  class ConjuredItemsTestCase {

    @ParameterizedTest
    @ValueSource(strings = {"Conjured Some product", "Conjured"})
    void unrecognisedItemsUseRegularItemHandler(String itemName) {
      //When
      ItemHandler itemHandler = handlerFactory.getItemHandlerForItem(itemName);

      //Then
      assertConjuredHandlerWithChildHandler(RegularItemHandler.class, itemHandler);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Conjured Backstage passes to a TAFKAL80ETC concert", "Conjured Backstage passes to another concert",
        "Conjured Backstage passes"})
    void namesStartWithBackstagePassUseBackstagePassesHandler(String itemName) {
      //When
      ItemHandler itemHandler = handlerFactory.getItemHandlerForItem(itemName);

      //Then
      assertConjuredHandlerWithChildHandler(BackstagePassesHandler.class, itemHandler);
    }

    @Test
    void namesStartWithAgedBrieUseAgedBrieHandler() {
      //When
      ItemHandler itemHandler = handlerFactory.getItemHandlerForItem("Conjured Aged Brie");

      //Then
      assertConjuredHandlerWithChildHandler(AgedBrieHandler.class, itemHandler);
    }

    @Test
    void namesStartWithSulfurasUseSulfurasHandler() {
      //When
      ItemHandler itemHandler = handlerFactory.getItemHandlerForItem("Conjured Sulfuras");

      //Then
      assertConjuredHandlerWithChildHandler(SulfurasHandler.class, itemHandler);
    }

    private void assertConjuredHandlerWithChildHandler(Class<?> type, ItemHandler itemHandler) {
      assertThat(itemHandler).isExactlyInstanceOf(ConjuredItemHandler.class);
      ConjuredItemHandler conjuredItemHandler = (ConjuredItemHandler) itemHandler;
      assertThat(conjuredItemHandler.childHandler()).isExactlyInstanceOf(type);
    }

  }

}
