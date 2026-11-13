package org.example.Day3;

import java.util.*;
public class PalindromeCheck {
    public static boolean isPalindrome(String str) {
        if (str==null) return false;
        String clean=str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int left=0;
        int right=clean.length()-1; 
        while (left<right) {
            if (clean.charAt(left) != clean.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public static void main(String[] args) {
        String input="RaceCar";
        System.out.println("Is Palindrome: " + isPalindrome(input)); // true
    }
}
