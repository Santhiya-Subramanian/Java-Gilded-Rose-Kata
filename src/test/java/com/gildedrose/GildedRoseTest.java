package com.gildedrose;

import com.gildedrose.item.CustomItem;
import com.gildedrose.item.CustomItemFactory;
import com.gildedrose.item.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void fixme() {
        //Item[] items = new Item[] { new Item("fixme", 0, 0) };
        GildedRose app = newGildedRose("fixme", 0, 0);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

    @Test
    void agedBrie_decrease_sellIn_byday_eachTime(){
        GildedRose app = newGildedRose(CustomItemFactory.A_BRIE, 0, 0);
        app.updateQuality();
        assertEquals(-1, product_sellIn_byDay(app));
    }

    @Test
    void backstagePasses_decrease_sellin_byday_eachTime(){
        GildedRose app = newGildedRose(CustomItemFactory.BACKSTAGE_PASSES, 0, 0);
        app.updateQuality();
        assertEquals(-1, product_sellIn_byDay(app));
    }

    @Test
    void sulfuras_never_decrease_sellin_Quality_byday_eachTime(){
        GildedRose app = newGildedRose(CustomItemFactory.SULFURAS, 1, 1);
        app.updateQuality();
        assertEquals(1, product_sellIn_byDay(app));
        assertEquals(1, product_quality_byDay(app));
    }

    @Test
    void conjured_decrease_sellin_byday_eachTime(){
        GildedRose app = newGildedRose(CustomItemFactory.CONJURED, 0, 0);
        app.updateQuality();
        assertEquals(-1, product_sellIn_byDay(app));
    }

    @Test
    void agedBrie_increase_quality_byday_eachTime(){
        GildedRose app = newGildedRose(CustomItemFactory.A_BRIE, 1, 1);
        app.updateQuality();
        assertEquals(2, product_quality_byDay(app));
    }

    // sellin <= 5 days then Quality = Quality + 3
    @Test
    void backstagePasses_increase_quality_byday_eachTime(){
        GildedRose app = newGildedRose(CustomItemFactory.BACKSTAGE_PASSES, 5, 1);
        app.updateQuality();
        assertEquals(4, product_quality_byDay(app));
    }

    // sellin value more than 10 quality increase by one
    @Test
    void backstagePasses_increase_quality_byOne_when_sellIn_moreThan_ten(){
        GildedRose app = newGildedRose(CustomItemFactory.BACKSTAGE_PASSES, 11, 10);
        app.updateQuality();
        assertEquals(12, product_quality_byDay(app));
    }

    // sellin value < 10 && > 5 quality increase by two
    @Test
    void backstagePasses_increase_quality_byTwo_when_sellIn_lessThanTen_moreThanFive(){
        GildedRose app = newGildedRose(CustomItemFactory.BACKSTAGE_PASSES, 8, 4);
        app.updateQuality();
        assertEquals(6, product_quality_byDay(app));
    }

    // sellin days passed then Quality = 0
    @Test
    void backstagePasses_quality_drops_zero_when_sellIn_passed(){
        GildedRose app = newGildedRose(CustomItemFactory.BACKSTAGE_PASSES, 0, 50);
        app.updateQuality();
        assertEquals(0, product_quality_byDay(app));
    }

    @Test
    void sulfuras_increase_quality_byday_eachTime(){
        GildedRose app = newGildedRose(CustomItemFactory.SULFURAS, 1, 1);
        app.updateQuality();
        assertEquals(1, product_quality_byDay(app));
    }

    @Test
    void conjured_decrease_quality_bytwo_when_sellIn_isAbove_zero(){
        GildedRose app = newGildedRose(CustomItemFactory.CONJURED, 2, 5);
        app.updateQuality();
        assertEquals(3, product_quality_byDay(app));
    }

    @Test
    void conjured_decrease_quality_byfour_when_sellIn_isBelow_zero_orLess(){
        GildedRose app = newGildedRose(CustomItemFactory.CONJURED, 0, 5);
        app.updateQuality();
        assertEquals(1, product_quality_byDay(app));
    }

    @Test
    void conjured_quality_cannot_goBelow_zero(){
        GildedRose app = newGildedRose(CustomItemFactory.CONJURED, 0, 0);
        app.updateQuality();
        assertEquals(0, product_quality_byDay(app));
    }

    @Test
    void product_quality_cannot_goBeyond_fifty(){
        GildedRose app = newGildedRose(CustomItemFactory.A_BRIE, 3, 49);
        GildedRose app_1 = newGildedRose(CustomItemFactory.BACKSTAGE_PASSES, 5, 51);
        app.updateQuality();
        app_1.updateQuality();
        assertEquals(50, product_quality_byDay(app));
        assertEquals(50, product_quality_byDay(app_1));
    }

    @Test
    void sulfuras_quality_always_eighty(){
        GildedRose app = newGildedRose(CustomItemFactory.SULFURAS, 1, 80);
        app.updateQuality();
        assertEquals(80, product_quality_byDay(app));
    }

    @Test
    void product_quality_cannot_goBelow_Zero(){
        GildedRose app = newGildedRose("Elixir of the Mongoose", 0, 0);
        app.updateQuality();
        assertEquals(0, product_quality_byDay(app));
    }

    @Test
    void other_product_quality_decreases_byTwo_when_sellIn_passed(){
        GildedRose app = newGildedRose("+5 Dexterity Vest", 0, 6);
        app.updateQuality();
        assertEquals(4, product_quality_byDay(app));
    }

    private GildedRose newGildedRose(String product_name, int product_sellIn, int product_Quality){
        Item[] items = new Item[] { new Item(product_name, product_sellIn, product_Quality ) };
        return new GildedRose(items);
    }

    private int product_sellIn_byDay(GildedRose app){
        return app.items[0].sellIn;
    }

    private int product_quality_byDay(GildedRose app){
        return app.items[0].quality;
    }

}
