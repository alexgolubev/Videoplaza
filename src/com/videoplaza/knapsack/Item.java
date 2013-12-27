package com.videoplaza.knapsack;

/**
 * Represents an item that one can put in a knapsack
 *
 * @author alexgolubev
 */
public class Item {

    private String mName;
    private int mSize;
    private int mValue;

    public Item (String pName, int pSize, int pValue) {
        mName = pName;
        mSize = pSize;
        mValue = pValue;
    }

    public String getName() {
        return mName;
    }

    public int getSize() {
        return mSize;
    }

    public int getValue() {
        return mValue;
    }
}
