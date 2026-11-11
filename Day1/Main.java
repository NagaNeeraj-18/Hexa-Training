package org.example;

class Animal{
    void sound(){
        System.out.println("animals make noise");
    }
}
class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("dog barks");
    }
}
class Main{
    public static void main(String[] args) {
        Animal a;
        a=new Dog();
        a.sound();
    }
}
