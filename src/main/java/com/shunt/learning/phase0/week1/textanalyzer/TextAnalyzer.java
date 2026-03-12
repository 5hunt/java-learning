package com.shunt.learning.phase0.week1.textanalyzer;

import java.util.Scanner;

/**
 * TextAnalyzer: Console application that analyzes text statistics.
 * Combines all Week 1 concepts: arrays, strings, loops, edge cases.
 *
 * Architecture insight:
 * - Each method has single responsibility (SRP)
 * - No mutable state in static methods (pure functions)
 * - Null-safe and empty-safe by design
 * - O(n) time complexity for all operations (single pass where possible)
 */
public class TextAnalyzer {

    /**
     * Counts words separated by non-alphanumeric characters.
     * Algorithm: State machine with 'insideWord' flag tracks transitions.
     *
     * Why not split()?
     * - split() creates temporary array (O(n) extra memory)
     * - State machine uses O(1) memory and single pass
     * - Demonstrates fundamental parsing technique used in compilers/tokenizers
     *
     * @param text input string (null-safe)
     * @return number of words (0 for null/empty/whitespace-only)
     */
    public static int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        int count = 0;
        boolean insideWord = false;

        for (int i = 0; i < text.length(); i++) {
            boolean isChar = Character.isLetterOrDigit(text.charAt(i));
            if (isChar && !insideWord) {
                // Transition: outside word → inside word = new word started
                count++;
                insideWord = true;
            } else if (!isChar && insideWord) {
                // Transition: inside word → outside word = word ended
                insideWord = false;
            }
            // Other transitions (outside→outside, inside→inside) require no action
        }
        return count;
    }

    /**
     * Counts frequency of English letters (a-z) in input string.
     * Algorithm: Direct-address table (array[26]) for O(1) access.
     *
     * Why array instead of Map?
     * - Fixed domain (26 letters) → array is faster and simpler
     * - Guarantees alphabetical order in output without sorting
     * - Used in real systems: log analyzers, character frequency counters
     *
     * @param text input string (null-safe)
     * @return formatted "letter:count" pairs separated by commas
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

    /**
     * Checks if string is palindrome (case-insensitive, ignores non-alphanumeric).
     * Algorithm: Two pointers moving toward center with in-place filtering.
     *
     * Why two pointers?
     * - O(1) extra memory (vs O(n) for reversed copy)
     * - Early termination on first mismatch
     * - Used in real systems: token validation, symmetric ID checks
     *
     * @param text input string (null-safe)
     * @return true if palindrome after filtering, false otherwise
     */
    public static boolean isPalindrome(String text) {
        if (text == null || text.isEmpty()) {
            return true;
        }

        String lowerText = text.toLowerCase();

        // Search boundaries: inclusive range [left, right]
        int left = 0;
        int right = lowerText.length() - 1;

        // Continue while search space is non-empty
        while (left < right) {
            // Skip non-alphanumeric chars from left
            while (left < right && !Character.isLetterOrDigit(lowerText.charAt(left))) {
                left++;
            }
            // Skip non-alphanumeric chars from right
            while (left < right && !Character.isLetterOrDigit(lowerText.charAt(right))) {
                right--;
            }

            // Compare valid chars
            if (lowerText.charAt(left) != lowerText.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;

    }

    /**
     * Finds longest word (sequence of alphanumeric characters).
     * Algorithm: State machine tracks word boundaries via 'insideWord' flag.
     *
     * Edge cases handled:
     * - Empty string → ""
     * - Whitespace-only → ""
     * - Multiple words with same max length → returns first occurrence
     *
     * @param text input string (null-safe)
     * @return longest word or empty string if none found
     */
    public static String longestWord(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        int maxLength = 0;
        int start = 0;
        boolean insideWord = false;
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            boolean isChar = Character.isLetterOrDigit(text.charAt(i));
            if (isChar && !insideWord) {
                // Word started
                start = i;
                insideWord = true;
            } else if (!isChar && insideWord) {
                // Word ended
                int currentLength = i - start;
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    result = text.substring(start, i);
                }
                insideWord = false;
            }
        }

        // Handle word at end of string (no trailing delimiter)
        if (insideWord) {
            int currentLength = text.length() - start;
            if (currentLength > maxLength) {
                result = text.substring(start);
            }
        }
        return result;
    }

    /**
     * Main entry point: interactive console loop.
     * UX principle: Never block user with "invalid input" — show meaningful stats for any input.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Text Analyzer (Week 1 Mini-Project) ===");
            System.out.println("Enter text or 'exit' to quit:\n");

            while (true) {
                System.out.print("> ");
                //System.out.print("Enter the text (or \"exit\" to complete): ");
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("\nGoodbye!");
                    break;
                }

                // Show statistics for ANY input (including empty)
                System.out.println("\nStatistics:");
                System.out.println("  Length: " + input.length() + " characters");
                System.out.println("  Words: " + countWords(input));
                System.out.println("  Letter frequency: " +
                        (charFrequency(input).isEmpty() ? "(none)" : charFrequency(input)));
                System.out.println("  Palindrome: " + (isPalindrome(input) ? "yes" : "no"));
                System.out.println("  Longest word: \"" + longestWord(input) + "\"");
                System.out.println();
            }
        }
    }
}