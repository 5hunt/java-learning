package com.shunt.learning.phase0.week2.automation;

/**
 * Task 3: Count words in string (word = sequence of letters).
 * Key insight: Increment counter on word entry (not exit) → no post-check needed.
 */
public class Task3CountWords {

    public static int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        boolean insideWord = false;
        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            if (!insideWord && Character.isLetter(text.charAt(i))) {
                count++;
                insideWord = true;
            }

            if (insideWord && !Character.isLetter(text.charAt(i))) {
                insideWord = false;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countWords("hello world"));  // 2
        System.out.println(countWords("  a  b  c  "));  // 3
        System.out.println(countWords("1one"));         // 1
        System.out.println(countWords("one"));          // 1
        System.out.println(countWords(""));             // 0
        System.out.println(countWords(null));           // 0


    }
}