package com.gildedrose.item;

public class RegularItem implements CustomItem {
    private final Item item;

    public RegularItem(Item item) {
        this.item = item;
    }

    public void updateStatus() {
        decreaseSellInByOne();
        if (sellInValueOverZero()){
            decreaseQualityBy(decreaseValueForSellInOverZero());
        }else {
            decreaseQualityBy(decreaseValueForSellInZeroOrLess());
        }
    }

    private void decreaseSellInByOne(){
        item.sellIn -= 1;
    }

    private boolean sellInValueOverZero(){
        return item.sellIn > 0;
    }

    private void decreaseQualityBy(int qualityValue){
        item.quality -= qualityValue;
    }

    private int decreaseValueForSellInZeroOrLess(){
        return decreaseValueForSellInOverZero() * 2;
    }

    public int decreaseValueForSellInOverZero(){
        return 1;
    }

}
