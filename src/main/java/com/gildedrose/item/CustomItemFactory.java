package com.gildedrose.item;

import java.util.HashMap;
import java.util.Map;

public class CustomItemFactory {
    private final static Map<String, CustomItem> ITEM_LIST = new HashMap<>();
    public final static  String A_BRIE = "Aged Brie";
    public final static  String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public final static  String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public final static  String CONJURED = "Conjured";

    public CustomItemFactory(Item item){
        ITEM_LIST.put(SULFURAS, new Sulfuras());
        ITEM_LIST.put(A_BRIE, new AgedBrie(item));
        ITEM_LIST.put(BACKSTAGE_PASSES, new BackstagePasses(item));
        ITEM_LIST.put(CONJURED, new Conjured(item));
    }

    private boolean isRegularItem(Item item){
        return !ITEM_LIST.keySet().contains(item.name);
    }

    public CustomItem customItem(Item item){
        if(isRegularItem(item)){
            return new RegularItem(item);
        }
        return ITEM_LIST.get(item.name);
    }
}
