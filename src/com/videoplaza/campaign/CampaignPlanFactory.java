package com.videoplaza.campaign;

import com.videoplaza.knapsack.KnapsackFactoryIf;

import java.util.Arrays;

/**
 * Factory to create campaigns
 * @author alexgolubev
 */
public class CampaignPlanFactory implements KnapsackFactoryIf<CampaignPlan, Campaign> {

    private final CampaignsInfo mCampaignsInfo;

    public CampaignPlanFactory(CampaignsInfo pCampaignsInfo) {
        mCampaignsInfo = pCampaignsInfo;
    }

    @Override
    public CampaignPlan newEmptyKnapsack() {
        CampaignPlan tCampaignPlan = new CampaignPlan();
        tCampaignPlan.mCampaignsInfo = mCampaignsInfo;
        tCampaignPlan.mCampaignNumbers = new int[mCampaignsInfo.getCampaigns().length];
        tCampaignPlan.mTotalRevenue = 0;
        return tCampaignPlan;

    }

    @Override
    public CampaignPlan[] newKnapsackArray(int pSize) {
        return new CampaignPlan[pSize];
    }

    @Override
    public CampaignPlan newKnapsackWithItem(CampaignPlan pOriginalKnapsack, Campaign pItem) {
        CampaignPlan tCampaignPlan = getCopyOf(pOriginalKnapsack);
        tCampaignPlan.addCampaign(pItem);
        return tCampaignPlan;
    }

    /**
     * Constructs exact copy of the campaign plan
     * @param pOriginalCampaignPlan original campaign plan
     * @return copy of the campaign plan
     */
    private CampaignPlan getCopyOf(CampaignPlan pOriginalCampaignPlan) {
        CampaignPlan tCampaignPlan = new CampaignPlan();
        int[] tOriginalCampaigns = pOriginalCampaignPlan.getCampaignNumbers();
        tCampaignPlan.mCampaignNumbers = Arrays.copyOf(tOriginalCampaigns, tOriginalCampaigns.length);
        tCampaignPlan.mTotalRevenue = pOriginalCampaignPlan.getTotalRevenue();
        tCampaignPlan.mCampaignsInfo = mCampaignsInfo;
        return tCampaignPlan;
    }
}
