package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
    
    @Test
    @DisplayName("Test Foo")
    void testFoo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, app.items[0].sellIn, "SellIn");
        assertEquals(0, app.items[0].quality, "Quality");
    }
    
    @ParameterizedTest(name = "Test para {0} con sellIn={1} quality={2} expected sellIn={3} y expected quality={4}")
	@CsvSource({"Aged Brie,0,0,-1,2", "Aged Brie,2,0,1,1"})
    @DisplayName("Aged Brie")
    void testAgedBrie(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
 
        assertEquals(expectedSellIn, app.items[0].sellIn, "SellIn");
        assertEquals(expectedQuality, app.items[0].quality, "Quality");
    }
    
    @ParameterizedTest(name = "Test para {0} con sellIn={1} quality={2} expected sellIn={3} y expected quality={4}")
	@CsvSource({"'Sulfuras, Hand of Ragnaros',0,80,0,80"})
    @DisplayName("Sulfuras")
    void testSulfuras(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
 
        assertEquals(expectedSellIn, app.items[0].sellIn, "SellIn");
        assertEquals(expectedQuality, app.items[0].quality, "Quality");
    }
    
    @ParameterizedTest (name = "Test para {0} con sellIn={1} quality={2} expected sellIn={3} y expected quality={4}")
	@CsvSource({"'Backstage passes to a TAFKAL80ETC concert',0,0,-1,0",
		"'Backstage passes to a TAFKAL80ETC concert',7,0,6,2",
		"'Backstage passes to a TAFKAL80ETC concert',2,0,1,3"	
		})
    @DisplayName("Backstage Passes")
    void testBackstageExpired(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
 
        assertEquals(expectedSellIn, app.items[0].sellIn, "SellIn");
        assertEquals(expectedQuality, app.items[0].quality, "Quality");
    }
    
    @ParameterizedTest (name = "Test para {0} con sellIn={1} quality={2} expected sellIn={3} y expected quality={4}")
	@CsvSource({"'Conjured Mana Cake',0,0,-1,2"})
    @DisplayName("Conjured")
    void testConjured(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
 
        assertEquals(expectedSellIn, app.items[0].sellIn, "SellIn");
        assertEquals(expectedQuality, app.items[0].quality, "Quality");
    }
    

    

    

    



}
