package org.example.Day1Assessment;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Task1Search {

    static long comparisons = 0;

    // Linear search: O(n) - checks every element sequentially
    static int linearSearch(Order[] arr, long id) {
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].orderId == id) {
                return i;
            }
        }
        return -1;
    }

    // Binary search: O(log n) - requires array sorted by orderId
    static int binarySearch(Order[] arr, long id) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;

            if (arr[mid].orderId == id) {
                return mid;
            } else if (arr[mid].orderId < id) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {

        List<Order> list = OrderLoader.load("C:/Users/admin/Downloads/orders.csv");
        Order[] arr = list.toArray(new Order[0]);
        System.out.println("Records loaded: " + arr.length);

        // Binary search requires sorted data
        Arrays.sort(arr, (a, b) -> Long.compare(a.orderId, b.orderId));

        // Worst case for linear search: the last order
        long target = arr[arr.length - 1].orderId;

        comparisons = 0;
        int posLinear = linearSearch(arr, target);
        System.out.println("Linear search found at index " + posLinear
                + " using " + comparisons + " comparisons");

        comparisons = 0;
        int posBinary = binarySearch(arr, target);
        System.out.println("Binary search found at index " + posBinary
                + " using " + comparisons + " comparisons");

        // Benchmark: 10,000 random lookups
        Random rnd = new Random(42);
        int queries = 10000;
        long[] ids = new long[queries];
        for (int i = 0; i < queries; i++) {
            ids[i] = arr[rnd.nextInt(arr.length)].orderId;
        }

        // JVM JIT warm-up
        for (int w = 0; w < 3; w++) {
            for (long id : ids) {
                linearSearch(arr, id);
                binarySearch(arr, id);
            }
        }

        comparisons = 0;
        long start = System.nanoTime();
        for (long id : ids) {
            linearSearch(arr, id);
        }
        long linearTime = System.nanoTime() - start;
        long linearComparisons = comparisons;

        comparisons = 0;
        start = System.nanoTime();
        for (long id : ids) {
            binarySearch(arr, id);
        }
        long binaryTime = System.nanoTime() - start;
        long binaryComparisons = comparisons;

        System.out.println("\nBenchmark: " + queries + " lookups on " + arr.length + " records");
        System.out.println("Linear: " + linearTime / 1_000_000.0 + " ms, average comparisons = "
                + (linearComparisons / (double) queries));
        System.out.println("Binary: " + binaryTime / 1_000_000.0 + " ms, average comparisons = "
                + (binaryComparisons / (double) queries));
        System.out.println("Binary search is about " + (linearTime / Math.max(1, binaryTime)) + "x faster");
    }
}
