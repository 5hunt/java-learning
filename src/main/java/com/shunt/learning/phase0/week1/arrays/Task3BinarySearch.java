package com.shunt.learning.phase0.week1.arrays;

import java.util.Arrays;

/**
 * Task 3: Binary search in a sorted array.
 * Time complexity: O(log n)
 * Space complexity: O(1)
 */
public class Task3BinarySearch {

    /**
     * Performs binary search on a sorted ascending array.
     * <p>
     * Algorithm insight:
     * - Maintains search boundaries [left, right] that always contain the target (if present)
     * - Each iteration halves the search space by comparing middle element with target
     * - Stops when boundaries cross (left > right) meaning target not found
     * <p>
     * Why mid = left + (right - left) / 2?
     * Prevents integer overflow that could occur with (left + right) / 2
     * when left and right are large positive integers.
     *
     * @param arr    sorted array to search in (ascending order)
     * @param target value to find
     * @return index of target if found, -1 otherwise
     * @throws IllegalArgumentException if arr is null
     */
    public static int binarySearch(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array must not be null");
        }

        // Search boundaries: inclusive range [left, right]
        int left = 0;
        int right = arr.length - 1;

        // Continue while search space is non-empty
        while (left <= right) {
            // Calculate middle index safely (avoid overflow)
            int mid = left + (right - left) / 2;

            // Found target - return its index immediately
            if (arr[mid] == target) {
                return mid;
            }

            // Target is in right half - move left boundary past mid
            if (arr[mid] < target) {
                left = mid + 1;
            }

            // Target is in left half - move right boundary before mid
            else {
                right = mid - 1;
            }
        }
        // Search space exhausted without finding target
        return -1;
    }

    public static void main(String[] args) {
        // Test 1: Element exists in middle
        int[] a = {1, 3, 5, 7, 9, 11, 13};
        int targetA = 7;
        System.out.println("Test 1 - Array: " + Arrays.toString(a) +
                ", Target: " + targetA +
                ", Index: " + binarySearch(a, targetA)); // 3

        // Test 2: Element does not exist
        int[] b = {1, 3, 5, 7, 9, 11, 13};
        int targetB = 8;
        System.out.println("Test 2 - Array: " + Arrays.toString(b) +
                ", Target: " + targetB +
                ", Index: " + binarySearch(b, targetB)); // -1

        // Test 3: Single element matches
        int[] c = {5};
        int targetC = 5;
        System.out.println("Test 3 - Array: " + Arrays.toString(c) +
                ", Target: " + targetC +
                ", Index: " + binarySearch(c, targetC)); // 0

        // Test 4: Empty array
        int[] d = {};
        int targetD = 5;
        System.out.println("Test 4 - Array: " + Arrays.toString(d) +
                ", Target: " + targetD +
                ", Index: " + binarySearch(d, targetD)); // -1

        // Test 5: Null array (exception case)
        System.out.print("Test 5 - Array: null, Target: 5");
        try {
            binarySearch(null, 5);
            System.err.println(", FAILED: No exception thrown");
        } catch (IllegalArgumentException e) {
            System.out.println(", PASSED: " + e.getMessage());
        }
    }
}
