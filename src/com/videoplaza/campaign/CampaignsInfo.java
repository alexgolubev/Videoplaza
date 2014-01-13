package com.videoplaza.campaign;

import java.util.Set;

/**
 * Information about available campaigns and monthly inventory of impressions is kept here
 *
 * @author alexgolubev
 */
public class CampaignsInfo {

    private int mInventory;
    private Campaign[] mCampaigns;

    public CampaignsInfo(int pInventory, Set<Campaign> pCampaigns) {
        mInventory = pInventory;
        mCampaigns = pCampaigns.toArray(new Campaign[pCampaigns.size()]);
    }

    public int getInventory() {
        return mInventory;
    }

    public Campaign[] getCampaigns() {
        return mCampaigns;
    }
}
