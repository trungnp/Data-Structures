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
    private Queue<Integer> bankQueue;
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
    
    public void simulate(Scanner anEvent){
        while(anEvent.hasNextLine()){
            //Event newEvent = new Event(anEvent.nextLine());
            insertEvent(new Event(anEvent.nextLine()));
            currentTime += eventList[0].getArrivalTime();
            
            while(eventList.length != 0){
                Event newEvent = eventList[0];
                if(!newEvent.isDepart())
                    processArrival(newEvent, anEvent, eventList, bankQueue);
                else
                    processDeparture(newEvent, eventList, bankQueue);
            }
        }
    }

    public void processArrival(Event newEvent, Scanner anEvent, Event[] eventList, Queue<Integer> bankQueue) {
        boolean atFront = bankQueue.isEmpty();
        bankQueue.add(newEvent.getArrivalTime());
        eventList[0] = null;
        
        if(atFront){
            insertEvent(new Event(currentTime + newEvent.getTransactionTime()));
        }
        
        if(anEvent.hasNextLine())
            insertEvent(new Event(anEvent.nextLine()));
    }

    public void processDeparture(Event newEvent, Event[] eventList, Queue<Integer> bankQueue) {
        bankQueue.remove();
        
        eventList[0] = null;
        if(!bankQueue.isEmpty())
            insertEvent(new Event(currentTime + newEvent.getTransactionTime()));
    }
    
    public void insertEvent(Event anEvent){
        if(eventList.length == 0 || eventList[0] == null)
            eventList[0] = anEvent;
        else {
            eventList[1] = anEvent;
            Arrays.sort(eventList);
//            if(eventList[0].isDepart()){
//                if(eventList[0].getDepartureTime() >= anEvent.getArrivalTime()){
//                    Event tmp = eventList[0];
//                    eventList[1] = eventList[0];
//                    eventList[0] = anEvent;
//                } else {
//                    eventList[1] = anEvent;
//                }
//            } else {
//                if(eventList[0].getArrivalTime() >= anEvent.getArrivalTime()){
//                    Event tmp = eventList[0];
//                    eventList[1] = eventList[0];
//                    eventList[0] = anEvent;
//                } else {
//                    eventList[1] = anEvent;
//                }
//            }
        }
    }
    
    public static void main(String[] args){
        BankQueuee bankQueuee = new BankQueuee();
        Scanner s = bankQueuee.readEventFile("/Users/trungnp/NetBeansProjects/Data Structures/src/data/structures/arrivalEvents");
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
        if(this.isDepart)
            return this.getDepartureTime() >= otherEvent.getArrivalTime() ? -1 : 0;
        else
            return this.getArrivalTime()>= otherEvent.getArrivalTime() ? -1 : 0;
    }
}
