package com.shunt.learning.phase0.week2.automation;

/**
 * Task 1: Find index of first vowel (a, e, i, o, u in any case).
 * Automation goal: Solve in <10 minutes with minimal cognitive load.
 */
public class Task1FindFirstVowel {

    public static int findFirstVowel(String text) {
        if (text == null || text.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findFirstVowel("hello"));    // 1 ('e')
        System.out.println(findFirstVowel("xyz"));      // -1
        System.out.println(findFirstVowel("Apple"));    // 0 ('A')
    }
}