package org.example.Day3;

import java.util.*;
public class ReverseHalfTheArray {
    public static void main(String[] args) {
        int[] a=new int[]{6,7,8,9,1,2,3,4};
        int i=0,j=0;
        if(a.length/2 % 2 ==0){
            j=a.length/2;
        }else{
            j=a.length/2+1;
        }
        for(i=0;i<a.length/2;i++,j++){
            int temp=a[i];
            a[i]=a[j];
            a[j]=temp;
        }
        System.out.println(Arrays.toString(a));
    }
}
