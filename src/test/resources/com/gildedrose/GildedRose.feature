Feature: Gilded Rose quality

  Scenario Outline: The quality of a regular item decreases over time
    Given A GildedRose with a "+5 Dexterity Vest" item with quality 10 and sellIn 5
    When I update the quality <updates> times
    Then The item should have a quality of <quality>
    And the item should have a sellIn of <sellIn>

    Examples:
      | updates | quality | sellIn |
      | 1       | 9       | 4      |
      | 3       | 7       | 2      |
      | 5       | 5       | 0      |
      | 6       | 3       | -1     |

  Scenario Outline: The quality of an aged brie increases over time
    Given A GildedRose with a "Aged Brie" item with quality 10 and sellIn 5
    When I update the quality <updates> times
    Then The item should have a quality of <quality>
    And the item should have a sellIn of <sellIn>

    Examples:
      | updates | quality | sellIn |
      | 1       | 11      | 4      |
      | 3       | 13      | 2      |
      | 5       | 15      | 0      |
      | 6       | 17      | -1     |

  Scenario Outline: The quality of sulfuras items does not change
    Given A GildedRose with a "Sulfuras, Hand of Ragnaros" item with quality 80 and sellIn 0
    When I update the quality <updates> times
    Then The item should have a quality of 80
    And the item should have a sellIn of 0

    Examples:
      | updates |
      | 1       |
      | 3       |
      | 5       |
      | 6       |

  Scenario Outline: The quality of a Backstage pass increases over time until the concert
    Given A GildedRose with a "Backstage passes to a TAFKAL80ETC concert" item with quality 10 and sellIn <sellInStart>
    When I update the quality 1 time
    Then The item should have a quality of <quality>
    And the item should have a sellIn of <sellInEnd>

    Examples:
      | sellInStart | quality | sellInEnd |
      | 15          | 11      | 14        |
      | 8           | 12      | 7         |
      | 4           | 13      | 3         |
      | 0           | 0       | -1        |

  Scenario Outline: The quality of a Conjured items changes twice as fast
    Given A GildedRose with a <itemName> item with quality <startQuality> and sellIn 10
    When I update the quality 1 time
    Then The item should have a quality of <endQuality>

    Examples:
      | itemName                    | startQuality | endQuality |
      | "Conjured Mana Cake"        | 10           | 8          |
      | "Conjured Aged Brie"        | 10           | 12         |
      | "Conjured Sulfuras"         | 80           | 80         |
      | "Conjured Backstage passes" | 10           | 14         |
