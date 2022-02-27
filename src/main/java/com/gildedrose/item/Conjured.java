package com.gildedrose.item;

public class Conjured extends RegularItem{

    public Conjured(Item item) {
        super(item);
    }

    @Override
    public int decreaseValueForSellInOverZero() {
        return 2;
    }
}
