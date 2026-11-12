package org.example.Day2Assessment;

public class Q4SlidingWindow {

    static int longestRun(int[] claims, int limit) {
        int left = 0;
        int best = 0;

        for (int right = 0; right < claims.length; right++) {
            if (claims[right] <= limit) {
                left = right + 1;
            }
            int length = right - left + 1;
            if (length > best) {
                best = length;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        int limit = 100;

        System.out.println("Test 1: empty input");
        System.out.println("Longest run: " + longestRun(new int[0], limit));

        System.out.println("\nTest 2: mixed days");
        int[] days = {80, 120, 130, 150, 90, 101, 102, 70};
        System.out.println("Longest run: " + longestRun(days, limit));

        System.out.println("\nTest 3: no day above 100");
        System.out.println("Longest run: " + longestRun(new int[]{10, 50, 100, 99}, limit));

        System.out.println("\nTest 4: every day above 100");
        System.out.println("Longest run: " + longestRun(new int[]{101, 200, 150}, limit));
    }
}
