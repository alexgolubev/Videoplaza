package com.videoplaza.in;

import java.util.List;

/**
 * Information about available campaigns and monthly inventory of impressions is kept here
 *
 * @author alexgolubev
 */
public class CampaignsInfo {

    private int mInventory;
    private List<Campaign> mCampaigns;

    public int getInventory() {
        return mInventory;
    }

    public List<Campaign> getCampaigns() {
        return mCampaigns;
    }
}
