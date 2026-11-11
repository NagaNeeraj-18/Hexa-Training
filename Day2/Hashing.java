package org.example;
import java.util.*;
public class Hashing {

    public static void main(String[] args) {
        HashMap<String,Integer> car=new HashMap<>();
        car.put("Bentley", 100000);
        car.put("audi", 200000);
        car.put("supra", 400000);
        car.put("benz", 400000);

        for(String key: car.keySet()){
            System.out.println(key + " " + car.get(key));
        }
    }
}
