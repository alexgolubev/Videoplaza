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
    private int mTotalImpressions;

    /**
     * Creates new empty plan
     */
    public CampaignPlan() {
        mCampaigns = new ArrayList<>();
        mTotalImpressions = 0;
    }

    /**
     * Copy constructor
     * @param pOriginalPlan a plan to copy from
     */
    public CampaignPlan(CampaignPlan pOriginalPlan) {
        mCampaigns = new ArrayList<>(pOriginalPlan.getCampaigns());
        mTotalImpressions = pOriginalPlan.getTotalImpressions();
    }

    public List<Campaign> getCampaigns() {
        return mCampaigns;
    }

    public int getTotalImpressions() {
        return mTotalImpressions;
    }

    /**
     * @return a map with a campaign and number of times it is represented in the plan
     */
    public Map<Campaign, Integer> getNumberOfCampaigns() {
        Map<Campaign, Integer> tCampaignMap = new HashMap<>();
        for (Campaign tCampaign : mCampaigns) {
            if (tCampaignMap.containsKey(tCampaign)) {
                int tNumberOfCampaigns = tCampaignMap.get(tCampaignMap);
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
        tCampaignPlan.mTotalImpressions += pCampaign.getWeight();
        return tCampaignPlan;
    }

    @Override
    public int getTotalWeight() {
        return mTotalImpressions;
    }

    @Override
    public List<Campaign> getItems() {
        return mCampaigns;
    }

    @Override
    public boolean equals(Object pObject) {
        if (this == pObject) return true;
        if (pObject == null || getClass() != pObject.getClass()) return false;

        CampaignPlan that = (CampaignPlan) pObject;

        if (mTotalImpressions != that.mTotalImpressions) return false;
        if (mCampaigns != null ? !mCampaigns.equals(that.mCampaigns) : that.mCampaigns != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int tResult = mCampaigns != null ? mCampaigns.hashCode() : 0;
        tResult = 31 * tResult + mTotalImpressions;
        return tResult;
    }
}
