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
public class CampaignPlan implements KnapsackIf<CampaignPlan, Campaign> {

    private final CampaignsInfo mCampaignsInfo;
    private int[] mCampaignNumbers;
    private int mTotalRevenue;

    /**
     * Creates new empty plan
     */
    public CampaignPlan(CampaignsInfo pCampaignsInfo) {
        mCampaignsInfo = pCampaignsInfo;
        mCampaignNumbers = new int[mCampaignsInfo.getCampaigns().length];
        mTotalRevenue = 0;
    }

    /**
     * Copy constructor
     * @param pOriginalPlan a plan to copy from
     */
    public CampaignPlan(CampaignPlan pOriginalPlan) {
        int[] tOriginalCampaigns = pOriginalPlan.getCampaignNumbers();
        mCampaignNumbers = Arrays.copyOf(tOriginalCampaigns, tOriginalCampaigns.length);
        mTotalRevenue = pOriginalPlan.getTotalRevenue();
        mCampaignsInfo = pOriginalPlan.mCampaignsInfo;
    }

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

    /**
     *
     * Adds an campaign to the plan. Recalculates total revenue.
     * @param pCampaign an item to put in knapsack
     * @return a new copy of a campaign plan with a new campaign in it
     */
    @Override
    public CampaignPlan put(Campaign pCampaign) {
        CampaignPlan tCampaignPlan = new CampaignPlan(this);
        addCampaign(tCampaignPlan, pCampaign);
        return tCampaignPlan;
    }

    private static void addCampaign(CampaignPlan pCampaignPlan, Campaign pCampaign) {
        Campaign[] tCampaigns = pCampaignPlan.mCampaignsInfo.getCampaigns();
        for (int i = 0; i < tCampaigns.length; i++) {
            if (tCampaigns[i].equals(pCampaign)) {
                pCampaignPlan.mCampaignNumbers[i]++;
                break;
            }
        }
        pCampaignPlan.mTotalRevenue += pCampaign.getRevenue();
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
        return !(mCampaignNumbers != null ? !mCampaignNumbers.equals(that.mCampaignNumbers) :
                that.mCampaignNumbers != null);
    }

    @Override
    public int hashCode() {
        int tResult = mCampaignNumbers != null ? mCampaignNumbers.hashCode() : 0;
        tResult = 31 * tResult + mTotalRevenue;
        return tResult;
    }

    public int[] getCampaignNumbers() {
        return mCampaignNumbers;
    }
}
