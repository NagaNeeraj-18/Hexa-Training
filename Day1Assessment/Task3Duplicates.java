package org.example.Day1Assessment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3Duplicates {

    static String makeKey(Order o) {
        long cents = Math.round(o.amount * 100);
        String minute = o.createdAt.substring(0, 16);
        return o.customerId + "|" + cents + "|" + minute;
    }

    public static void main(String[] args) throws Exception {
        List<Order> orders = OrderLoader.load("C:/Users/admin/Downloads/orders.csv");

        Map<String, Order> seen = new HashMap<>();
        int duplicateCount = 0;

        for (Order o : orders) {
            String key = makeKey(o);

            if (seen.containsKey(key)) {
                duplicateCount++;
                System.out.println("DUPLICATE: " + o);
                System.out.println("  matches: " + seen.get(key));
            } else {
                seen.put(key, o);
            }
        }

        System.out.println("\nOrders checked      : " + orders.size());
        System.out.println("Duplicates detected : " + duplicateCount);
        System.out.println("Time complexity     : O(n) average");
        System.out.println("Space complexity    : O(n)");
    }
}