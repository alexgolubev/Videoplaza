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
    private Map<Integer, K> mCachedResults = new HashMap<>();
    // Special case
    private K mEmptyKnapsack;
    // All available item types
    private Set<I> mItems;

    /**
     * Solves unbounded knapsack problem for a given knapsack size and available items
     * @param pItems set of available item types
     * @param pKnapsackSize size of the knapsack
     * @param pEmptyKnapsack empty knapsack to fill in
     * @return a knapsack full of items with maximum total value
     */
    public K solve(Set<I> pItems, int pKnapsackSize, K pEmptyKnapsack) {
        mEmptyKnapsack = pEmptyKnapsack;
        mItems = pItems;
        for (int i = 0; i < pKnapsackSize + 1; i++) {
            calculateMaxValueKnapsack(i);
            if (i % 1000 == 0) {
                System.out.println(i);
            }
        }
        return mCachedResults.get(pKnapsackSize);
    }

    private void calculateMaxValueKnapsack(int pMaxWeight) {
        // Creating a list for each of the variants when we take an item and calculate the best
        // weight distribution for a remainder of the knapsack
        Map<K, I> tSmallerKnapsacks = new HashMap<>();
        for (I tItem : mItems) {
            int tSmallerKnapsackMaxWeight = pMaxWeight - tItem.getWeight();
            K tSmallerKnapsack;
            if (tSmallerKnapsackMaxWeight >= 0) {
                tSmallerKnapsack = mCachedResults.get(tSmallerKnapsackMaxWeight);
                tSmallerKnapsacks.put(tSmallerKnapsack, tItem);
            }
        }

        K tMaxValueKnapsack = getMaxValueKnapsack(tSmallerKnapsacks);
        // Caching the results to speed up the algorithm. This is the essence of dynamic programming principle.
        mCachedResults.put(pMaxWeight, tMaxValueKnapsack); // Dynamic programming is applied here
    }

    private K getMaxValueKnapsack(Map<K, I> tKnapsacks) {
        if (tKnapsacks == null || tKnapsacks.size() == 0) {
            return mEmptyKnapsack;
        }

        Set<Map.Entry<K,I>> tKnapsackSet = tKnapsacks.entrySet();
        Map.Entry<K, I> tMax = null;
        int tMaxValue = -1;
        for (Map.Entry<K, I> tKnapsack : tKnapsackSet) {
            int tKnapsackValue = tKnapsack.getKey().getTotalValue() + tKnapsack.getValue().getValue();
            if (tKnapsackValue > tMaxValue) {
                tMax = tKnapsack;
                tMaxValue = tKnapsackValue;
            }
        }
        return tMax.getKey().put(tMax.getValue());
    }
}
