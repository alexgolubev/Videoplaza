package com.videoplaza.campaign;

import com.videoplaza.knapsack.KnapsackIf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a plan for campaign
 * @author alexgolubev
 */
public class CampaignPlan implements KnapsackIf<CampaignPlan, Campaign> {

    private List<Campaign> mCampaigns;
    private int mTotalRevenue;

    /**
     * Creates new empty plan
     */
    public CampaignPlan() {
        mCampaigns = new ArrayList<>();
        mTotalRevenue = 0;
    }

    /**
     * Copy constructor
     * @param pOriginalPlan a plan to copy from
     */
    public CampaignPlan(CampaignPlan pOriginalPlan) {
        mCampaigns = new ArrayList<>(pOriginalPlan.getCampaigns());
        mTotalRevenue = pOriginalPlan.getTotalRevenue();
    }

    public List<Campaign> getCampaigns() {
        return mCampaigns;
    }

    public int getTotalRevenue() {
        return mTotalRevenue;
    }

    /**
     * @return a map with a campaign and number of times it is represented in the plan
     */
    public Map<Campaign, Integer> getNumberOfCampaigns() {
        Map<Campaign, Integer> tCampaignMap = new HashMap<>();
        for (Campaign tCampaign : mCampaigns) {
            if (tCampaignMap.containsKey(tCampaign)) {
                int tNumberOfCampaigns = tCampaignMap.get(tCampaign);
                tCampaignMap.put(tCampaign, ++tNumberOfCampaigns);
            } else {
                tCampaignMap.put(tCampaign, 1);
            }
        }
        return tCampaignMap;
    }

    /**
     *
     * Adds an campaign to the plan. Recalculates total price.
     * @param pCampaign an item to put in knapsack
     * @return a new copy of a campaign plan with a new campaign in it
     */
    @Override
    public CampaignPlan put(Campaign pCampaign) {
        CampaignPlan tCampaignPlan = new CampaignPlan(this);
        tCampaignPlan.mCampaigns.add(pCampaign);
        tCampaignPlan.mTotalRevenue += pCampaign.getRevenue();
        return tCampaignPlan;
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
        return !(mCampaigns != null ? !mCampaigns.equals(that.mCampaigns) : that.mCampaigns != null);
    }

    @Override
    public int hashCode() {
        int tResult = mCampaigns != null ? mCampaigns.hashCode() : 0;
        tResult = 31 * tResult + mTotalRevenue;
        return tResult;
    }
}
