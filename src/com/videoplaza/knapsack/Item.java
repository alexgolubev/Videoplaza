package com.videoplaza.knapsack;

/**
 * Represents an item that one can put in a knapsack
 *
 * @author alexgolubev
 */
public class Item {

    private String mName;
    private int mWeight;
    private int mValue;

    public Item (String pName, int pWeight, int pValue) {
        mName = pName;
        mWeight = pWeight;
        mValue = pValue;
    }

    public String getName() {
        return mName;
    }

    public int getWeight() {
        return mWeight;
    }

    public int getValue() {
        return mValue;
    }
}
