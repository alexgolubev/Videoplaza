package com.videoplaza;

import com.videoplaza.campaign.Campaign;
import com.videoplaza.campaign.CampaignPlan;
import com.videoplaza.campaign.CampaignsInfo;
import com.videoplaza.io.OutputProcessor;
import com.videoplaza.io.Parser;
import com.videoplaza.knapsack.UnboundedKnapsackProblemSolver;

import java.io.IOException;
import java.nio.file.InvalidPathException;

/**
 * Main class for campaign selling planning
 *
 * @author alexgolubev
 */
public class CampaignPlanner {

    public static void main(String[] pArgs) {
        CampaignsInfo tCampaignInfo = getCampaignInfo(pArgs);
        CampaignPlan tBestCampaignPlan = calculateBestCampaignPlan(tCampaignInfo);
        showResults(tBestCampaignPlan);
    }

    private static CampaignsInfo getCampaignInfo(String[] pArgs) {
        String tFilePath = null;
        try {
            tFilePath = getFilePathArgument(pArgs);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        Parser tParser = new Parser();
        CampaignsInfo tCampaignInfo = null;
        try {
            tCampaignInfo = tParser.parse(tFilePath);
        } catch (IOException e) {
            System.out.println("Specified path is invalid");
            System.exit(1);
        }
        return tCampaignInfo;
    }

    private static CampaignPlan calculateBestCampaignPlan(CampaignsInfo pCampaignInfo) {
        UnboundedKnapsackProblemSolver<Campaign, CampaignPlan> tSolver = new UnboundedKnapsackProblemSolver<>();
        return tSolver.solve(
                pCampaignInfo.getCampaigns(), pCampaignInfo.getInventory(), new CampaignPlan());
    }

    private static void showResults(CampaignPlan pCampaignPlan) {
        OutputProcessor tProcessor = new OutputProcessor();
        tProcessor.printResults(pCampaignPlan);
    }

    private static String getFilePathArgument(String[] pArgs) throws IllegalArgumentException{
        if (pArgs.length != 1) {
            throw new IllegalArgumentException("One and only one argument is expected");
        }
        return pArgs[0];
    }
}
