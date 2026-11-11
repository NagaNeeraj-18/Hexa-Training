package org.example;


import java.util.*;
public class StacksAndQueues {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        System.out.println("Elements in the Array : "+ Arrays.toString(arr));
        Stack<Integer> st = new Stack<>();
        for(int val:arr){
            st.push(val);
        }
        System.out.println("Reverse the Array : ");
        while(!st.isEmpty()){
            System.out.print(st.pop()+" ");
        }
    }
}

