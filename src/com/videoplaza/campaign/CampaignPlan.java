package com.videoplaza.campaign;

import com.videoplaza.knapsack.KnapsackIf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a plan for campaign. Should be light-weight object since a lot of them are created for
 * resolving the planning problem.
 * @author alexgolubev
 */
public class CampaignPlan implements KnapsackIf {

    CampaignsInfo mCampaignsInfo;
    int[] mCampaignNumbers;
    int mTotalRevenue;

    CampaignPlan() {}

    public int getTotalRevenue() {
        return mTotalRevenue;
    }

    /**
     * @return a map with a campaign and number of times it is represented in the plan
     */
    public Map<Campaign, Integer> getCampaigns() {
        Map<Campaign, Integer> tCampaignIntegerMap = new HashMap<>();
        Campaign[] tCampaigns = mCampaignsInfo.getCampaigns();
        for (int i = 0; i < tCampaigns.length; i++) {
            tCampaignIntegerMap.put(tCampaigns[i], mCampaignNumbers[i]);
        }
        return tCampaignIntegerMap;
    }

    @Override
    public int getTotalValue() {
        return mTotalRevenue;
    }

    @Override
    public boolean equals(Object pObject) {
        if (this == pObject) return true;
        if (pObject == null || getClass() != pObject.getClass()) return false;

        CampaignPlan that = (CampaignPlan) pObject;

        if (mTotalRevenue != that.mTotalRevenue) return false;
        return !(mCampaignNumbers != null ? !Arrays.equals(mCampaignNumbers, that.mCampaignNumbers) :
                that.mCampaignNumbers != null);
    }

    @Override
    public int hashCode() {
        int tResult = mCampaignNumbers != null ? Arrays.hashCode(mCampaignNumbers) : 0;
        tResult = 31 * tResult + mTotalRevenue;
        return tResult;
    }

    public int[] getCampaignNumbers() {
        return mCampaignNumbers;
    }
}
