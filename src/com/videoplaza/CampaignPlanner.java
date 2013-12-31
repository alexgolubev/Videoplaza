package com.videoplaza;

import com.videoplaza.campaign.Campaign;
import com.videoplaza.campaign.CampaignPlan;
import com.videoplaza.campaign.CampaignsInfo;
import com.videoplaza.io.OutputProcessor;
import com.videoplaza.io.Parser;
import com.videoplaza.knapsack.UnboundedKnapsackProblemSolver;

import java.io.IOException;
import java.util.List;

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

    private static CampaignPlan calculateBestCampaignPlan(CampaignsInfo pCampaignInfo) {
        UnboundedKnapsackProblemSolver<Campaign, CampaignPlan> tSolver = new UnboundedKnapsackProblemSolver<>();
        CampaignPlan tBestCampaignPlan = tSolver.solve(
                pCampaignInfo.getCampaigns(), pCampaignInfo.getInventory(), new CampaignPlan());
        return tBestCampaignPlan;
    }

    private static void showResults(CampaignPlan pCampaignPlan) {
        OutputProcessor tProcessor = new OutputProcessor();
        tProcessor.printResults(pCampaignPlan);
    }

    private static String getFilePathArgument(String[] pArgs) throws IllegalArgumentException{
        if (pArgs.length != 1) {
            throw new IllegalArgumentException("Provided file path is incorrect");
        }
        return pArgs[0];
    }
}
