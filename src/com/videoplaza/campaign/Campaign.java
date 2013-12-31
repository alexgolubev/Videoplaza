package com.videoplaza.campaign;

import com.videoplaza.knapsack.ItemIf;

/**
 * This class represents available campaign
 *
 * @author alexgolubev
 */
public class Campaign implements ItemIf{

    private String mCustomer;
    private int mImpressions;
    private int mRevenue;

    public Campaign(String pCustomer, int pImpressions, int pRevenue) {
        mCustomer = pCustomer;
        mImpressions = pImpressions;
        mRevenue = pRevenue;
    }

    public String getCustomer() {
        return mCustomer;
    }

    public int getImpressions() {
        return mImpressions;
    }

    public int getRevenue() {
        return mRevenue;
    }

    @Override
    public String getName() {
        return mCustomer;
    }

    @Override
    public int getWeight() {
        return mImpressions;
    }

    @Override
    public int getValue() {
        return mRevenue;
    }

    @Override
    public boolean equals(Object pObject) {
        if (this == pObject) return true;
        if (pObject == null || getClass() != pObject.getClass()) return false;

        Campaign tCampaign = (Campaign) pObject;

        if (mImpressions != tCampaign.mImpressions) return false;
        if (mRevenue != tCampaign.mRevenue) return false;
        if (!mCustomer.equals(tCampaign.mCustomer)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int tResult = mCustomer.hashCode();
        tResult = 31 * tResult + mImpressions;
        tResult = 31 * tResult + mRevenue;
        return tResult;
    }
}
