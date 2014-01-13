package com.videoplaza;

import com.videoplaza.campaign.Campaign;
import com.videoplaza.campaign.CampaignPlanFactory;
import com.videoplaza.campaign.CampaignPlan;
import com.videoplaza.campaign.CampaignsInfo;
import com.videoplaza.io.OutputProcessor;
import com.videoplaza.io.Parser;
import com.videoplaza.knapsack.UnboundedKnapsackProblemSolver;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main class for campaign selling planning
 *
 * @author alexgolubev
 */
public class CampaignPlanner {

    private static Timer mTimer = new Timer();
    private static TimerTask mTimerTask;
    private static DecimalFormat mFormat = new DecimalFormat("#.##");
    private static final int cTimerInterval = 1000;
    private static long mStartTime;
    private static String mCleanString = "                                            \r";

    public static void main(String[] pArgs) {
        CampaignsInfo tCampaignInfo = getCampaignInfo(pArgs);
        CampaignPlan tBestCampaignPlan = calculateBestCampaignPlan(tCampaignInfo);
        showResults(tBestCampaignPlan);
    }

    private static void setTimer(final UnboundedKnapsackProblemSolver pSolver) {
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                double tProgress = pSolver.getProgress();
                long tTimePassed = (System.currentTimeMillis() - mStartTime) / 1000;
                String tProgressString =
                        "Thinking... " + mFormat.format(tProgress) + "% completed in " + tTimePassed + " seconds";
                System.out.print(mCleanString + tProgressString + "\r");
            }
        };
        mStartTime = System.currentTimeMillis();
        mTimer.schedule(mTimerTask, 0, cTimerInterval);
    }

    private static void stopTimer() {
        mTimer.cancel();
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
        setTimer(tSolver);
        CampaignPlan tCampaignPlan = tSolver.solve(
                pCampaignInfo.getCampaigns(), pCampaignInfo.getInventory(), new CampaignPlanFactory(pCampaignInfo));
        stopTimer();
        System.out.print(mCleanString);
        return tCampaignPlan;
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
