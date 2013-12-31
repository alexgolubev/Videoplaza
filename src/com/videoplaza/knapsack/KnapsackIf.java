package com.videoplaza.knapsack;

import java.util.List;

/**
 * Interface to represent a knapsack with items in it
 * @author alexgolubev
 */
public interface KnapsackIf<K extends KnapsackIf, T extends ItemIf> {

    /**
     * Puts an item into the knapsack.
     * @param pItem an item to put in knapsack
     * @return new copy of a knapsack with a new item in it
     */
    public K put(T pItem);

    /**
     * @return total value of items in the knapsack
     */
    public int getTotalValue();
}
