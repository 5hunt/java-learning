package com.shunt.learning.phase0.week2.automation;

/**
 * Task 4: Check for duplicates in array using nested loops.
 * Automation goal: Internalize O(n²) pattern without collections.
 */
public class Task4HasDuplicate {

    public static boolean hasDuplicate(int[] arr) {
        if (arr == null || arr.length < 2) {
            return false;
        }

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasDuplicate(new int[]{1, 2, 3}));    // false
        System.out.println(hasDuplicate(new int[]{1, 2, 1}));    // true
        System.out.println(hasDuplicate(new int[]{5}));          // false
        System.out.println(hasDuplicate(new int[]{}));           // false
        System.out.println(hasDuplicate(null));              // false
    }
}