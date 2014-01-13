package com.videoplaza.knapsack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests on unbounded knapsack problem solution algorithm
 * @author alexgolubev
 */
public class UnboundedKnapsackProblemSolverTest {

    public Item[] mItems;
    public Knapsack mEmptyKnapsack;
    public UnboundedKnapsackProblemSolver<Item, Knapsack> mSolver = new UnboundedKnapsackProblemSolver<>();


    @Before
    public void before() {
        mEmptyKnapsack = new Knapsack();
    }

    @Test
    public void testSolveOneItemTypeFullyPacked() throws Exception {
        mItems = new Item[1];
        Item tItem = new Item(1, 1);
        mItems[0] = tItem;

        Knapsack tSolution = mSolver.solve(mItems, 3, new KnapsackFactory());
        Assert.assertEquals(3, tSolution.getTotalValue());
    }

    @Test
    public void testSolveOneItemTypePartiallyPacked() throws Exception {
        mItems = new Item[1];
        Item tItem = new Item(2, 1);
        mItems[0] = tItem;

        Knapsack tSolution = mSolver.solve(mItems, 5, new KnapsackFactory());
        Assert.assertEquals(2, tSolution.getTotalValue());
    }

    @Test
    public void testSolveManyItemsTypeFullyPacked() throws Exception {
        mItems = new Item[3];
        Item tItem = new Item(1, 10);
        mItems[0] = tItem;
        tItem = new Item(3, 31);
        mItems[1] = tItem;
        tItem = new Item(5, 53);
        mItems[2] = tItem;

        Knapsack tSolution = mSolver.solve(mItems, 9, new KnapsackFactory());
        Assert.assertEquals(94, tSolution.getTotalValue()); // one of each type
    }

    @Test
    public void testSolveManyItemsTypePartiallyPacked() throws Exception {
        mItems = new Item[2];
        Item tItem = new Item(3, 31);
        mItems[0] = tItem;
        tItem = new Item(5, 63);
        mItems[1] = tItem;

        Knapsack tSolution = mSolver.solve(mItems, 9, new KnapsackFactory());
        Assert.assertEquals(94, tSolution.getTotalValue()); // one of each type
    }

    class Knapsack implements KnapsackIf {

        List<Item> mItems = new ArrayList<>();

        @Override
        public int getTotalValue() {
            int tTotalValue = 0;
            for (Item tItem : mItems) {
                tTotalValue += tItem.getValue();
            }
            return tTotalValue;
        }
    }

    class Item implements ItemIf {

        public int mWeight;
        public int mValue;

        public Item(int pWeight, int pValue) {
            mWeight = pWeight;
            mValue = pValue;
        }

        @Override
        public int getWeight() {
            return mWeight;
        }

        @Override
        public int getValue() {
            return mValue;
        }
    }

    class KnapsackFactory implements KnapsackFactoryIf<Knapsack, Item> {

        @Override
        public Knapsack newEmptyKnapsack() {
            return new Knapsack();
        }

        @Override
        public Knapsack[] newKnapsackArray(int pSize) {
            return new Knapsack[pSize];
        }

        @Override
        public Knapsack newKnapsackWithItem(Knapsack pOriginalKnapsack, Item pItem) {
            Knapsack tNewKnapsack = new Knapsack();
            tNewKnapsack.mItems = new ArrayList<>(pOriginalKnapsack.mItems);
            tNewKnapsack.mItems.add(pItem);
            return tNewKnapsack;
        }
    }

}
