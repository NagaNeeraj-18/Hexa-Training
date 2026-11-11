package org.example;
import java.util.*;
public class BinarySearch {
    public static int binarySearch(int[] arr,int val){
        Arrays.sort(arr);
        int start=0,end=arr.length-1;
        while(start<=end){
            int mid=start+(end-start)/2;
            if(val==arr[mid]){
                return mid;
            }
            if(val<arr[mid]){
                end=mid-1;
            }
            else{
                start=mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the size of an array: ");
        int n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        System.out.println("value to search for: ");
        int val=sc.nextInt();
        int res=binarySearch(arr,val);
        System.out.println(res);
    }
}
