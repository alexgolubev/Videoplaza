package com.videoplaza;

import com.videoplaza.in.Campaign;
import com.videoplaza.in.CampaignsInfo;
import com.videoplaza.in.Parser;
import com.videoplaza.knapsack.Item;
import com.videoplaza.knapsack.KnapsackProblemSolver;
import com.videoplaza.out.OutputProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Main class for campaign selling planning
 *
 * @author alexgolubev
 */
public class Planner {

    public static void main(String[] pArgs) {
        CampaignsInfo tCampaignInfo = getCampaignInfo(pArgs);
        Map<String, Integer> tBestCampaignDistribution = calculateBestCampaignDistribution(tCampaignInfo);
        showResults(tBestCampaignDistribution, tCampaignInfo.getCampaigns());
    }

    private static CampaignsInfo getCampaignInfo(String[] pArgs) {
        String tFilePath = null;
        try {
            tFilePath = getFilePathArgument(pArgs);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Parser tParser = new Parser();
        CampaignsInfo tCampaignInfo = null;
        try {
            tCampaignInfo = tParser.parse(tFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return tCampaignInfo;
    }

    private static Map<String, Integer> calculateBestCampaignDistribution(CampaignsInfo pCampaignInfo) {
        KnapsackProblemSolver tSolver = new KnapsackProblemSolver();
        List<Item> tItems = convertCampaignsToItems(pCampaignInfo.getCampaigns());
        Map<String, Integer> tBestCampaignDistribution = tSolver.solve(pCampaignInfo.getInventory(), tItems);
        return tBestCampaignDistribution;
    }

    private static void showResults(Map<String, Integer> pBestCampaignDistribution, List<Campaign> pCampaigns) {
        OutputProcessor tProcessor = new OutputProcessor();
        tProcessor.printResults(pBestCampaignDistribution, pCampaigns);
    }

    private static String getFilePathArgument(String[] pArgs) throws IllegalArgumentException{
        if (pArgs.length != 1) {
            throw new IllegalArgumentException("Provided file path is incorrect");
        }
        return pArgs[0];
    }

    private static List<Item> convertCampaignsToItems(List<Campaign> pCampaigns) {
        List<Item> tItems = new ArrayList<Item>();
        for (Campaign tCampaign : pCampaigns) {
            Item tItem = new Item(tCampaign.getCustomer(), tCampaign.getImpressions(), tCampaign.getPrice());
            tItems.add(tItem);
        }
        return tItems;
    }
}
