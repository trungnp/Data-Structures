/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.Collections;
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
        while(current != null && current.getName().compareToIgnoreCase(newCustomer.getName()) <= 0){
            current = current.next;
        }
        
        if(current == null)
            throw new NoSuchElementException();
        return current;
    }
    
    public void insert(Customer newCustomer){
        if(isEmpty())
            insertFirst(newCustomer);
        else {
            Customer current = firstCustomer;
            while(current != null){
                int a = current.getName().compareTo(newCustomer.getName());
                if(a <= 0)
                    current = current.next;
                else
                    break;
            }
            if(current == null)
                insertLast(newCustomer);
            else {
                newCustomer.next = current;
                newCustomer.previous = current.previous;
                current.previous = newCustomer;
                current.previous.next = newCustomer;
            }
        }
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
        SortedCustomerLinkedList myList = new SortedCustomerLinkedList();
        myList.insert(new Customer("a", "123", 1));
        //myList.displayList();
        myList.insert(new Customer("d", "1234", 2));
//        myList.displayList();
        myList.insert(new Customer("e", "1235", 2));
//        myList.displayList();
        myList.insert(new Customer("zz", "1236", 1));
//        myList.displayList();
        myList.insert(new Customer("g", "1237", 1));
        myList.displayList();
        myList.insert(new Customer("r", "1238", 1));
        //myList.displayList();
        myList.insert(new Customer("d", "1239", 1));
        //myList.displayList();
        
    
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

class CustomerIterator implements Iterator{
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
            return !theList.isEmpty();
        else
            return current.next != null;
    }

    @Override
    public Customer next() {
        if(!hasNext())
            throw new NoSuchElementException();
        else {
            isAfterNext = true;
            previous = current;
            if(current == null)
                current = theList.getFirstCustomer();
            else
                current = current.next;
        }
        return current;
    }

    @Override
    public void remove() {
        if(!isAfterNext)
            throw new IllegalStateException();
        if(current == theList.getFirstCustomer())
            theList.deleteFirst();
        else if(current == theList.getLastCustomer())
            theList.deleteLast();
        else {
            previous.next = current.next;
            current.next.previous = current.previous;
        }
        current = previous;
        isAfterNext = false;
    }
    
    public Customer getCurrent(){
        if(theList.isEmpty())
            throw new NoSuchElementException();
        return current;
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
        return this.name.compareTo(other.getName());
    }
}
