package com.videoplaza.campaign;

import java.util.Set;

/**
 * Information about available campaigns and monthly inventory of impressions is kept here
 *
 * @author alexgolubev
 */
public class CampaignsInfo {

    private int mInventory;
    private Set<Campaign> mCampaigns;

    public CampaignsInfo(int pInventory, Set<Campaign> pCampaigns) {
        mInventory = pInventory;
        mCampaigns = pCampaigns;
    }

    public int getInventory() {
        return mInventory;
    }

    public Set<Campaign> getCampaigns() {
        return mCampaigns;
    }
}
