package com.shunt.learning.phase0.week2.automation;

import java.util.Arrays;

/**
 * Task 2: Copy array range [from, to) without Arrays.copyOf.
 * Automation goal: Internalize index arithmetic and boundary checks.
 */
public class Task2CopyRange {

    public static int[] copyRange(int[] arr, int from, int to) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        if (from < 0 || to > arr.length || from >= to) {
            return new int[0];
        }

        int[] result = new int[to - from];
        int index = 0;

        for (int i = from; i < to; i++) {
            result[index++] = arr[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(copyRange(new int[]{1, 2, 3, 4, 5}, 1, 4))); // [2, 3, 4]
        System.out.println(Arrays.toString(copyRange(new int[]{1, 2, 3}, 0, 1)));       // [1]
        System.out.println(Arrays.toString(copyRange(new int[]{1, 2, 3}, 2, 2)));       // []
        System.out.println(Arrays.toString(copyRange(new int[]{1, 2, 3, 4, 5}, 1, 6))); // []
        System.out.println(Arrays.toString(copyRange(null, 1, 4)));                 // []
        System.out.println(Arrays.toString(copyRange(new int[]{}, 1, 4)));              // []
    }
}