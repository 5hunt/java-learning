package com.shunt.learning.phase0.week2.automation;

import java.util.Arrays;

/**
 * Task 8: Merge two sorted arrays into one sorted array.
 * Key insight: Always return a NEW array (never return input array directly).
 * Real-world note: This is the merge step of Merge Sort algorithm.
 */
public class Task8MergeSorted {

    public static int[] mergeSorted(int[] arr1, int[] arr2) {
        // Normalize nulls to empty arrays
        if (arr1 == null) {
            arr1 = new int[0];
        }
        if (arr2 == null) {
            arr2 = new int[0];
        }

        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        // Merge while both arrays have elements
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        // Append remaining elements from arr1 (if any)
        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }

        // Append remaining elements from arr2 (if any)
        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }
        return result;  // Always a new array
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeSorted(
                new int[]{1, 3, 5}, new int[]{2, 4, 6}
        )));    // [1, 2, 3, 4, 5, 6]
        System.out.println(Arrays.toString(mergeSorted(
                new int[]{1, 2}, new int[]{3, 4}
        )));    // [1, 2, 3, 4]
        System.out.println(Arrays.toString(mergeSorted(
                new int[]{}, new int[]{1, 2}
        )));    // [1, 2]
        System.out.println(Arrays.toString(mergeSorted(
                new int[]{5}, new int[]{}
        )));    // [5]
        System.out.println(Arrays.toString(mergeSorted(
                null, new int[]{1}
        )));    // [1]
        System.out.println(Arrays.toString(mergeSorted(
                null, new int[]{}
        )));    // []
        System.out.println(Arrays.toString(mergeSorted(
                null, null
        )));    // []
        System.out.println(Arrays.toString(mergeSorted(
                new int[]{}, new int[]{}
        )));    // []

        //Critical test: mutation safety
        int[] original = {1, 2, 3, 4, 5};
        int[] merged = mergeSorted(original, new int[]{});
        merged[0] = 999;
        System.out.println("Original unchanged: "
                + Arrays.toString(original));   // [1, 2, 3, 4, 5]
    }
}