package com.videoplaza.knapsack;

import java.util.*;

/**
 * Class that helps to solve knapsack problem
 * @param <I> type of items
 * @param <K> type of knapsack
 *
 * @author alexgolubev
 */
public class UnboundedKnapsackProblemSolver<I extends ItemIf, K extends KnapsackIf<K, I>> {

    // Here the results that are already calculated once are saved. So we can reuse them.
    // Index represents the size of a knapsack result is calculated for.
    private K[] mResults;
    // Map of smaller knapsacks together with respective items. Used for calculating
    // the best possible combination before actually creating a best value knapsack.
    // It is set of tuples in fact.
    private Map<K, I> mSmallerKnapsacks = new HashMap<>();
    // Special case
    private K mEmptyKnapsack;
    // All available item types
    private I[] mItems;
    // No progress from the beginning
    private int mProgress = 0;
    boolean mIsSolving = false;

    /**
     * Solves unbounded knapsack problem for a given knapsack size and available items
     * @param pItems set of available item types
     * @param pKnapsackArray array of knapsacks used for caching results
     * @param pEmptyKnapsack empty knapsack to fill in
     * @return a knapsack full of items with maximum total value
     */
    public K solve(I[] pItems, K[] pKnapsackArray, K pEmptyKnapsack) {
        mEmptyKnapsack = pEmptyKnapsack;
        mResults = pKnapsackArray;
        mItems = pItems;
        int tKnapsackSize = pKnapsackArray.length - 1;
        mIsSolving = true;
        for (int i = 0; i < tKnapsackSize + 1; i++) {
            synchronized (this) {
                mProgress = i; // to keep track of the progress
            }
            calculateMaxValueKnapsack(i);
        }
        mIsSolving = false;
        return mResults[tKnapsackSize];
    }

    public double getProgress() {
        if (mIsSolving) {
            synchronized (this) {
                return (double) mProgress / (mResults.length - 1) * 100;
            }
        }
        return 0.0;
    }

    private void calculateMaxValueKnapsack(int pMaxWeight) {
        // Creating a list for each of the variants when we take an item and get the best
        // weight distribution for a remainder of the knapsack. It is already calculated.
        for (I tItem : mItems) {
            int tSmallerKnapsackMaxWeight = pMaxWeight - tItem.getWeight();
            K tSmallerKnapsack;
            if (tSmallerKnapsackMaxWeight >= 0) {
                tSmallerKnapsack = mResults[tSmallerKnapsackMaxWeight];
                mSmallerKnapsacks.put(tSmallerKnapsack, tItem);
            }
        }

        K tMaxValueKnapsack = getMaxValueKnapsack();
        // Caching the results. This is the essence of dynamic programming principle.
        mResults[pMaxWeight] = tMaxValueKnapsack;
        // Do not forget to clear the map
        mSmallerKnapsacks.clear();
    }

    private K getMaxValueKnapsack() {
        if (mSmallerKnapsacks == null || mSmallerKnapsacks.size() == 0) {
            return mEmptyKnapsack;
        }

        Set<Map.Entry<K,I>> tKnapsackItemCombinations = mSmallerKnapsacks.entrySet();
        Map.Entry<K, I> tMaxCombination = null;
        int tMaxValue = -1;
        for (Map.Entry<K, I> tKnapsackItemCombination : tKnapsackItemCombinations) {
            K tKnapsack = tKnapsackItemCombination.getKey();
            I tItem = tKnapsackItemCombination.getValue();
            int tKnapsackValue = tKnapsack.getTotalValue() + tItem.getValue();
            if (tKnapsackValue > tMaxValue) {
                tMaxCombination = tKnapsackItemCombination;
                tMaxValue = tKnapsackValue;
            }
        }
        K tMaxCombinationKnapsack = tMaxCombination.getKey();
        I tMaxCombinationItem = tMaxCombination.getValue();
        K tMaxValueKnapsack = tMaxCombinationKnapsack.put(tMaxCombinationItem);
        return tMaxValueKnapsack;
    }
}
