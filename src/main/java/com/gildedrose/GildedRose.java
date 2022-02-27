package com.gildedrose;

import com.gildedrose.item.CustomItem;
import com.gildedrose.item.CustomItemFactory;
import com.gildedrose.item.Item;
import com.gildedrose.item.QualityValue;

class GildedRose {
    Item[] items;
    private static final int LOWEST_QUALITY_VALUE = 0;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for(Item item: items){
            customItem(item).updateStatus();
            if(hasQualityReachedLowestValue(item)){
                item.quality = LOWEST_QUALITY_VALUE;
            } else if(hasQualityReachedHighestValue(item)){
                item.quality = QualityValue.highestQualityValue(item);
            }
        }
    }

    private CustomItem customItem(Item item){
        return new CustomItemFactory(item).customItem(item);
    }

    private boolean hasQualityReachedLowestValue(Item item){
        return item.quality < LOWEST_QUALITY_VALUE;
    }

    private boolean hasQualityReachedHighestValue(Item item){
        return item.quality > QualityValue.highestQualityValue(item);
    }
}
