package com.videoplaza.knapsack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that helps to solve knapsack problem
 *
 * @author alexgolubev
 */
public class KnapsackProblemSolver {

    private Map<Integer, Knapsack> mCachedResults = new HashMap<Integer, Knapsack>();
    private final Knapsack mEmptyKnapsack = new Knapsack();
    private List<Item> mItems;
    private int mMinimalWeight;

    public KnapsackProblemSolver(List<Item> pItems) {
        mItems = pItems;
        mMinimalWeight = calculateMinimalWeight(pItems);
    }

    private int calculateMinimalWeight(List<Item> pItems) {
        if (pItems == null || pItems.size() == 0) {
            return 0;
        }
        int tMin = pItems.get(0).getWeight();
        for (Item tItem : pItems) {
            int tWeight = tItem.getWeight();
            if (tWeight < tMin) {
                tMin = tWeight;
            }
        }
        return tMin;
    }

    public Map<String, Integer> solve(int pKnapsackSize) {
        Knapsack tMaxValueKnapsack = calculateMaxValueKnapsack(pKnapsackSize);
        return tMaxValueKnapsack.getContent();
    }

    private Knapsack calculateMaxValueKnapsack(int pMaxWeight) {
        if (pMaxWeight < mMinimalWeight) {
            return mEmptyKnapsack;
        }
        if (mCachedResults.containsKey(pMaxWeight)) {
            return mCachedResults.get(pMaxWeight);
        }

        List<Knapsack> tMaxKnapsacks = new ArrayList<Knapsack>();
        for (Item tItem : mItems) {
            Knapsack tSmallerKnapsack = calculateMaxValueKnapsack(pMaxWeight - tItem.getWeight());
            if ((tSmallerKnapsack.getWeight() + tItem.getWeight()) < pMaxWeight) {
                Knapsack tMaxKnapsack = new Knapsack(tSmallerKnapsack, tItem);
                tMaxKnapsacks.add(tMaxKnapsack);
            }
        }

        Knapsack tResult = getMaxKnapsack(tMaxKnapsacks);
        mCachedResults.put(pMaxWeight, tResult); // Dynamic programming is applied here
        return tResult;
    }

    private Knapsack getMaxKnapsack(List<Knapsack> tKnapsacks) {
        if (tKnapsacks == null || tKnapsacks.size() == 0) {
            return mEmptyKnapsack;
        }

        Knapsack tMax = tKnapsacks.get(0);
        for (Knapsack tKnapsack : tKnapsacks) {
            if (tKnapsack.getWeight() > tMax.getWeight()) {
                tMax = tKnapsack;
            }
        }
        return tMax;
    }
}
