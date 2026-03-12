package com.shunt.learning.phase0.week2.loops;

import java.util.Arrays;

/**
 * Task 1: Find all unique index pairs with given sum.
 * Algorithm: Nested loops with j = i + 1 to guarantee uniqueness (i < j).
 * Complexity: O(n²) time, O(1) extra space (excluding result string).
 */
public class Task1FindPairs {

    /**
     * Finds all unique index pairs (i, j) where i < j and arr[i] + arr[j] == target.
     *
     * Key insight: Starting inner loop at i+1 eliminates duplicate pairs
     * and reduces iterations from n² to n(n-1)/2.
     *
     * @param arr    input array (null-safe)
     * @param target target sum
     * @return formatted string "(i,j)" pairs separated by commas, or empty string
     */
    public static String findPairs(int[] arr, int target) {
        if (arr == null || arr.length < 2) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == target) {
                    if (!result.isEmpty()) {
                        result.append(',');
                    }
                    result.append('(').append(i).append(',').append(j).append(')');
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // Test 1: Two pairs
        int[] a = {1, 2, 3, 4, 5};
        int targetA = 5;
        System.out.println("Test 1 - Array: " + Arrays.toString(a) + "; Target: " +
                targetA + "; Pairs: \"" + findPairs(a, targetA) + "\""); // "(0,3),(1,2)"

        // Test 2: One pair
        int[] b = {3, 3};
        int targetB = 6;
        System.out.println("Test 2 - Array: " + Arrays.toString(b) + "; Target: " +
                targetB + "; Pairs: \"" + findPairs(b, targetB) + "\""); // "(0,1)"

        // Test 3: No pairs
        int[] c = {1, 2, 3};
        int targetC = 10;
        System.out.println("Test 3 - Array: " + Arrays.toString(c) + "; Target: " +
                targetC + "; Pairs: \"" + findPairs(c, targetC) + "\""); // ""

        // Test 4: Empty array
        int[] d = {};
        int targetD = 5;
        System.out.println("Test 4 - Array: " + Arrays.toString(d) + "; Target: " +
                targetD + "; Pairs: \"" + findPairs(d, targetD) + "\""); // ""

        // Test 5: Null array
        int targetE = 5;
        System.out.println("Test 5 - Array: null; Target: " +
                targetE + "; Pairs: \"" + findPairs(null, targetE) + "\""); // ""
    }
}