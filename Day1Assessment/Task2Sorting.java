package org.example.Day1Assessment;

import java.util.Arrays;
import java.util.List;

public class Task2Sorting {

    // O(n^2) : Bubble sort, descending by amount
    static void bubbleSort(Order[] arr) {
        int n = arr.length;
        for (int pass = 0; pass < n - 1; pass++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - pass; j++) {
                if (arr[j].amount < arr[j + 1].amount) {
                    Order temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    // O(n log n) : Merge sort, descending by amount
    static void mergeSort(Order[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    static void merge(Order[] arr, int left, int mid, int right) {
        Order[] temp = new Order[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].amount >= arr[j].amount) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    static String amounts(Order[] arr) {
        StringBuilder sb = new StringBuilder();
        for (Order o : arr) {
            sb.append(o.amount).append("  ");
        }
        return sb.toString();
    }

    // Prints the array after each of the first three bubble sort passes
    static void traceFirstThreePasses(Order[] original) {
        Order[] arr = original.clone();
        System.out.println("Bubble sort trace");
        System.out.println("Start  : " + amounts(arr));

        for (int pass = 1; pass <= 3; pass++) {
            for (int j = 0; j < arr.length - pass; j++) {
                if (arr[j].amount < arr[j + 1].amount) {
                    Order temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("Pass " + pass + " : " + amounts(arr));
        }
    }

    public static void main(String[] args) throws Exception {
        List<Order> list = OrderLoader.load("C:/Users/admin/Downloads/orders.csv");

        // Trace on the first 6 orders
        Order[] sample = list.subList(0, 6).toArray(new Order[0]);
        traceFirstThreePasses(sample);

        // Comparison on 10,000 records
        Order[] data = list.subList(0, 10000).toArray(new Order[0]);

        Order[] a = data.clone();
        long start = System.nanoTime();
        bubbleSort(a);
        long bubbleTime = System.nanoTime() - start;

        Order[] b = data.clone();
        start = System.nanoTime();
        mergeSort(b, 0, b.length - 1);
        long mergeTime = System.nanoTime() - start;

        Order[] c = data.clone();
        start = System.nanoTime();
        Arrays.sort(c, (x, y) -> Double.compare(y.amount, x.amount));
        long builtInTime = System.nanoTime() - start;

        System.out.println("\nSorting 10,000 records (descending by amount)");
        System.out.println("Bubble sort   : " + bubbleTime / 1_000_000.0 + " ms");
        System.out.println("Merge sort    : " + mergeTime / 1_000_000.0 + " ms");
        System.out.println("Built-in sort : " + builtInTime / 1_000_000.0 + " ms");

        System.out.println("\nTop 5 orders by amount:");
        for (int i = 0; i < 5; i++) {
            System.out.println(b[i]);
        }
    }
}