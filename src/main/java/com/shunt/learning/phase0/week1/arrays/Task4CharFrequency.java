package com.shunt.learning.phase0.week1.arrays;

/**
 * Task 4: Count character frequency in string (English letters only).
 * Approach: Fixed-size array for 26 letters (a-z) instead of Map.
 * Why array? For fixed small domains (alphabet), array is faster and simpler than HashMap.
 */
public class Task4CharFrequency {

    /**
     * Counts frequency of English letters (a-z) in the input string.
     * Ignores case, non-letter characters, and non-English letters.
     * <p>
     * Algorithm insight:
     * - Use array[26] as direct-address table: index = char - 'a'
     * - Direct addressing gives O(1) access without hash collisions
     * - Array guarantees alphabetical order in output (indices 0..25)
     *
     * @param text input string (null-safe)
     * @return formatted string "letter:count" pairs separated by commas
     */
    public static String charFrequency(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        // Fixed-size counter array for 26 English letters
        int[] counters = new int[26];
        String lowerText = text.toLowerCase();

        // Count only English letters (a-z)
        for (int i = 0; i < lowerText.length(); i++) {
            char c = lowerText.charAt(i);
            if (c >= 'a' && c <= 'z') { // Strictly English letters
                counters[c - 'a']++;
            }
        }

        // Build result with comma separation
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < counters.length; j++) {
            if (counters[j] != 0) {
                if (!result.isEmpty()) {
                    result.append(',');
                }
                result.append((char) (j + 'a')).append(':').append(counters[j]);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // Test 1: Mixed case word
        String a = "Hello";
        System.out.println("Test 1 - String: " + "\"" + a + "\"" +
                ", Letter/Count: \"" + charFrequency(a) + "\"");    // e:1,h:1,l:2,o:1

        // Test 2: Repeated letters
        String b = "Java";
        System.out.println("Test 2 - String: " + "\"" + b + "\"" +
                ", Letter/Count: \"" + charFrequency(b) + "\"");    // a:2,j:1,v:1

        // Test 3: Empty string (edge case)
        String c = "";
        System.out.println("Test 3 - String: " + "\"" + c + "\"" +
                ", Letter/Count: \"" + charFrequency(c) + "\"");    // empty string

        // Test 4: Null string (edge case)
        String d = null;
        System.out.println("Test 4 - String: " + "\"" + d + "\"" +
                ", Letter/Count: \"" + charFrequency(d) + "\"");    // empty string

        // Test 5: Non-letter characters
        String e = "123!@#";
        System.out.println("Test 5 - String: " + "\"" + e + "\"" +
                ", Letter/Count: \"" + charFrequency(e) + "\"");    // empty string

        // Test 6: Non-English letters (should be ignored)
        String f = "ПриветМир - HelloWorld";
        System.out.println("Test 6 - String: " + "\"" + f + "\"" +
                ", Letter/Count: \"" + charFrequency(f) + "\"");    // d:1,e:1,h:1,l:3,o:2,r:1,w:1
    }
}
