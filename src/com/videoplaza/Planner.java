package com.videoplaza;

import com.videoplaza.in.Campaign;
import com.videoplaza.in.CampaignsInfo;
import com.videoplaza.in.Parser;
import com.videoplaza.knapsack.Item;
import com.videoplaza.knapsack.KnapsackProblemSolver;
import com.videoplaza.out.OutputProcessor;

import java.util.List;
import java.util.Map;

/**
 * Main class for campaign selling planning
 *
 * @author alexgolubev
 */
public class Planner {

    public static void main(String[] pArgs) {
        String tFilePath = getFilePathFromArguments();
        Parser tParser = new Parser();
        CampaignsInfo tCampaignInfo = tParser.parse(tFilePath);

        KnapsackProblemSolver tSolver = new KnapsackProblemSolver();
        List<Item> tItems = convertCampaignsToItems(tCampaignInfo.getCampaigns());
        Map<String, Integer> tBestCampaignDistribution = tSolver.solve(tCampaignInfo.getInventory(), tItems);

        OutputProcessor tProcessor = new OutputProcessor();
        tProcessor.printResults(tBestCampaignDistribution);
    }

    private static List<Item> convertCampaignsToItems(List<Campaign> pCampaigns) {
        return null; //TODO
    }

    private static String getFilePathFromArguments() {
        return ""; //TODO
    }
}
