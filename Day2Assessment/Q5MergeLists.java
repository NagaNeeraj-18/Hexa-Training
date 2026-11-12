package org.example.Day2Assessment;

import java.util.Arrays;

public class Q5MergeLists {

    static int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k] = a[i];
                i++;
            } else {
                result[k] = b[j];
                j++;
            }
            k++;
        }

        while (i < a.length) {
            result[k] = a[i];
            i++;
            k++;
        }

        while (j < b.length) {
            result[k] = b[j];
            j++;
            k++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: both lists empty");
        System.out.println(Arrays.toString(merge(new int[0], new int[0])));

        System.out.println("\nTest 2: one list empty");
        System.out.println(Arrays.toString(merge(new int[]{1001, 1005}, new int[0])));

        System.out.println("\nTest 3: with duplicates");
        int[] officeA = {1001, 1004, 1004, 1010};
        int[] officeB = {1002, 1004, 1008, 1010, 1020};
        System.out.println(Arrays.toString(merge(officeA, officeB)));
    }
}