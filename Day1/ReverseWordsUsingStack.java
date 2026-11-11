package org.example;
import com.sun.security.jgss.GSSUtil;

import java.util.*;
public class ReverseWordsUsingStack {
    public static String reverseTheSentence(String str){
        Stack<Character> st=new Stack<>();
        StringBuilder result=new StringBuilder();
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(ch!=' '){
                st.push(ch);
            }else{
                while(!st.isEmpty()){
                    result.append(st.pop());
                }
                result.append(' ');
            }
        }
        while(!st.isEmpty()){
            result.append(st.pop());
        }
        return result.toString();
    }

    public static void main(String[] args){
        String in="ym eman si ahsa";

        String rev=reverseTheSentence(in);
        System.out.println(in);
        System.out.println(rev);
    }
}
