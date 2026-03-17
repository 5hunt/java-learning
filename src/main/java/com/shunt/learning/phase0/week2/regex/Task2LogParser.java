package com.shunt.learning.phase0.week2.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Task 2: Extract transaction IDs from application logs.
 * Demonstrates Pattern/Matcher usage with dynamic array resizing (ArrayList internals).
 */
public class Task2LogParser {

    // Precompiled pattern for performance (compile once, use many times)
    public static final Pattern TRANSACTION_ID_PATTERN = Pattern.compile(
            "tx_id=(TXN-\\d{8}-[A-Z0-9]+)"
    );

    /**
     * Extracts all transaction IDs from log text.
     *
     * Implementation insight:
     * - Uses dynamic array resizing (doubling strategy) to avoid collections
     * - Mirrors ArrayList internal growth mechanism
     * - Returns compact array with no null elements
     *
     * @param logText log content (null-safe)
     * @return array of transaction IDs (empty array if none found)
     */
    public static String[] extractTransactionIds(String logText) {
        if (logText == null || logText.isEmpty()) {
            return new String[0];
        }

        // Initial capacity: 4 (covers most single-log cases)
        String[] buffer = new String[4];
        int count = 0;
        Matcher matcher = TRANSACTION_ID_PATTERN.matcher(logText);

        while (matcher.find()) {
            // Double array size when full (ArrayList growth strategy)
            if (count == buffer.length) {
                buffer = Arrays.copyOf(buffer, buffer.length * 2);
            }
            buffer[count++] = matcher.group(1);
        }

        // Trim to actual size (remove unused slots)
        return Arrays.copyOf(buffer, count);
    }

    public static void main(String[] args) {
        // Single match
        System.out.println(Arrays.toString(extractTransactionIds(
                "[2024-03-17 14:30:45] INFO tx_id=TXN-20240317-ABC123 status=ok"
        )));   // ["TXN-20240317-ABC123"]

        // Multiple matches
        System.out.println(Arrays.toString(extractTransactionIds(
                "tx_id=TXN-20240317-ABC123 tx_id=TXN-20240317-XYZ789"
        )));   // ["TXN-20240317-ABC123", "TXN-20240317-XYZ789"]

        // Mixed alphanumeric suffix
        System.out.println(Arrays.toString(extractTransactionIds(
                "tx_id=TXN-20240317-A1B2C3"
        )));  // ["TXN-20240317-A1B2C3"]

        // Case sensitivity: lowercase suffix rejected
        System.out.println(Arrays.toString(extractTransactionIds(
                "[2024-03-17 14:35:45] INFO tx_id=TXN-20240317-abc657 status=ok"
        )));   // []

        // Edge cases
        System.out.println(Arrays.toString(extractTransactionIds("")));     // []
        System.out.println(Arrays.toString(extractTransactionIds(null)));   // []
    }
}