package com.videoplaza.knapsack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that helps to solve knapsack problem
 *
 * @author alexgolubev
 */
public class KnapsackProblemSolver {

    public Map<String, Integer> solve(int pSize, List<Item> pItems) {
        Map<String, Integer> tMap = new HashMap<String, Integer>();
        for (Item tItem : pItems) {
            tMap.put(tItem.getName(), 1);
        }
        return tMap;
    }
}
