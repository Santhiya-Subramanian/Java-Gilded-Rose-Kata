package com.gildedrose.item;

public class BackstagePasses implements CustomItem {
    private final Item item;

    public BackstagePasses(Item item){
        this.item = item;
    }

    public void updateStatus(){
        decreaseSellInByOne();
        if (sellInValueAbove(10)){
            increaseQualityBy(1);
        }else if (sellInValueAbove(5)){
            increaseQualityBy(2);
        }else if (sellInValueAbove(0)){
            increaseQualityBy(3);
        }else {
            qualityZero();
        }
    }

    private void decreaseSellInByOne(){
        item.sellIn -= 1;
    }

    private void increaseQualityBy(int qualityValue){
        item.quality += qualityValue;
    }

    private boolean sellInValueAbove(int numberOfDays){
        return item.sellIn > numberOfDays;
    }

    private void qualityZero(){
        item.quality = 0;
    }
}
