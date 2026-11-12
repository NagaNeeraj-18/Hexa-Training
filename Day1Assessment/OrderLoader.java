package org.example.Day1Assessment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderLoader {

    public static List<Order> load(String path) throws IOException {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // skip the header row
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                long orderId = Long.parseLong(parts[0].trim());
                String customerId = parts[1].trim();
                double amount = Double.parseDouble(parts[2].trim());
                String timestamp = parts[3].trim();
                orders.add(new Order(orderId, customerId, amount, timestamp));
            }
        }
        return orders;
    }
}