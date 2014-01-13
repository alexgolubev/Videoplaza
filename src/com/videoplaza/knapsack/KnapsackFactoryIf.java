package com.videoplaza.knapsack;

/**
 * Factory to produce knapsacks
 * @param <K> type of knapsack
 * @param <I> type of item
 * @author alexgolubev
 */
public interface KnapsackFactoryIf<K extends KnapsackIf, I extends ItemIf> {

    /**
     * @return new empty knapsack
     */
    public K newEmptyKnapsack();

    /**
     * @param pSize size of newly created array
     * @return new array of knapsacks with the given size
     */
    public K[] newKnapsackArray(int pSize);

    /**
     * @param pOriginalKnapsack original knapsack
     * @param pItem item to add
     * @return new knapsack that has exactly the same content as the original one plus new given item
     */
    public K newKnapsackWithItem(K pOriginalKnapsack, I pItem);
}
