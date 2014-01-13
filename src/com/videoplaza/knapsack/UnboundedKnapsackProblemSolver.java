package com.videoplaza.knapsack;

import java.util.*;

/**
 * Class that helps to solve knapsack problem
 * @param <I> type of items
 * @param <K> type of knapsack
 *
 * @author alexgolubev
 */
public class UnboundedKnapsackProblemSolver<I extends ItemIf, K extends KnapsackIf> {

    // Here the results that are already calculated once are saved. So we can reuse them.
    // Index represents the size of a knapsack result is calculated for.
    private K[] mResults;
    // Map of smaller knapsacks together with respective items. Used for calculating
    // the best possible combination before actually creating a best value knapsack.
    // It is set of tuples in fact.
    private Map<K, I> mSmallerKnapsacks = new HashMap<>();
    // Special case
    private KnapsackFactoryIf<K, I> mKnapsackFactory;
    // All available item types
    private I[] mItems;
    // No progress from the beginning
    private int mProgress = 0;
    boolean mIsSolving = false;
    private int mMaxItemWeight;
    private int mAdjustedKnapsackSize;
    private int mWeightGcd;

    /**
     * Solves unbounded knapsack problem for a given knapsack size and available items
     * @param pItems array of available item types
     * @param pKnapsackSize array of knapsacks used for caching results
     * @param pKnapsackFactory factory to produce knapsacks
     * @return a knapsack full of items with maximum total value
     */
    public K solve(I[] pItems, int pKnapsackSize, KnapsackFactoryIf<K, I> pKnapsackFactory) {
        initialize(pItems, pKnapsackSize, pKnapsackFactory);
        mIsSolving = true;

        for (int i = 0; i <= mAdjustedKnapsackSize; i++) {
            synchronized (this) {
                mProgress = i; // to keep track of the progress
            }
            calculateMaxValueKnapsack(i);
        }
        mIsSolving = false;
        return mResults[mAdjustedKnapsackSize];
    }

    private void initialize(I[] pItems, int pKnapsackSize, KnapsackFactoryIf<K, I> pKnapsackFactory) {
        mKnapsackFactory = pKnapsackFactory;
        mItems = pItems;
        calculateGcdOfWeights(pItems);
        mAdjustedKnapsackSize = pKnapsackSize / mWeightGcd;
        mResults = mKnapsackFactory.newKnapsackArray(mAdjustedKnapsackSize + 1);
        mMaxItemWeight = calculateMaxItemWeight();
    }

    private void calculateGcdOfWeights(I[] pItems) {
        int[] tWeights = new int[pItems.length];
        for (int i = 0; i < tWeights.length; i++) {
            tWeights[i] = pItems[i].getWeight();
        }
        mWeightGcd = GcdCalculator.calculateGcd(tWeights);
    }

    private int calculateMaxItemWeight() {
        int tMax = 0;
        for (I tItem : mItems) {
            int tWeight = getAdjustedWeight(tItem);
            if (tMax < tWeight &&
                    // we do not care about items bigger than knapsack anyway.
                    // But they can prevent the algorithm from cleaning unnecessary results.
                    tWeight <= mAdjustedKnapsackSize) {
                tMax = tWeight;
            }
        }
        return tMax;
    }

    private int getAdjustedWeight(I pItem) {
        return pItem.getWeight() / mWeightGcd;
    }

    public double getProgress() {
        if (mIsSolving) {
            synchronized (this) {
                return (double) mProgress / mAdjustedKnapsackSize * 100;
            }
        }
        return 0.0;
    }

    private synchronized void calculateMaxValueKnapsack(int pMaxWeight) {
        // Creating a list for each of the variants when we take an item and get the best
        // weight distribution for a remainder of the knapsack. It is already calculated.
        for (I tItem : mItems) {
            int tSmallerKnapsackMaxWeight = pMaxWeight - getAdjustedWeight(tItem);
            K tSmallerKnapsack;
            if (tSmallerKnapsackMaxWeight >= 0) {
                tSmallerKnapsack = mResults[tSmallerKnapsackMaxWeight];
                mSmallerKnapsacks.put(tSmallerKnapsack, tItem);
            }
        }

        K tMaxValueKnapsack = getMaxValueKnapsack();
        // Caching the results. This is the essence of dynamic programming principle.
        mResults[pMaxWeight] = tMaxValueKnapsack;
        // Cleaning the results that are not needed anymore to save some space
        int tResultToClean = pMaxWeight - mMaxItemWeight;
        if (tResultToClean >= 0) {
            mResults[tResultToClean] = null;
        }
        // Do not forget to clear the map
        mSmallerKnapsacks.clear();
    }

    private K getMaxValueKnapsack() {
        if (mSmallerKnapsacks == null || mSmallerKnapsacks.size() == 0) {
            return mKnapsackFactory.newEmptyKnapsack();
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
        return mKnapsackFactory.newKnapsackWithItem(tMaxCombinationKnapsack, tMaxCombinationItem);
    }
}
