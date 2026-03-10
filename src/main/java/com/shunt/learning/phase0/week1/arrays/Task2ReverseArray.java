package com.shunt.learning.phase0.week1.arrays;

import java.util.Arrays;

/**
 * Task 2: Reverse array in-place using two-pointer technique.
 */
public class Task2ReverseArray {

    /**
     * Reverses the given array in-place.
     *
     * @param arr array to reverse
     * @throws IllegalArgumentException if arr is null
     */
    public static void reverse(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array must not be null");
        }

        // Two pointers: swap elements from ends toward center
        int left = 0;
        int right = arr.length - 1;

        // Swap elements at left and right positions
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        // Test 1: Normal array
        int[] data1 = {1, 2, 3, 4, 5, 6, 7};
        System.out.print("Test 1: Source array: " + Arrays.toString(data1));
        reverse(data1);
        System.out.println("; Reverse array: " + Arrays.toString(data1)); // Expected {7, 6, 5, 4, 3, 2, 1}

        // Test 2: Single element
        int[] data2 = {10};
        System.out.print("Test 2: Source array: " + Arrays.toString(data2));
        reverse(data2);
        System.out.println("; Reverse array: " + Arrays.toString(data2)); // Expected {10}

        // Test 3: Empty array
        int[] data3 = {};
        System.out.print("Test 3: Source array: " + Arrays.toString(data3));
        reverse(data3);
        System.out.println("; Reverse array: " + Arrays.toString(data3)); // Expected {}

        // Test 4: Null array (edge case)
        System.out.print("Test 4: Source array: Null;");
        try {
            reverse(null);
            System.err.println(" FAILED: No exception thrown");
        } catch (IllegalArgumentException e) {
            System.out.println(" PASSED: " + e.getMessage());
        }
    }
}
