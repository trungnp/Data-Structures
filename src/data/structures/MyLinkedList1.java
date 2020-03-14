/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author trungnp
 */
 class ListIterator implements Iterator{
    private MyLinkedList1 theList;
    private Node current, previous;
    private boolean isAfterNext;
    public ListIterator(MyLinkedList1 theList){
        this.theList = theList;
        current = null;
        previous = null;
        isAfterNext = false;
    }
    
    @Override
    public boolean hasNext(){
        if(current == null){
            return (theList.getFirst() != null);
        }
        else
            return current.next != null;
    }

    @Override
    public Object next() {
        if(!hasNext())
            throw new NoSuchElementException();
        else {
            previous = current;
            isAfterNext = true;
            if(current == null)
                current = theList.getFirst();
            else
                current = current.next;
        }
        return current.data;
    }
    
    public void remove(){
        if(!isAfterNext)
            throw new IllegalStateException();
        if(current == theList.getFirst())
            theList.deleteFirst();
        else
            previous.next = current.next;
        current = previous;
        isAfterNext = false;
    }
    
    public void reset(){
        ListIterator listIterator = new ListIterator(theList);
    }
    
    public Object getCurrent(){
        if(current == null)
            throw new NoSuchElementException();
        return current;
    }
    
    public void insertAfter(Object data){
        Node newNode = new Node(data);
        if(theList.isEmpty()){
            theList.setFirst(data);
        } else {
            newNode.next = current.next;
            current.next = newNode;
        }
        current = newNode;
    }
    
    public void insertBefore(Object data){
        Node newNode = new Node(data);
        if(previous == null){
            newNode.next = theList.getFirst();
            theList.setFirst(data);
        } else {
            newNode.next = current;
            previous.next = newNode;
        }
        current = newNode;
    }
    
}

class Node {
    Object data;
    Node next;

    public Node(Object data) {
        this.data = data;
    }
    
    public void displayNode(){
        System.out.print(data + " ");
    }
}

public class MyLinkedList1 {
    private Node first;
    
    public MyLinkedList1(){
        first = null;
    }
    
    public void setFirst(Object data){
        Node newNode = new Node(data);
        first = newNode;
    }
    
    public Node getFirst(){
        if(first == null) { throw new NoSuchElementException(); }
        return first;
    }
    
    public void addFirst(Object data){
        Node newNode = new Node(data);
        newNode.next = first;
        first = newNode;
    }
    
    public Iterator getIterator(){
        return new ListIterator(this);
    }
    
    public Node deleteFirst(){
        Node tmp;
        if(first == null)
            throw new NoSuchElementException();
        else{
            tmp = first;
            first = first.next;
        }
        return tmp;
    }
    
    public boolean isEmpty(){
        return first == null;
    }
    
    public void displayForward(){
        Node current = first;
        
        while(current != null){
            current.displayNode();
            current = current.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args){
        MyLinkedList1 x = new MyLinkedList1();
        x.addFirst("A");x.addFirst("B");x.addFirst("C");x.addFirst("D");x.addFirst("E");x.addFirst("M");
        Iterator i = x.getIterator();
        while (i.hasNext()){
           System.out.print(i.next() + " ");
           //i.remove();
          // i.next();
        }
        //i.remove();
        System.out.println();
        
        Iterator ii = x.getIterator();
        while (ii.hasNext()){
           System.out.print(ii.next() + " ");
        }
        System.out.println();
        x.displayForward();
    }
}