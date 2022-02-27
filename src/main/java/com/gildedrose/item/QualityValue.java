package com.gildedrose.item;

public class QualityValue {
    public static int highestQualityValue(Item item){
        if(item.name.equals(CustomItemFactory.SULFURAS)){
            return 80;
        }
        return 50;
    }
}
