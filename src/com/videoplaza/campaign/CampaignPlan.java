package com.videoplaza.campaign;

import com.videoplaza.knapsack.KnapsackIf;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a plan for campaign
 * @author alexgolubev
 */
public class CampaignPlan implements KnapsackIf<CampaignPlan, Campaign> {

    private List<Campaign> mCampaigns;
    private int mTotalPrice;

    /**
     * Creates new empty plan
     */
    public CampaignPlan() {
        mCampaigns = new ArrayList<>();
        mTotalPrice = 0;
    }

    /**
     * Copy constructor
     * @param pOriginalPlan a plan to copy from
     */
    public CampaignPlan(CampaignPlan pOriginalPlan) {
        mCampaigns = new ArrayList<>(pOriginalPlan.getCampaigns());
        mTotalPrice = pOriginalPlan.getTotalPrice();
    }

    public List<Campaign> getCampaigns() {
        return mCampaigns;
    }

    public int getTotalPrice() {
        return mTotalPrice;
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
        tCampaignPlan.mTotalPrice += pCampaign.getWeight();
        return tCampaignPlan;
    }

    @Override
    public int getTotalWeight() {
        return mTotalPrice;
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

        if (mTotalPrice != that.mTotalPrice) return false;
        if (mCampaigns != null ? !mCampaigns.equals(that.mCampaigns) : that.mCampaigns != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int tResult = mCampaigns != null ? mCampaigns.hashCode() : 0;
        tResult = 31 * tResult + mTotalPrice;
        return tResult;
    }
}
