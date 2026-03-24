package com.shunt.learning.phase0.week2.consolidation;

import java.util.Arrays;

/**
 * Task 1: Find all indices of target value without sorting.
 * <p>
 * Key insight: Sorting destroys original indices — never sort when position matters.
 * Real-world analogy: Finding error codes in log files — position = timestamp order.
 */
public class Task1FindAllIndices {

    /**
     * Two-pass approach: count → allocate → fill.
     * Best for: short arrays (<1000 elements), predictable memory usage.
     * Complexity: O(2n) time, O(k) space (k = matches count).
     */
    public static int[] findAllIndicesTwoPass(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        // Pass 1: Count matches to determine exact array size
        int count = 0;
        for (int value : arr) {
            if (value == target) {
                count++;
            }
        }

        // Allocate exact-size array
        int[] result = new int[count];

        // Pass 2: Collect indices
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                result[index++] = i;
            }
        }
        return result;
    }

    /**
     * One-pass with dynamic resizing (ArrayList growth strategy).
     * Best for: long arrays, streaming data, unknown match count.
     * Complexity: O(n) amortized time, O(k) space with temporary overhead.
     */
    public static int[] findAllIndicesOnePass(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        // Initial capacity: 4 (optimal for sparse matches in short arrays)
        int[] buffer = new int[4];
        int size = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                // Double capacity when full (standard ArrayList strategy)
                if (size == buffer.length) {
                    buffer = Arrays.copyOf(buffer, buffer.length * 2);
                }
                buffer[size++] = i;
            }
        }
        // Trim to actual size (remove unused slots)
        return Arrays.copyOf(buffer, size);
    }

    public static void main(String[] args) {
        // Test case 1: Multiple matches (4 occurrences)
        int[] arr1 = {3, 1, 4, 1, 5, 9, 1, 2, 1};
        System.out.println("Array: " + Arrays.toString(arr1));
        System.out.println("Target: 1");
        System.out.println("Two-pass: " + Arrays.toString(findAllIndicesTwoPass(arr1, 1)));  // [1, 3, 6, 8]
        System.out.println("One-pass: " + Arrays.toString(findAllIndicesOnePass(arr1, 1)));  // [1, 3, 6, 8]

        // Test case 2: No matches
        int[] arr2 = {2, 4, 6, 8};
        System.out.println("\nArray: " + Arrays.toString(arr2));
        System.out.println("Target: 1");
        System.out.println("Two-pass: " + Arrays.toString(findAllIndicesTwoPass(arr2, 1)));  // []
        System.out.println("One-pass: " + Arrays.toString(findAllIndicesOnePass(arr2, 1)));  // []

        // Test case 3: All matches
        int[] arr3 = {5, 5, 5};
        System.out.println("\nArray: " + Arrays.toString(arr3));
        System.out.println("Target: 5");
        System.out.println("Two-pass: " + Arrays.toString(findAllIndicesTwoPass(arr3, 5)));  // [0, 1, 2]
        System.out.println("One-pass: " + Arrays.toString(findAllIndicesOnePass(arr3, 5)));  // [0, 1, 2]

        // Empty array (edge cases)
        System.out.println("\nEmpty array:");
        System.out.println("Two-pass: " + Arrays.toString(findAllIndicesTwoPass(new int[]{}, 5)));  // []
        System.out.println("One-pass: " + Arrays.toString(findAllIndicesOnePass(new int[]{}, 5)));  // []

        // Null array (edge cases)
        System.out.println("\nNull array:");
        System.out.println("Two-pass: " + Arrays.toString(findAllIndicesTwoPass(null, 5)));  // []
        System.out.println("One-pass: " + Arrays.toString(findAllIndicesOnePass(null, 5)));  // []
    }
}