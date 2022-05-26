package com.gildedrose;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.stream.IntStream;

public class StepDefinitions {
    private Item[] items = new Item[1];
    private GildedRose app;

    @Given("A GildedRose with a {string} item with quality {int} and sellIn {int}")
    public void initial_gildedrose(String name, int quality, int sellIn) {
        items[0] = new Item(name, sellIn, quality);
        app = new GildedRose(items);
    }

    @When("I update the quality {int} times")
    @When("I update the quality {int} time")
    public void i_update_the_quality_x_times(int updates) {
        IntStream.range(0, updates).forEach(i -> app.updateQuality());
    }

    @Then("The item should have a quality of {int}")
    public void expectQuality(int quality) {
        assertEquals(quality, app.items[0].quality);
    }

    @Then("the item should have a sellIn of {int}")
    public void expectSellIn(int sellIn) {
        assertEquals(sellIn, app.items[0].sellIn);
    }
}

