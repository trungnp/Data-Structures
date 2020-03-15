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
public class SortedLinkedList <E extends Comparable<E>>{
    private Node minNode, maxNode;

    public SortedLinkedList() {
        minNode = null;
        maxNode = null;
    }
    
    public boolean isEmpty(){
        return minNode == null;
    }
    
    public Node getMinimumNode(){
        if(minNode == null)
            throw new NoSuchElementException();
        return minNode;
    }
    
    public Node getMaximumNode(){
        if(minNode == null)
            throw new NoSuchElementException();
        return maxNode;
    }
    
    public Node findNode(E data){
        Node newNode = new Node(data);
        Node current = minNode;
        
        while(current != null && current.compareTo(data) < 0){
            current = current.next;
        }
        
        if(current == null)
            throw new NoSuchElementException();
        return current;
    }
    
    public void insertFirst(E data){
        Node newNode = new Node(data);
        
        if(isEmpty())
            last = newNode;
        else
            first.previous = newNode;
        newNode.next = first;
        first = newNode;
    }
    
    public void insertLast(E data){
        Node newNode = new Node(data);
        
        if(isEmpty())
            first = newNode;
        else
            last.next = newNode;
        newNode.previous = last;
        last = newNode;
    }
    
    public void insertAfter(E data, E input){
        Node newNode = new Node(input);
        Node current = findNode(data);
        if(current != null) {
            newNode.next = current.next;
            newNode.previous = current;
            current.next.previous = newNode;
            current.next = newNode;
         }
    }
    
    public void insertBefore(E data, E input){
        Node newNode = new Node(input);
        Node current = findNode(data);
        if(current != null){
            newNode.next = current;
            newNode.previous = current.previous;
            current.previous.next = newNode;
            current.previous = newNode;
        }
    }
    
    public E deleteFirst(){
        E tmp = (E) first;
        
        if(first.next == null)
            last = null;
        else
            first.next.previous = null;
        first = first.next;
        return tmp;
    }
    
    public E deleteLast(){
        E tmp = (E)last;
        
        if(last.previous == null)
            first = null;
        else
            last.previous.next = null;
        last = last.previous;
        return tmp;
    }
    
    public void displayList(){
        Node current = first;
        
        while(current != null){
            current.displayNode();
            current = current.next;
        }
        System.out.println();
    }
    
    public void displayBackward(){
        Node current = last;
        
        while(current != null){
            current.displayNode();
            current = current.previous;
        }
        System.out.println();
    }
    
    public MyIterator getIterator(){
        return new MyIterator(this);
    }
    
    public static void main(String[] args){
        DoublyLinkedList dblLinkedList = new DoublyLinkedList();
        dblLinkedList.insertFirst("a");
        dblLinkedList.insertFirst("b");
        dblLinkedList.insertFirst("c");
        dblLinkedList.insertFirst("d");
        dblLinkedList.insertLast("z");
        dblLinkedList.insertLast("y");
        dblLinkedList.insertLast("x");
        dblLinkedList.insertLast("t");
//        dblLinkedList.displayList();
        dblLinkedList.deleteFirst();
        dblLinkedList.deleteLast();
//        dblLinkedList.displayBackward();
        
        dblLinkedList.insertFirst(1);
        dblLinkedList.insertFirst(2);
        dblLinkedList.insertFirst(3);
        dblLinkedList.insertFirst(4);
        dblLinkedList.insertLast(5);
        dblLinkedList.insertLast(6);
        dblLinkedList.insertLast(7);
        dblLinkedList.insertLast(8);
//        dblLinkedList.displayList();
        
        dblLinkedList.insertAfter(5, 100);
        dblLinkedList.insertAfter(100, 9999);
        dblLinkedList.insertAfter(9999, 2222);
        dblLinkedList.insertBefore(2222, "asjdh");
        dblLinkedList.insertBefore(5, "qiueyeur");
//        dblLinkedList.displayList();
        
        MyIterator i = dblLinkedList.getIterator();
        while (i.hasNext()){
           System.out.print(i.next() + " ");
        }
        System.out.println();
        System.out.println(i.getCurrent());
        i.remove();
        dblLinkedList.displayList();
        dblLinkedList.displayBackward();
        
    }
}

class MyIterator<E> implements Iterator{
    private SortedLinkedList theList;
    private Node current, previous;
    private boolean isAfterNext;

    public MyIterator(SortedLinkedList theList) {
        this.theList = theList;
        current = null;
        previous = null;
        isAfterNext = false;
    }
    
    public boolean hasNext(){
        if(current == null)
            return !theList.isEmpty();
        else
            return (current.next != null);
    }
    
    public Object getCurrent(){
        if(current == null)
            throw new NoSuchElementException();
        return current.data;
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

    @Override
    public void remove() {
        if(!isAfterNext)
            throw new IllegalStateException();
        if(current == theList.getFirst())
            theList.deleteFirst();
        else if (current == theList.getLast())
            theList.deleteLast();
        else {
            previous.next = current.next;
            current.next.previous = current.previous;
        }
        current = previous;
        isAfterNext = false;
    }
}

class Node implements Comparable<Customer>{
    Customer c;
    Node next, previous;

    public Node(Customer c){
        this.c = c;
    }

    public void displayNode(){
        System.out.println(c.toString());
    }

//    @Override
//    public int compareTo(Object obj) {
//        if(data instanceof String)
//            return ((String)this.data).compareTo((String)obj);
//        else
//            return ((Double)this.data).compareTo((Double)obj);
//    }

    @Override
    public int compareTo(Customer other) {
        return this.c.getName().compareTo(other.getName());
    }
}

class Customer {
    private String name;
    private String phoneNumber;
    private double salary;
    
    public Customer(String name, String phoneNumber, double salary){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public double getSalary(){
        return salary;
    }
    
    public String toString(){
        return "Name: " +name+ " || Phone: " +phoneNumber+ " || Salary: " +salary;
    }
}
