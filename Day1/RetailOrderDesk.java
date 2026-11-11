package org.example;

import java.util.*;

class Order {
    int orderId;
    String customerName;
    String productName;
    int quantity;
    double price;

    Order(int orderId, String customerName, String productName, int quantity, double price) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Customer: %-15s | Product: %-15s | Qty: %-3d | Price: $%.2f",
                orderId, customerName, productName, quantity, price);
    }
}

public class RetailOrderDesk {

    static ArrayList<Order> orders = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== RETAIL ORDER DESK =====");
            System.out.println("1. Add Order");
            System.out.println("2. Display Orders");
            System.out.println("3. Remove Order");
            System.out.println("4. Update Order");
            System.out.println("5. Sort by Order ID");
            System.out.println("6. Sort by Customer Name");
            System.out.println("7. Sort by Product Name");
            System.out.println("8. Sort by Price");
            System.out.println("9. Binary Search (by Order ID)");
            System.out.println("10. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear scanner buffer

            switch (choice) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    displayOrders();
                    break;
                case 3:
                    removeOrder();
                    break;
                case 4:
                    updateOrder();
                    break;
                case 5:
                    sortById();
                    break;
                case 6:
                    sortByCustomer();
                    break;
                case 7:
                    sortByProduct();
                    break;
                case 8:
                    sortByPrice();
                    break;
                case 9:
                    binarySearch();
                    break;
                case 10:
                    System.out.println("Thank You!");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 10);
    }

    static void addOrder() {
        System.out.print("Enter Order ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        // Check if ID already exists
        for (Order o : orders) {
            if (o.orderId == id) {
                System.out.println("Error: Order ID already exists!");
                return;
            }
        }

        System.out.print("Enter Customer Name: ");
        String customer = sc.nextLine();

        System.out.print("Enter Product Name: ");
        String product = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        orders.add(new Order(id, customer, product, qty, price));
        System.out.println("Order added successfully!");
    }

    static void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }
        System.out.println("\n--- Current Orders ---");
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    static void removeOrder() {
        System.out.print("Enter Order ID to remove: ");
        int id = sc.nextInt();

        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order o = iterator.next();
            if (o.orderId == id) {
                iterator.remove();
                System.out.println("Order ID " + id + " removed successfully.");
                return;
            }
        }
        System.out.println("Order ID not found!");
    }

    static void updateOrder() {
        System.out.print("Enter Order ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        for (Order o : orders) {
            if (o.orderId == id) {
                System.out.print("Enter New Customer Name: ");
                o.customerName = sc.nextLine();

                System.out.print("Enter New Product Name: ");
                o.productName = sc.nextLine();

                System.out.print("Enter New Quantity: ");
                o.quantity = sc.nextInt();

                System.out.print("Enter New Price: ");
                o.price = sc.nextDouble();

                System.out.println("Order updated successfully!");
                return;
            }
        }
        System.out.println("Order ID not found!");
    }

    // Sort by Order ID
    static void sortById() {
        orders.sort(Comparator.comparingInt(o -> o.orderId));
        System.out.println("Orders sorted by Order ID.");
    }
    static void sortByCustomer() {
        orders.sort(Comparator.comparing(o -> o.customerName, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Orders sorted by Customer Name.");
    }
    static void sortByProduct() {
        orders.sort(Comparator.comparing(o -> o.productName, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Orders sorted by Product Name.");
    }
    static void sortByPrice() {
        orders.sort(Comparator.comparingDouble(o -> o.price));
        System.out.println("Orders sorted by Price.");
    }
    static void binarySearch() {
        if (orders.isEmpty()) {
            System.out.println("No orders available to search.");
            return;
        }
        sortById();

        System.out.print("Enter Order ID to search: ");
        int targetId = sc.nextInt();

        int low = 0;
        int high = orders.size() - 1;
        boolean found = false;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Order midOrder = orders.get(mid);

            if (midOrder.orderId == targetId) {
                System.out.println("\nOrder Found:\n" + midOrder);
                found = true;
                break;
            } else if (midOrder.orderId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (!found) {
            System.out.println("Order with ID " + targetId + " not found.");
        }
    }
}