package com.videoplaza.knapsack;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author alexgolubev
 */
public class GcdCalculatorTest {

    @Test
    public void testCalculateGcd1() throws Exception {
        Assert.assertEquals(3, GcdCalculator.calculateGcd(new int[] {3, 6, 9}));
    }

    @Test
    public void testCalculateGcd2() throws Exception {
        Assert.assertEquals(1, GcdCalculator.calculateGcd(new int[] {1, 3, 6, 9}));
    }

    @Test
    public void testCalculateGcd3() throws Exception {
        Assert.assertEquals(1, GcdCalculator.calculateGcd(new int[] {3, 6, 7}));
    }

    @Test
    public void testCalculateGcdOfTwo1() throws Exception {
        Assert.assertEquals(3, GcdCalculator.calculateGcd(3, 6));
    }

    @Test
    public void testCalculateGcdOfTwoPrimeNumbers() throws Exception {
        Assert.assertEquals(1, GcdCalculator.calculateGcd(23, 37));
    }

    @Test
    public void testCalculateGcdOfTwo2() throws Exception {
        Assert.assertEquals(20, GcdCalculator.calculateGcd(100, 40));
    }
}
