package com.videoplaza.out;

import com.videoplaza.in.Campaign;
import com.videoplaza.in.Campaigns;

import java.util.List;
import java.util.Map;

/**
 * Helps to provide user friendly output about campaign selling plan
 *
 * @author alexgolubev
 */
public class OutputProcessor {

    public void printResults(Map<String, Integer> pCampaignDistribution, List<Campaign> pCampaigns) {
        StringBuilder tStringBuilder = new StringBuilder();
        int tAllImpressions = 0;
        int tTotalRevenue = 0;
        for (Map.Entry<String, Integer> tEntry : pCampaignDistribution.entrySet()) {
            String tCustomer = tEntry.getKey();
            tStringBuilder.append(tCustomer);
            tStringBuilder.append(",");
            int tNumberOfCampaigns = tEntry.getValue();
            tStringBuilder.append(tNumberOfCampaigns);
            tStringBuilder.append(",");
            int tImpressionsPerCustomer = calculateImpressionsPerCustomer(pCampaigns, tCustomer, tNumberOfCampaigns);
            tAllImpressions += tImpressionsPerCustomer;
            tStringBuilder.append(tImpressionsPerCustomer);
            tStringBuilder.append(",");
            int tRevenuePerCustomer = calculateRevenuePerCustomer(pCampaigns, tCustomer, tNumberOfCampaigns);
            tTotalRevenue += tRevenuePerCustomer;
            tStringBuilder.append(tRevenuePerCustomer);
            System.out.println(tStringBuilder.toString());
            tStringBuilder.setLength(0);
        }
        tStringBuilder.append(tAllImpressions);
        tStringBuilder.append(",");
        tStringBuilder.append(tTotalRevenue);
        System.out.println(tStringBuilder.toString());
}

    private int calculateRevenuePerCustomer(List<Campaign> pCampaigns, String pCustomer, int pNumberOfCampaigns) {
        int tPrice = Campaigns.findCampaignPriceByCustomer(pCampaigns, pCustomer);
        return tPrice * pNumberOfCampaigns;
    }

    private int calculateImpressionsPerCustomer(List<Campaign> pCampaigns, String pCustomer, int pNumberOfCampaigns) {
        int tImpressions = Campaigns.findCampaignImpressionsByCustomer(pCampaigns, pCustomer);
        return tImpressions * pNumberOfCampaigns;
    }
}
