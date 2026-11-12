package org.example.Day2Assessment;

public class Q1BST {

    static class Node {
        int id;
        Node left;
        Node right;

        Node(int id) {
            this.id = id;
        }
    }

    static Node root = null;

    static Node insert(Node node, int id) {
        if (node == null) {
            return new Node(id);
        }
        if (id < node.id) {
            node.left = insert(node.left, id);
        } else if (id > node.id) {
            node.right = insert(node.right, id);
        }
        return node;
    }

    static boolean search(Node node, int id) {
        if (node == null) {
            return false;
        }
        if (id == node.id) {
            return true;
        }
        if (id < node.id) {
            return search(node.left, id);
        }
        return search(node.right, id);
    }

    static void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.id + " ");
        inorder(node.right);
    }

    static void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.id + " ");
        preorder(node.left);
        preorder(node.right);
    }

    static void postorder(Node node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.id + " ");
    }

    public static void main(String[] args) {
        System.out.println("Test 1: empty tree");
        System.out.println("Search 101: " + search(root, 101));
        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();

        int[] claimIds = {5005, 5002, 5008, 5001, 5003, 5007, 5009, 5003, 5008};
        for (int id : claimIds) {
            root = insert(root, id);
        }

        System.out.println("\nTest 2: inserted 5005 5002 5008 5001 5003 5007 5009 5003 5008");
        System.out.println("(5003 and 5008 were inserted twice, duplicates are ignored)");

        System.out.print("Inorder   : ");
        inorder(root);
        System.out.println();

        System.out.print("Preorder  : ");
        preorder(root);
        System.out.println();

        System.out.print("Postorder : ");
        postorder(root);
        System.out.println();

        System.out.println("\nTest 3: search");
        System.out.println("Search 5007: " + search(root, 5007));
        System.out.println("Search 9999: " + search(root, 9999));
    }
}
