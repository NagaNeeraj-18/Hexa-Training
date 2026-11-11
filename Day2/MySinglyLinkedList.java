package org.example;

public class MySinglyLinkedList {
    public static class Node {
        int data;
        Node next;

        Node(int info) {
            data = info;
            next = null;
        }
    }
    Node head = null;
    public void addAtLast(int data) {
        Node ptr = new Node(data);

        if (head == null) {
            head = ptr;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = ptr;
        }
    }
    public void addAtBeginning(int data){
        Node ptr=new Node(data);
        if(head==null){
            head=ptr;
        }
        else{
            ptr.next=head;
            head=ptr;
        }
    }

    public void addAtSpecificPos(int data,int pos){
        Node temp=head;
        Node ptr=new Node(data);
        int i=0;
        while(temp.next!=null){
            if(i==pos-1){
                ptr.next=temp.next;
                temp.next=ptr;
                break;
            }
            temp=temp.next;
            i++;
        }
    }
    public void deleteFromTheEnd(){
        Node temp=head;
        Node save=head;
        if(head==null){
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        while(temp.next!=null){
            save=temp;
            temp=temp.next;
        }
        save.next=null;
    }

    void deleteAtSpecificPos(int pos){
        if(head==null || pos<1){
            return;
        }
        if (pos==1) {
            head=head.next;
            return;
        }
        else{
            Node temp=head;
            int i=1;
            while(temp.next!=null) {
                if (i==pos) {
                    temp.next=temp.next.next;
                    break;
                }
                temp=temp.next;
                i++;
            }
        }
    }

    public void deleteFromFront(){
        if(head==null){
            return;
        }
        else{
            head=head.next;
        }
    }

    public void show() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        MySinglyLinkedList list = new MySinglyLinkedList();

        list.addAtLast(10);
        list.addAtLast(20);
        list.addAtLast(30);
        list.addAtLast(40);
        list.addAtBeginning(50);
        list.addAtBeginning(60);
        list.addAtSpecificPos(100,2);
        list.addAtSpecificPos(120,1);
        list.deleteFromTheEnd();
        list.deleteFromTheEnd();
        list.deleteAtSpecificPos(2);
        list.deleteAtSpecificPos(3);
        list.deleteFromFront();
        list.show();
    }
}