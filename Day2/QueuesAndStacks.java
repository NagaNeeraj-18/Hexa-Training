package org.example;

import java.util.*;

public class QueuesAndStacks {
    public static void main(String[] args) {

        int[] arr = {3, 9, 11, 1 , 5, 8};

        Queue<Integer> q;
        q = new LinkedList<>();

        for(int i = 0; i < arr.length; i++) {
            q.add(arr[i]);
        }

        int temp = q.poll();
        q.add(temp);

        while(!q.isEmpty()) {
            System.out.print(q.poll() + " ");
        }
    }
}
