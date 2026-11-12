package org.example.Day1Assessment;

import java.util.ArrayDeque;
import java.util.List;

public class Task4StackQueue {

    public static void main(String[] args) throws Exception {
        List<Order> orders = OrderLoader.load("C:/Users/admin/Downloads/orders.csv");

        // ---------- Undo last order edit (own Stack) ----------
        System.out.println("Undo demo using MyStack");
        Order order = orders.get(0);
        MyStack<Double> undoStack = new MyStack<>();

        double[] newAmounts = {100.00, 250.50, 999.99};
        for (double newAmount : newAmounts) {
            undoStack.push(order.amount); // save old value before editing
            System.out.println("Edit : " + order.amount + " -> " + newAmount);
            order.amount = newAmount;
        }
        while (!undoStack.isEmpty()) {
            double oldAmount = undoStack.pop();
            System.out.println("Undo : " + order.amount + " -> " + oldAmount);
            order.amount = oldAmount;
        }

        // ---------- Dispatch line (own Queue) ----------
        System.out.println("\nDispatch demo using MyQueue");
        MyQueue<Order> dispatchLine = new MyQueue<>();
        for (int i = 0; i < 5; i++) {
            dispatchLine.enqueue(orders.get(i));
            System.out.println("Enqueued order " + orders.get(i).orderId);
        }
        while (!dispatchLine.isEmpty()) {
            System.out.println("Dispatched order " + dispatchLine.dequeue().orderId);
        }

        // ---------- Compare own vs built-in ----------
        int n = 1_000_000;
        Order sample = orders.get(0);

        // JVM warm-up
        for (int w = 0; w < 3; w++) {
            testOwnStack(n, sample);
            testBuiltInStack(n, sample);
            testOwnQueue(n, sample);
            testBuiltInQueue(n, sample);
        }

        System.out.println("\nTime for " + n + " operations");
        System.out.println("MyStack          : " + testOwnStack(n, sample) / 1_000_000.0 + " ms");
        System.out.println("ArrayDeque stack : " + testBuiltInStack(n, sample) / 1_000_000.0 + " ms");
        System.out.println("MyQueue          : " + testOwnQueue(n, sample) / 1_000_000.0 + " ms");
        System.out.println("ArrayDeque queue : " + testBuiltInQueue(n, sample) / 1_000_000.0 + " ms");
    }

    static long testOwnStack(int n, Order o) {
        MyStack<Order> stack = new MyStack<>();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) stack.push(o);
        while (!stack.isEmpty()) stack.pop();
        return System.nanoTime() - start;
    }

    static long testBuiltInStack(int n, Order o) {
        ArrayDeque<Order> stack = new ArrayDeque<>();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) stack.push(o);
        while (!stack.isEmpty()) stack.pop();
        return System.nanoTime() - start;
    }

    static long testOwnQueue(int n, Order o) {
        MyQueue<Order> queue = new MyQueue<>();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) queue.enqueue(o);
        while (!queue.isEmpty()) queue.dequeue();
        return System.nanoTime() - start;
    }

    static long testBuiltInQueue(int n, Order o) {
        ArrayDeque<Order> queue = new ArrayDeque<>();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) queue.offer(o);
        while (!queue.isEmpty()) queue.poll();
        return System.nanoTime() - start;
    }
}