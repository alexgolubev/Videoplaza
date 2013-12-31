package com.videoplaza.knapsack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Tests on unbounded knapsack problem solution algorithm
 * @author alexgolubev
 */
public class UnboundedKnapsackProblemSolverTest {

    public Set<Item> mItems;
    public Knapsack mEmptyKnapsack;
    public UnboundedKnapsackProblemSolver<Item, Knapsack> mSolver = new UnboundedKnapsackProblemSolver();


    @Before
    public void before() {
        mItems = new HashSet<>();
        mEmptyKnapsack = new Knapsack();
    }

    @Test
    public void testSolveOneItemTypeFullyPacked() throws Exception {
        Item tItem = new Item(1, 1);
        mItems.add(tItem);

        Knapsack tSolution = mSolver.solve(mItems, 3, mEmptyKnapsack);
        Assert.assertEquals(3, tSolution.getTotalValue());
    }

    @Test
    public void testSolveOneItemTypePartiallyPacked() throws Exception {
        Item tItem = new Item(2, 1);
        mItems.add(tItem);

        Knapsack tSolution = mSolver.solve(mItems, 5, mEmptyKnapsack);
        Assert.assertEquals(2, tSolution.getTotalValue());
    }

    @Test
    public void testSolveManyItemsTypeFullyPacked() throws Exception {
        Item tItem = new Item(1, 10);
        mItems.add(tItem);
        tItem = new Item(3, 31);
        mItems.add(tItem);
        tItem = new Item(5, 53);
        mItems.add(tItem);

        Knapsack tSolution = mSolver.solve(mItems, 9, mEmptyKnapsack);
        Assert.assertEquals(94, tSolution.getTotalValue()); // one of each type
    }

    @Test
    public void testSolveManyItemsTypePartiallyPacked() throws Exception {
        Item tItem = new Item(3, 31);
        mItems.add(tItem);
        tItem = new Item(5, 63);
        mItems.add(tItem);

        Knapsack tSolution = mSolver.solve(mItems, 9, mEmptyKnapsack);
        Assert.assertEquals(94, tSolution.getTotalValue()); // one of each type
    }

    class Knapsack implements KnapsackIf<Knapsack, Item> {

        List<Item> mItems = new ArrayList<>();

        @Override
        public Knapsack put(Item pItem) {
            Knapsack tNewKnapsack = new Knapsack();
            tNewKnapsack.mItems = new ArrayList<>(this.mItems);
            tNewKnapsack.mItems.add(pItem);
            return tNewKnapsack;
        }

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

}
