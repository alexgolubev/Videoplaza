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
        addCampaign(tCampaignPlan, pItem);
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

    /**
     * Adds campaign to the campaign plan
     * @param pCampaignPlan campaign plan to add to
     * @param pCampaign campaign to add
     */
    private void addCampaign(CampaignPlan pCampaignPlan, Campaign pCampaign) {
        Campaign[] tCampaigns = pCampaignPlan.mCampaignsInfo.getCampaigns();
        for (int i = 0; i < tCampaigns.length; i++) {
            if (tCampaigns[i].equals(pCampaign)) {
                pCampaignPlan.mCampaignNumbers[i]++;
                break;
            }
        }
        pCampaignPlan.mTotalRevenue += pCampaign.getRevenue();
    }
}
