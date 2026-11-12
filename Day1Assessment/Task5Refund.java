package org.example.Day1Assessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task5Refund {

    // Greedy: always take the largest note/coin that fits (denominations in descending order)
    static List<Integer> greedy(int[] denoms, int amount) {
        List<Integer> result = new ArrayList<>();
        for (int d : denoms) {
            while (amount >= d) {
                result.add(d);
                amount -= d;
            }
        }
        return result;
    }

    // Dynamic programming: minimum[a] = fewest notes/coins needed to make amount a
    static List<Integer> dynamic(int[] denoms, int amount) {
        int[] minimum = new int[amount + 1];
        int[] lastUsed = new int[amount + 1]; // which gave a optimal count

        Arrays.fill(minimum, Integer.MAX_VALUE);
        minimum[0] = 0;

        for (int a = 1; a <= amount; a++) {
            for (int d : denoms) {
                if (d <= a && minimum[a - d] != Integer.MAX_VALUE
                        && minimum[a - d] + 1 < minimum[a]) {
                    minimum[a] = minimum[a - d] + 1;
                    lastUsed[a] = d;
                }
            }
        }

        // Rebuild which notes/coins were used
        List<Integer> result = new ArrayList<>();
        int a = amount;
        while (a > 0) {
            result.add(lastUsed[a]);
            a -= lastUsed[a];
        }
        return result;
    }

    public static void main(String[] args) {
        // Standard currency: greedy produces the optimal answer
        int[] currency = {2000, 500, 200, 100, 50, 20, 10, 5, 2, 1};
        int refund = 8973;

        List<Integer> g = greedy(currency, refund);
        System.out.println("Refund " + refund + " with normal currency");
        System.out.println("Greedy : " + g.size() + " items " + g);
        List<Integer> d = dynamic(currency, refund);
        System.out.println("DP     : " + d.size() + " items " + d);

        // Non-canonical denomination set where greedy fails
        int[] odd = {4, 3, 1};
        int amount = 6;

        List<Integer> g2 = greedy(odd, amount);
        List<Integer> d2 = dynamic(odd, amount);

        System.out.println("\nRefund " + amount + " with denominations " + Arrays.toString(odd));
        System.out.println("Greedy : " + g2.size() + " items " + g2 + "   <-- not the minimum");
        System.out.println("DP     : " + d2.size() + " items " + d2 + "   <-- correct minimum");
    }
}
