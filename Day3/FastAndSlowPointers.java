package org.example.Day3;

public class FastAndSlowPointers {

    class Node{
        int data;
        Node next;

        Node(int data){
            this.data=data;
            this.next=null;
        }
    }

    Node head;

    public void add(int data){
            Node newNode=new Node(data);
            if(head==null){
                head=newNode;
            }

            Node current=head;
            while(current.next!=null){
                current=current.next;
            }
            current.next=newNode;
    }

    public void display(){
        Node current=head;
        while(current!=null){
            System.out.println(current.data + "->");
            current=current.next;
        }
        System.out.println("null");
    }


    public int middleElement(){

        Node slow=head;
        Node fast=head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow.data;
    }
    public static void main(String[] args) {

    }
}
