package com.shunt.learning.phase0.week2.loops;

/**
 * Task 2: Check if array contains triplet with given sum.
 * Algorithm: Triple nested loops with i < j < k constraint and early exit.
 * Complexity: O(n³) worst case, but early exit improves average case.
 * Constraint: No collections allowed (pure array operations).
 */
public class Task2HasTriplet {

    /**
     * Checks if three distinct elements exist with sum equal to target.
     * Early exit: returns true immediately upon finding first valid triplet.
     *
     * Loop invariant: i < j < k guaranteed by loop initialization:
     * - j starts at i+1
     * - k starts at j+1
     *
     * @param arr    input array (null-safe)
     * @param target target sum
     * @return true if triplet exists, false otherwise
     */
    public static boolean hasTriplet(int[] arr, int target) {
        if (arr == null || arr.length < 3) {
            return false;
        }

        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] + arr[k] == target) {
                        return true;    // Early exit on first match
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasTriplet(new int[]{1, 2, 3, 4, 5}, 9));    // true
        System.out.println(hasTriplet(new int[]{1, 2, 3}, 6));          // true
        System.out.println(hasTriplet(new int[]{1, 2, 3}, 10));         // false
        System.out.println(hasTriplet(new int[]{5}, 15));               // false
        System.out.println(hasTriplet(null, 10));                   // false
    }
}