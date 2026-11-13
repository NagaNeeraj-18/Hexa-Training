package org.example.Day3;

public class TargetSum {
    public static void main(String[] args) {
        int[] arr=new int[]{10,20,30,40,50};
        int target=50;
        int i=0,j=arr.length-1,sum=0;
        while(i<=j){
            sum=arr[i]+arr[j];
            if(sum==target){
                System.out.println(arr[i] + " " + arr[j]);
            }
            if(sum>target){
                j--;
            }else{
                i++;
            }
        }
    }
}
