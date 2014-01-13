package com.videoplaza.knapsack;

/**
 * Class to calculate greatest common divisor using Euclidean algorithm
 * @author alexgolubev
 */
public class GcdCalculator {

    public static int calculateGcd(int[] pInts) {
        if (pInts == null || pInts.length == 0) {
            throw new IllegalArgumentException("No numbers to calculate GCD were provided!");
        }
        if (pInts.length == 1) {
            return pInts[0];
        }
        int tA = pInts[0];
        int tB;
        for (int i = 1; i< pInts.length; i++) {
            tB = pInts[i];
            tA = calculateGcd(tA, tB);
        }
        return tA;
    }

    public static int calculateGcd(int pA, int pB) {
        return pB==0 ? pA : calculateGcd(pB, pA % pB);
    }
}
