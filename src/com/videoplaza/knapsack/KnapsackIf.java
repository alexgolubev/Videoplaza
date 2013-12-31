package com.videoplaza.knapsack;

/**
 * Interface to represent a knapsack with items in it
 * @author alexgolubev
 * @param <K> type of knapsack
 * @param <I> type of items
 */
public interface KnapsackIf<K extends KnapsackIf, I extends ItemIf> {

    /**
     * Puts an item into the knapsack.
     * @param pItem an item to put in knapsack
     * @return new copy of a knapsack with a new item in it
     */
    public K put(I pItem);

    /**
     * @return total value of items in the knapsack
     */
    public int getTotalValue();
}
