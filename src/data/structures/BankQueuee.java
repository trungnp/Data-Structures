/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author trungnp
 */
public class BankQueuee {
    private Queue<Event> bankQueue;
    private Event[] eventList;
    private int currentTime = 0;
    
    public BankQueuee(){
        bankQueue = new LinkedList<>();
        eventList = new Event[2];
    }
    
    public Scanner readEventFile(String fileName){
        File eventFile = new File(fileName);
        
        try {
            Scanner anEvent = new Scanner(eventFile);
            
            return anEvent;
        } catch (FileNotFoundException ex) { 
            System.out.println("File not found.");
        }
        return null;
    }
    
    public void simulate(Scanner arrivalFile){
        while(arrivalFile.hasNextLine()){
            //Event newEvent = new Event(anEvent.nextLine());
            //Event anEvent = new Event(arrivalFile.nextLine());
            insertEvent(new Event(arrivalFile.nextLine()));
            currentTime = eventList[0].getArrivalTime();
            
            while(!isEmpty()){
                Event newEvent;
                if(eventList[0] == null)
                    newEvent = eventList[1];
                else
                    newEvent = eventList[0];
                if(!newEvent.isDepart())
                    currentTime = newEvent.getArrivalTime();
                if(!newEvent.isDepart())
                    processArrival(newEvent, arrivalFile, eventList, bankQueue);
                else
                    processDeparture(newEvent, arrivalFile, eventList, bankQueue);
            }
        }
    }

    public void processArrival(Event newEvent, Scanner arrivalFile, Event[] eventList, Queue<Event> bankQueue) {
        boolean atFront = bankQueue.isEmpty();
        bankQueue.add(newEvent);
        eventList[0] = null;
        
        if(atFront){
            insertEvent(new Event(currentTime + newEvent.getTransactionTime()));
        }
        
        if(arrivalFile.hasNextLine())
            insertEvent(new Event(arrivalFile.nextLine()));
    }

    public void processDeparture(Event newEvent, Scanner arrivalFile, Event[] eventList, Queue<Event> bankQueue) {
        bankQueue.remove();
        
        eventList[0] = null;
        if(!bankQueue.isEmpty()){
            currentTime += bankQueue.peek().getTransactionTime();
            //insertEvent(new Event(currentTime + bankQueue.peek().getTransactionTime()));
            insertEvent(new Event(currentTime));
        } else {
            if(arrivalFile.hasNextLine())
                insertEvent(new Event(arrivalFile.nextLine()));
        }
    }
    
    public boolean isEmpty(){
        return (eventList[0] == null && eventList[1] == null);
    }
    
    public void insertEvent(Event anEvent){
        if(eventList[0] == null)
            eventList[0] = anEvent;
        else if(eventList[1] == null){
            eventList[1] = anEvent;
            //Arrays.sort(eventList);
        }
        if(eventList[0] != null && eventList[1] != null){
            if(eventList[0].isDepart()){
                if(eventList[0].getDepartureTime() >= eventList[1].getArrivalTime())
                    swapEvent(0, 1);
            } else if(eventList[1].isDepart()){
                if(eventList[0].getArrivalTime() > eventList[1].getDepartureTime())
                    swapEvent(0, 1);
            } else {
                Arrays.sort(eventList);
            }
        }
    }
    
    public void swapEvent(int a, int b){
        Event tmp = eventList[0];
        eventList[0] = eventList[1];
        eventList[1] = tmp;
    }
    
    public static void main(String[] args){
        BankQueuee bankQueuee = new BankQueuee();
        Scanner s = bankQueuee.readEventFile("/Users/trungnp/NetBeansProjects/Data-Structures/src/data/structures/arrivalEvents");
        bankQueuee.simulate(s);
    }
    
}

class Event implements Comparable{
    private int arrivalTime;
    private int transactionTime;
    private int departureTime;
    private boolean isDepart;
    
    public Event(String arrivalEvent){
        String[] times = arrivalEvent.split(" ");
        arrivalTime = Integer.parseInt(times[0]);
        transactionTime = Integer.parseInt(times[1]);
        departureTime = 0;
        isDepart = false;
    }
    
    public Event(int departureTime){
        this.departureTime = departureTime;
        isDepart = true;
    }
    
    public boolean isDepart(){
        return isDepart;
    }
    
    public int getDepartureTime(){
        return departureTime;
    }
    
    public int getArrivalTime(){
        return arrivalTime;
    }
    
    public int getTransactionTime(){
        return transactionTime;
    }

    @Override
    public int compareTo(Object obj) {
        Event otherEvent = (Event)obj;
        
        return (this.getArrivalTime() - otherEvent.getArrivalTime());
    }
}
