package com.videoplaza.knapsack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alexgolubev
 */
public class Knapsack {
    private Map<Item, Integer> mItemAmounts;
    private int mWeight;

    public Knapsack() {
        mItemAmounts = new HashMap<Item, Integer>();
        mWeight = 0;
    }

    public Knapsack(Knapsack pKnapsack, Item pItem) {
        Map<Item, Integer> tItemAmounts = new HashMap<>(pKnapsack.getItemAmounts());
        if (tItemAmounts.containsKey(pItem)) {
            int tAmount = tItemAmounts.get(pItem);
            tItemAmounts.put(pItem, ++tAmount);
        } else {
            tItemAmounts.put(pItem, 1);
        }
        mItemAmounts = tItemAmounts;
        mWeight = pKnapsack.getWeight() + pItem.getWeight();
    }

    public Map<String, Integer> getContent() {
        Map<String, Integer> tContent = new HashMap<String, Integer>();
        for (Map.Entry<Item, Integer> tItemAmount : mItemAmounts.entrySet()) {
            tContent.put(tItemAmount.getKey().getName(), tItemAmount.getValue());
        }
        return tContent;
    }

    public int getWeight() {
        return mWeight;
    }

    public Map<Item, Integer> getItemAmounts() {
        return mItemAmounts;
    }
}
