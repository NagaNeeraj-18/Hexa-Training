package org.example;

import java.util.Stack;

public class UndoHistory {

    static Stack<String> history = new Stack<>();
    public static void doAction(String action) {
        history.push(action);
    }

    public static String undo() {
        if (history.isEmpty()) {
            return "nothing to undo";
        }
        String last = history.pop();
        // REVERT last action
        return "undid: " + last;
    }

    public static void main(String[] args) {
        doAction("enroll Anu");
        doAction("enroll Ravi");
        doAction("move Ravi to Batch B");

        System.out.println(undo());
        System.out.println(undo());
        System.out.println("latest kept: " + history.peek());
    }
}