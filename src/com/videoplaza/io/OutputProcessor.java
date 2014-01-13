package com.videoplaza.io;

import com.videoplaza.campaign.Campaign;
import com.videoplaza.campaign.CampaignPlan;

import java.util.Map;

/**
 * Helps to provide user friendly output about campaign selling plan
 *
 * @author alexgolubev
 */
public class OutputProcessor {

    public void printResults(CampaignPlan pCampaignPlan) {
        StringBuilder tStringBuilder = new StringBuilder();
        Map<Campaign, Integer> tCampaignMap = pCampaignPlan.getCampaigns();
        int tTotalImpressions = 0;
        for (Map.Entry<Campaign, Integer> tEntry : tCampaignMap.entrySet()) {
            Campaign tCampaign = tEntry.getKey();
            String tCustomer = tCampaign.getCustomer();
            int tNumberOfCampaigns = tEntry.getValue();
            int tRevenuePerCustomer = tCampaign.getRevenue() * tNumberOfCampaigns;
            int tImpressionsPerCustomer = tCampaign.getImpressions() * tNumberOfCampaigns;

            tTotalImpressions += tImpressionsPerCustomer;

            tStringBuilder.append(tCustomer);
            tStringBuilder.append(",");
            tStringBuilder.append(tNumberOfCampaigns);
            tStringBuilder.append(",");
            tStringBuilder.append(tImpressionsPerCustomer);
            tStringBuilder.append(",");
            tStringBuilder.append(tRevenuePerCustomer);
            System.out.println(tStringBuilder.toString());
            tStringBuilder.setLength(0);
        }
        tStringBuilder.append(tTotalImpressions);
        tStringBuilder.append(",");
        tStringBuilder.append(pCampaignPlan.getTotalRevenue());
        System.out.println(tStringBuilder.toString());
    }
}
