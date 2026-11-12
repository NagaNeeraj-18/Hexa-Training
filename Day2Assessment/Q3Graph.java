package org.example.Day2Assessment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q3Graph {

    Map<String, List<String>> graph = new HashMap<>();

    void addStage(String stage) {
        graph.putIfAbsent(stage, new ArrayList<>());
    }

    void addEdge(String from, String to) {
        addStage(from);
        addStage(to);
        graph.get(from).add(to);
    }

    List<String> shortestPath(String start, String end) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(end)) {
                List<String> path = new ArrayList<>();
                String step = end;
                while (step != null) {
                    path.add(step);
                    step = parent.get(step);
                }
                Collections.reverse(path);
                return path;
            }

            for (String next : graph.get(current)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    parent.put(next, current);
                    queue.add(next);
                }
            }
        }
        return null;
    }

    boolean hasLoop() {
        Set<String> visited = new HashSet<>();
        Set<String> onPath = new HashSet<>();

        for (String stage : graph.keySet()) {
            if (dfs(stage, visited, onPath)) {
                return true;
            }
        }
        return false;
    }

    boolean dfs(String current, Set<String> visited, Set<String> onPath) {
        if (onPath.contains(current)) {
            return true;
        }
        if (visited.contains(current)) {
            return false;
        }

        visited.add(current);
        onPath.add(current);

        for (String next : graph.get(current)) {
            if (dfs(next, visited, onPath)) {
                return true;
            }
        }

        onPath.remove(current);
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: empty graph");
        Q3Graph empty = new Q3Graph();
        System.out.println("Path: " + empty.shortestPath("Registration", "Settlement"));
        System.out.println("Has loop: " + empty.hasLoop());

        System.out.println("\nTest 2: normal flow");
        Q3Graph g = new Q3Graph();
        g.addEdge("Registration", "Assessment");
        g.addEdge("Assessment", "Surveyor");
        g.addEdge("Surveyor", "Settlement");
        g.addEdge("Assessment", "Settlement");
        System.out.println("Shortest Registration -> Settlement: " + g.shortestPath("Registration", "Settlement"));
        System.out.println("Shortest Registration -> Surveyor: " + g.shortestPath("Registration", "Surveyor"));
        System.out.println("Shortest Settlement -> Registration: " + g.shortestPath("Settlement", "Registration"));
        System.out.println("Has loop: " + g.hasLoop());

        System.out.println("\nTest 3: flow with a loop");
        g.addEdge("Surveyor", "Assessment");
        System.out.println("Has loop: " + g.hasLoop());
    }
}