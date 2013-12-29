package com.videoplaza.io;

import com.videoplaza.campaign.Campaign;
import com.videoplaza.campaign.Campaigns;
import com.videoplaza.campaign.CampaignsInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser for a file with information about available campaigns and monthly inventory
 *
 * @author alexgolubev
 */
public class Parser {

    private static final Charset ENCODING = StandardCharsets.UTF_8;

    public CampaignsInfo parse(String pFilePath) throws IOException {

        int tInventory;
        List<Campaign> tCampaigns;

        Path tPath = Paths.get(pFilePath);
        try (BufferedReader tReader = Files.newBufferedReader(tPath, ENCODING)){
            String tLine = tReader.readLine();
            tInventory = Integer.valueOf(tLine);

            tCampaigns = new ArrayList<>();
            String tDelimiter = ",";
            while ((tLine = tReader.readLine()) != null) {
                String[] tStringValues = tLine.split(tDelimiter);
                String tCustomer = tStringValues[0];
                int tImpressions = Integer.valueOf(tStringValues[1]);
                int tPrice = Integer.valueOf(tStringValues[2]);

                Campaign tCampaign = Campaigns.newCampaign(tCustomer, tImpressions, tPrice);
                tCampaigns.add(tCampaign);
            }
        }

        return new CampaignsInfo(tInventory, tCampaigns);
    }

}
