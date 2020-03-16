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
public class SortedCustomerLinkedList {
    private Customer firstCustomer, lastCustomer;

    public SortedCustomerLinkedList() {
        firstCustomer = null;
        lastCustomer = null;
    }
    
    public boolean isEmpty(){
        return firstCustomer == null;
    }
    
    public Customer getFirstCustomer(){
        if(firstCustomer == null)
            throw new NoSuchElementException();
        return firstCustomer;
    }
    
    public Customer getLastCustomer(){
        if(firstCustomer == null)
            throw new NoSuchElementException();
        return lastCustomer;
    }
    
    public Customer findCustomer(Customer newCustomer){
        Customer current = firstCustomer;
        while(current != null && current.getName().compareToIgnoreCase(newCustomer.getName()) < 0){
            current = current.next;
        }
        
        if(current == null)
            throw new NoSuchElementException();
        return current;
    }
    
    public void insertFirst(Customer newCustomer){
        if(isEmpty())
            lastCustomer = newCustomer;
        else
            firstCustomer.previous = newCustomer;
        newCustomer.next = firstCustomer;
        firstCustomer = newCustomer;
    }
    
    public void insertLast(Customer newCustomer){
        if(isEmpty())
            firstCustomer = newCustomer;
        else
            lastCustomer.next = newCustomer;
        newCustomer.previous = lastCustomer;
        lastCustomer = newCustomer;
    }
    
    public void insertAfter(Customer find, Customer newCustomer){
        Customer current = findCustomer(find);
        if(current != null){
            newCustomer.next = current.next;
            newCustomer.previous = current;
            current.next = newCustomer;
            current.next.previous = newCustomer;
        }
    }
    
    public void insertBefore(Customer find, Customer newCustomer){
        Customer current = findCustomer(find);
        if(current != null){
            newCustomer.next = current;
            newCustomer.previous = current.previous;
            current.previous = newCustomer;
            current.previous.next = newCustomer;
        }
    }
    
    public Customer deleteFirst(){
        Customer deletedCustomer = firstCustomer;
        if(firstCustomer.next == null)
            lastCustomer = null;
        else
            firstCustomer.next.previous = null;
        firstCustomer = firstCustomer.next;
        return deletedCustomer;
    }
    
    public Customer deleteLast(){
        Customer deletedCustomer = lastCustomer;
        if(lastCustomer.previous == null)
            firstCustomer = null;
        else
            lastCustomer.previous.next = null;
        lastCustomer = lastCustomer.previous;
        return deletedCustomer;
    }
    
    public void displayList(){
        Customer current = firstCustomer;
        while(current != null){
            System.out.println(current.toString());
            current = current.next;
        }
    }
    
    
    
    public CustomerIterator getIterator(){
        return new CustomerIterator(this);
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
        
//        MyIterator i = dblLinkedList.getIterator();
//        while (i.hasNext()){
//           System.out.print(i.next() + " ");
//        }
//        System.out.println();
//        System.out.println(i.getCurrent());
//        i.remove();
//        dblLinkedList.displayList();
//        dblLinkedList.displayBackward();
        
    }
}

class CustomerIterator<Customer> implements Iterator<Customer>{
    private SortedCustomerLinkedList theList;
    private Customer current, previous;
    private boolean isAfterNext;
    
    public CustomerIterator(SortedCustomerLinkedList theList){
        this.theList = theList;
        current = previous = null;
        isAfterNext = false;
    }

    @Override
    public boolean hasNext() {
        if(current == null)
            return !theList.getFirstCustomer();
        else
            
    }

    @Override
    public Customer next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        Iterator.super.remove(); //To change body of generated methods, choose Tools | Templates.
    }
    
}

class Customer implements Comparable<Customer>{
    private String name;
    private String phoneNumber;
    private double salary;
    Customer next, previous;
    
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

    @Override
    public int compareTo(Customer other) {
        return this.name.compareToIgnoreCase(other.getName());
    }
}
