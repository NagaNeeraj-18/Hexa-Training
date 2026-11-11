package org.example;
import java.util.Scanner;

public class posOfTheElement{
    public static int posOfTheElement(int[] arr, int val) {
        for(int i=0;i<arr.length;i++){
            if(val==arr[i]){
                return i;
            }
        }
        return -1;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        int val=sc.nextInt();
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int res=posOfTheElement(arr,val);
        System.out.println(res);
    }
}

