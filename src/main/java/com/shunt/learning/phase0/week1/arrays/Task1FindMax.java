package com.shunt.learning.phase0.week1.arrays;

import java.util.Arrays;

/**
 * Task 1: Find maximum element in integer array.
 * Requirements:
 * - O(n) time complexity
 * - Handle null and empty array
 * - No sorting or built-in max utilities
 */
public class Task1FindMax {

    /**
     * Finds the maximum value in the given array.
     *
     * @param arr the array to search
     * @return the maximum integer value
     * @throws IllegalArgumentException if arr is null or empty
     */
    public static int findMax(int[] arr) {
        // Guard clause: validate input first
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Input array must not be null or empty");
        }

        // Assume first element is max, then iterate to find bigger
        int max = arr[0];
        for (int current : arr) {
            if (current > max) {
                max = current;  // update max when we find a larger value
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // Test 1: Normal case
        System.out.println("Test 1: " + findMax(new int[]{3, 7, 2, 9, 1}));    // Expected: 9

        // Text 2: Negative numbers
        System.out.println("Test 2: " + findMax(new int[]{-5, -2, -10}));   // Expected -2

        // Test 3: Single element
        System.out.println("Test 3: " + findMax(new int[]{42}));    //Expected 42;

        // Test 4: Empty array -> expect exception
        try {
            findMax(new int[]{});
            System.err.println("Test 4 FAILED: No exception thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 4 PASSED: " + e.getMessage());
        }

        //Test 5: Null array -> expect exception
        try {
            findMax(null);
            System.err.println("Test 5 FAILED: No exception thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 5 PASSED: " + e.getMessage());
        }

        // Bonus: Visual check with Arrays.toString
        int[] data = {15, 3, 28, 9, 42};
        System.out.println("\nBonus: Max in " + Arrays.toString(data) + " = " + findMax(data));

    }
}
