package com.videoplaza.knapsack;

import java.util.*;

/**
 * Class that helps to solve knapsack problem
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
        return calculateMaxValueKnapsack(pKnapsackSize);
    }

    private K calculateMaxValueKnapsack(int pMaxWeight) {

        if (mCachedResults.containsKey(pMaxWeight)) {
            return mCachedResults.get(pMaxWeight);
        }

        // Creating a list for each of the variants when we take an item and calculate the best
        // weight distribution for a remainder of the knapsack
        List<K> tSmallerKnapsacks = new ArrayList<>();
        for (I tItem : mItems) {
            int tSmallerKnapsackMaxWeight = pMaxWeight - tItem.getWeight();
            K tSmallerKnapsack;
            if (tSmallerKnapsackMaxWeight > 0) {
                tSmallerKnapsack = calculateMaxValueKnapsack(tSmallerKnapsackMaxWeight);
                // Creating a new knapsack not to mess up smaller ones since they can be used in further calculations
                K tMaxKnapsack = tSmallerKnapsack.put(tItem);
                tSmallerKnapsacks.add(tMaxKnapsack);
            }
        }

        K tResult = getMaxValueKnapsack(tSmallerKnapsacks);
        // Caching the results to speed up the algorithm. This is the essence of dynamic programming principle.
        mCachedResults.put(pMaxWeight, tResult); // Dynamic programming is applied here
        return tResult;
    }

    private K getMaxValueKnapsack(List<K> tKnapsacks) {
        if (tKnapsacks == null || tKnapsacks.size() == 0) {
            return mEmptyKnapsack;
        }

        K tMax = tKnapsacks.get(0);
        for (K tKnapsack : tKnapsacks) {
            if (tKnapsack.getTotalWeight() > tMax.getTotalWeight()) {
                tMax = tKnapsack;
            }
        }
        return tMax;
    }
}