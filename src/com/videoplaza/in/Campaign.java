package com.videoplaza.in;

/**
 * This class represents available campaign
 *
 * @author alexgolubev
 */
public class Campaign {

    private String mCustomer;
    private int mImpressions;
    private int mPrice;

    public Campaign(String pCustomer, int pImpressions, int pPrice) {
        mCustomer = pCustomer;
        mImpressions = pImpressions;
        mPrice = pPrice;
    }

    public String getCustomer() {
        return mCustomer;
    }

    public int getImpressions() {
        return mImpressions;
    }

    public int getPrice() {
        return mPrice;
    }
}
