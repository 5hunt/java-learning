package com.shunt.learning.phase0.week2.consolidation;

import java.util.Arrays;

/**
 * Task 3: Number extraction using finite state machine.
 * <p>
 * Core insight: State machine requires FRESH state for each pass.
 * Critical bug: Reusing 'insideNumber' between passes without reset → corrupted state.
 */
public class Task3NumberExtractor {

    public static int[] extractNumbers(String text) {
        if (text == null || text.isEmpty()) {
            return new int[0];
        }

        // Pass 1: Count numbers
        int size = countNumbers(text);

        // Pass 2: Extract numbers
        return fillNumbers(text, size);
    }

    private static int countNumbers(String text) {
        boolean insideNumber = false;
        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            if (!insideNumber && Character.isDigit(text.charAt(i))) {
                insideNumber = true;
            }

            if (insideNumber && !Character.isDigit(text.charAt(i))) {
                count++;
                insideNumber = false;
            }
        }

        if (insideNumber) {
            count++;
        }
        return count;
    }

    private static int[] fillNumbers(String text, int size) {
        boolean insideNumber = false;
        int index = 0;
        int start = 0;
        int[] result = new int[size];

        for (int i = 0; i < text.length(); i++) {
            if (!insideNumber && Character.isDigit(text.charAt(i))) {
                start = i;
                insideNumber = true;
            }

            if (insideNumber && !Character.isDigit(text.charAt(i))) {
                result[index++] = Integer.parseInt(text.substring(start, i));
                insideNumber = false;
            }
        }

        if (insideNumber) {
            result[index] = Integer.parseInt(text.substring(start));
        }
        return result;
    }

    public static void main(String[] args) {
        // Test 1: Groups of numbers
        System.out.println("Test 1: \"123, 456, 789\" - "
                + Arrays.toString(extractNumbers("123, 456, 789")));  // [123, 456, 789]
        // Test 2: Numbers and letters
        System.out.println("Test 2: \"abc123def\" - "
                + Arrays.toString(extractNumbers("abc123def")));      // [123]
        // Test 3: Letters Only
        System.out.println("Test 3: \"no digits\" - "
                + Arrays.toString(extractNumbers("no digits")));      // []
        // Test 4: Numbers and letters are mixed
        System.out.println("Test 4: \"1a2b3\" - "
                + Arrays.toString(extractNumbers("1a2b3")));          // [1, 2, 3]
        // Test 5: Only numbers
        System.out.println("Test 5: \"999\" - "
                + Arrays.toString(extractNumbers("a999")));            // [999]
        // Test 6: Letters before numbers
        System.out.println("Test 6: \"abc999\" - "
                + Arrays.toString(extractNumbers("a999")));            // [999]
        // Test 7: Null string
        System.out.println("Test 7: null - "
                + Arrays.toString(extractNumbers(null)));             // []
        // Test 8: Empty string
        System.out.println("Test 8: \"\" - "
                + Arrays.toString(extractNumbers("")));               // []
    }
}