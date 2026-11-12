package org.example.Day1Assessment;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyQueue<T> {
    private final LinkedList<T> elements = new LinkedList<>();

    public void enqueue(T item) {
        elements.addLast(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements.removeFirst();
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements.getFirst();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }
}