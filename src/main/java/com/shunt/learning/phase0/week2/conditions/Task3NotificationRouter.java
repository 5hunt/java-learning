package com.shunt.learning.phase0.week2.conditions;

/**
 * Task 3: Notification router using ternary operator and switch expressions.
 * Demonstrates modern Java control flow (Java 14+) for backend routing logic.
 */
public class Task3NotificationRouter {
    /**
     * Determines delivery channel based on event type.
     * Switch expression guarantees exhaustive handling (compiler-checked for enums).
     *
     * @param eventType event type (case-insensitive)
     * @return delivery channel: "email", "sms", or "push"
     * @throws IllegalArgumentException if eventType is null or empty
     */
    public static String getDeliveryChannel(String eventType) {
        validateEventType(eventType);
        return switch (eventType.toLowerCase()) {
            case "order_created", "payment_received" -> "email";
            case "order_shipped", "delivery_delayed" -> "sms";
            case "system_alert", "security_incident" -> "push";
            default -> "email";
        };
    }

    /**
     * Determines notification priority (1=highest, 5=lowest).
     * Ternary operator for critical flag → immediate priority 1.
     * Switch expression for non-critical event types.
     *
     * @param eventType  event type (case-insensitive)
     * @param isCritical whether event requires immediate attention
     * @return priority level 1-5
     * @throws IllegalArgumentException if eventType is null or empty
     */
    public static int getPriority(String eventType, boolean isCritical) {
        validateEventType(eventType);
        return isCritical ? 1
                : switch (eventType.toLowerCase()) {
            case "system_alert" -> 2;
            case "order_shipped", "delivery_delayed" -> 3;
            case "order_created", "payment_received" -> 4;
            default -> 5;
        };
    }

    /**
     * Validates event type once (DRY principle).
     * In production: would include logging and metrics.
     */
    private static void validateEventType(String eventType) {
        if (eventType == null || eventType.isEmpty()) {
            throw new IllegalArgumentException("eventType must not be null or empty");
        }
    }

    public static void main(String[] args) {
        // Channel tests
        System.out.println(getDeliveryChannel("ORDER_CREATED"));        // email
        System.out.println(getDeliveryChannel("order_shipped"));        // sms
        System.out.println(getDeliveryChannel("security_incident"));    // push
        System.out.println(getDeliveryChannel("unknown_event"));        // email (default)

        // Priority tests
        System.out.println(getPriority("order_shipped", true));     // 1 (critical)
        System.out.println(getPriority("system_alert", false));     // 2
        System.out.println(getPriority("DELIVERY_DELAYED", false)); // 3
        System.out.println(getPriority("order_created", false));    // 4
        System.out.println(getPriority("unknown_event", false));    // 5

        // Edge cases (channel)
        try {
            getDeliveryChannel(null);
            System.out.println("FAIL: null not rejected");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: null rejected - " + e.getMessage());
        }
        try {
            getDeliveryChannel("");
            System.out.println("FAIL: empty not rejected");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: empty rejected - " + e.getMessage());
        }

        // Edge cases (priority)
        try {
            getPriority(null, false);
            System.out.println("FAIL: null not rejected");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: null rejected - " + e.getMessage());
        }
        try {
            getPriority("", false);
            System.out.println("FAIL: empty not rejected");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: empty rejected - " + e.getMessage());
        }
    }
}
