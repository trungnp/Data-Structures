/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
        Customer current = firstCustomer;
        Customer previousCustomer = firstCustomer;
        if(isEmpty()){
            insertFirst(newCustomer);
        }
        else {
            while(current != null && current.getName().compareTo(newCustomer.getName()) < 0){
                previousCustomer = current;
                current = current.next;
            }
            if(current == null)
                insertLast(newCustomer);
            else {
                if(current.previous == null){
                    newCustomer.previous = current.previous;
                    newCustomer.next = current;
                    current.previous = newCustomer;
                    firstCustomer = newCustomer;
                } else {
                newCustomer.next = previousCustomer.next;
                newCustomer.previous = previousCustomer;
                previousCustomer.next = newCustomer;
                current.previous = newCustomer;
                }
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
    
    public static Scanner readFile(String fileName){
        File customerFile = new File(fileName);
        try {
            Scanner s = new Scanner(customerFile);
            return s;
        } catch (FileNotFoundException ex){
            System.out.println("File not found.");
        }
        return null;
    }
    
    public CustomerIterator getIterator(){
        return new CustomerIterator(this);
    }
    
    public static void main(String[] args){
        SortedCustomerLinkedList myList = new SortedCustomerLinkedList();
        Scanner input = readFile("/Users/trungnp/NetBeansProjects/Data-Structures/src/data/structures/customer");
        
        while(input.hasNextLine()){
            String a = input.nextLine();
            myList.insert(new Customer(a));
        }
//        myList.displayList();
        
        CustomerIterator i = myList.getIterator();
        while(i.hasNext())
            System.out.println(i.next().toString());
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

class Customer{
    private String name;
    private String phoneNumber;
    private double salary;
    Customer next, previous;
    
    public Customer(String infoString){
        String[] info = infoString.split(" ");
        this.name = info[0];
        this.phoneNumber = info[1];
        this.salary = Integer.parseInt(info[2]);
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

//    @Override
//    public int compareTo(Customer other) {
//        return this.name.compareTo(other.getName());
//    }
}
