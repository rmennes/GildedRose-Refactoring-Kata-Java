package com.gildedrose.handlers;

import static org.mockito.ArgumentMatchers.any;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConjuredItemHandlerTest {

  @Mock
  private ItemHandler childItemHandler;

  @InjectMocks
  private ConjuredItemHandler conjuredItemHandler;

  @BeforeEach
  void setup() {
    Mockito.doNothing().when(childItemHandler).updateQuality(any(Item.class));
    Mockito.doNothing().when(childItemHandler).updateSellIn(any(Item.class));
  }

  @Test
  void conjuredItemUpdatesQualityTwoTimesPerTick() {
    //Given
    Item item = new Item("SomeItem", 10, 23);

    //When
    conjuredItemHandler.tickDay(item);

    //Then
    Mockito.verify(childItemHandler, Mockito.times(2)).updateQuality(item);

  }

  @Test
  void conjuredItemUpdateSellInOncePerTick() {
    //Given
    Item item = new Item("SomeItem", 10, 23);

    //When
    conjuredItemHandler.tickDay(item);

    //Then
    Mockito.verify(childItemHandler, Mockito.times(1)).updateSellIn(item);
  }

}
