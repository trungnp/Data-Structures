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
    private ArrayList<Event> eventList;
    private int currentTime = 0;
    private int people = 0;
    private int waitTime = 0;
    private int longestLine = 0;
    private int maximumWaitTime = 0;
    
    public BankQueuee(){
        bankQueue = new java.util.LinkedList<>();
        eventList = new ArrayList<>();
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
        eventList.add(new Event(arrivalFile.nextLine()));
        System.out.println("Simulation Begins");
        while(!eventList.isEmpty()){
            Collections.sort(eventList);
            Event newEvent = eventList.get(0);
            currentTime = newEvent.getArrivalTime();
            if(!newEvent.isDepart()){
                people++;
                System.out.println("Processing an arrival event at time: " +currentTime);
                processArrival(newEvent, arrivalFile, eventList, bankQueue);
                longestLine = Math.max(longestLine, bankQueue.size());
            }
            else {
                processDeparture(newEvent, eventList, bankQueue);
            }
            printEventList(eventList);
        }
        System.out.println("Simulation Ends.");
        System.out.println("Maximum wait time: " +maximumWaitTime);
        System.out.println("Longest line: " +longestLine);
        System.out.println("Number of people processed: " + (people));
        System.out.printf("Wait time on average: %.2f\n", (waitTime*1.0 / people));
    }

    public void processArrival(Event newEvent, Scanner arrivalFile, ArrayList eventList, Queue<Event> bankQueue) {
        boolean atFront = bankQueue.isEmpty();
        bankQueue.add(newEvent);
        eventList.remove(0);
        
        if(atFront){
            eventList.add(new Event(currentTime + newEvent.getTransactionTime()));
        }
        if(arrivalFile.hasNextLine())
            eventList.add(new Event(arrivalFile.nextLine()));
        
    }

    public void processDeparture(Event newEvent, ArrayList eventList, Queue<Event> bankQueue) {
        System.out.println("Processing a departure event at time: " + (newEvent.getArrivalTime()));
        
        bankQueue.remove();
        
        eventList.remove(0);
        
        if(!bankQueue.isEmpty()){
            waitTime += newEvent.getArrivalTime() - bankQueue.peek().getArrivalTime();
            maximumWaitTime = Math.max(maximumWaitTime, newEvent.getArrivalTime() - bankQueue.peek().getArrivalTime());
            currentTime += bankQueue.peek().getTransactionTime();
            eventList.add(new Event(currentTime));
        } 
    }
    
    public void printEventList(ArrayList<Event> events){
        System.out.println("Event list:");
        if(events.isEmpty())
            System.out.println("empty");
        else {
            int count = 0;
            for(Event e : events){
                if(e != null)
                System.out.println((++count) + "/ " +e);
            }
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
    private boolean isDepart;
    
    public Event(String arrivalEvent){
        String[] times = arrivalEvent.split(" ");
        arrivalTime = Integer.parseInt(times[0]);
        transactionTime = Integer.parseInt(times[1]);
        isDepart = false;
    }
    
    public Event(int departureTime){
        this.arrivalTime = departureTime;
        isDepart = true;
    }
    
    public boolean isDepart(){
        return isDepart;
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
    
    public String toString(){
        if(isDepart)
            return "Event departs at " + getArrivalTime();
        else
            return "Event arrives at " + getArrivalTime();
    }
}
