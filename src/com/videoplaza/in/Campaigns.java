package com.videoplaza.in;

import java.util.List;

/**
 * Factory and convenient methods for {@link com.videoplaza.in.Campaign}
 *
 * @author alexgolubev
 */
public class Campaigns {

    public static Campaign newCampaign(String pCustomer, int pImpressions, int pPrice) {
        return new Campaign(pCustomer, pImpressions, pPrice);
    }

    /**
     * @param pCampaigns
     * @param pCustomer
     * @return price of a campaign or -1 if no campaign found
     */
    public static int findCampaignPriceByCustomer(List<Campaign> pCampaigns, String pCustomer) {
        for (Campaign tCampaign : pCampaigns) {
            if (tCampaign.getCustomer().equals(pCustomer)) {
                return tCampaign.getPrice();
            }
        }
        return -1;
    }

    /**
     * @param pCampaigns
     * @param pCustomer
     * @return number of a campaign impressions or -1 if no campaign found
     */
    public static int findCampaignImpressionsByCustomer(List<Campaign> pCampaigns, String pCustomer) {
        for (Campaign tCampaign : pCampaigns) {
            if (tCampaign.getCustomer().equals(pCustomer)) {
                return tCampaign.getImpressions();
            }
        }
        return -1;
    }
}
