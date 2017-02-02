/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedList;

/**
 *
 * @author Klevis
 */
class Node2<T> extends Node2Abstract<T>{
    
    public Node2 first = null;
    public Node2 last = null;

    Node2(T val) {
        
        super(val);
        first = last = this;
    }
    
    void add(T val) {
        Node2<T> newNode;
        newNode = new Node2<T>(val);
        
        if (first.next == null){
            first.next = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
    }

    
    Node2<T> remove() {
        if (first == null){
            System.out.println("The list is empty.");
            return null;
        } else {
        Node2 temp = last;
        last.prev.next = null;
        last = last.prev;
        return temp;
        }
        
    }

    
    void showHead(int m) {
        Node2 temp = first.next;
        for (int i = 0; temp.next != null && i < m; i++){
            System.out.println(temp.val);
            temp = temp.next;                    
        }
    }

    
    void showTail(int m) {
        Node2 temp = last;
        for (int i = 0; i < m && temp.prev != null; i++){
            System.out.println(temp.val);
            temp = temp.prev;
        }
    }

    
    Node2<T> removeOdd() {
      Node2 temp = first;
      Node2 temp2 = first.next;
      while (temp2 != last){
        if (((Integer)(temp.next.val ))% 2 == 0){
            temp = temp.next;
            temp2 = temp2.next;
        } else {
            if (temp == first){
                temp.next = temp2.next;
                temp2.next.prev = temp;
                temp2 = temp2.next;
                first.next = temp2;
                temp2 = first.next;
            } else {
                temp.next = temp2.next;
                temp2.next.prev = temp;
                temp2 = temp2.next;
            }
        }
      }
      last = temp;
      return last;  
    }
    
}
