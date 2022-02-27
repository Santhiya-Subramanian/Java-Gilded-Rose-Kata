package com.gildedrose.item;

public class AgedBrie implements CustomItem {
    private final Item item;

    public AgedBrie(Item item){
        this.item = item;
    }

    public void updateStatus(){
        decreaseSellInByOne();
        increaseQualityByOne();
    }

    private void decreaseSellInByOne(){
        item.sellIn -= 1;
    }
    private void increaseQualityByOne(){
        item.quality += 1;
    }
}
