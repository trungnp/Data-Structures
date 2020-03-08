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
public class Bank3Queues {
    private Queue<Event> queue1, queue2, queue3;
    //private Event[] eventList;
    private ArrayList<Event> eventList;
    private int currentTime = 0;
    private int people = 0;
    private int waitTime = 0;
    private int longestLine = 0;
    private int maximumWaitTime = 0;
    
    public Bank3Queues(){
        queue1 = new LinkedList<>();
        queue2 = new  LinkedList<>();
        queue3 = new LinkedList<>();
        eventList = new ArrayList<>();
    }
    
//    public String getName(Queue<Event> e){
//        
//    }
    
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
//        insertEvent(new Event(arrivalFile.nextLine()));
        eventList.add(new Event(arrivalFile.nextLine()));
        System.out.println("Simulation Begins");
        while(!eventList.isEmpty()){
            Event newEvent = eventList.get(0);
            currentTime = newEvent.getArrivalTime();
            if(!newEvent.isDepart()){
                people++;
                System.out.println("Processing an arrival event at time: " +currentTime+ " at " +shortestQueue(queue1, queue2, queue3).getClass());
                processArrival(newEvent, arrivalFile, eventList, shortestQueue(queue1, queue2, queue3));
                //ongestLine = Math.max(longestLine, bankQueue.size());
            }
            else {
                System.out.println("Processing a departure event at time: " +currentTime+ " from " +departureQueue(queue1, queue2, queue3).getClass().getSimpleName());
                processDeparture(newEvent, eventList, departureQueue(queue1, queue2, queue3));
            }
//            printEventList(eventList);
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
            insertEvent(new Event(currentTime + newEvent.getTransactionTime()));
        }
        if(arrivalFile.hasNextLine())
            insertEvent(new Event(arrivalFile.nextLine()));
        
    }

    public void processDeparture(Event newEvent, ArrayList eventList, Queue<Event> bankQueue) {
        System.out.println("Processing a departure event at time: " + (newEvent.getArrivalTime()));
        
        bankQueue.remove();
        
        eventList.remove(0);
        
        if(!bankQueue.isEmpty()){
            waitTime += newEvent.getArrivalTime() - bankQueue.peek().getArrivalTime();
            maximumWaitTime = Math.max(maximumWaitTime, newEvent.getArrivalTime() - bankQueue.peek().getArrivalTime());
            currentTime += bankQueue.peek().getTransactionTime();
            insertEvent(new Event(currentTime));
        } 
    }
    
    public Queue departureQueue(Queue<Event> q1, Queue<Event> q2, Queue<Event> q3){
//        int departureTime;
//        if(!q1.isEmpty() || !q2.isEmpty() || !q3.isEmpty()){
            if(!q1.isEmpty()){
                if(q1.peek().getArrivalTime() + q1.peek().getTransactionTime() == currentTime)
                    return q1;
            }
            if(!q2.isEmpty()){
                if(q2.peek().getArrivalTime() + q2.peek().getTransactionTime() == currentTime)
                    return q2;
            }
            if(!q3.isEmpty()){
                if(q3.peek().getArrivalTime() + q3.peek().getTransactionTime() == currentTime)
                    return q3;
            } 
//        } 
        
        //return null;
//            departureTime = Math.min(q2.peek().getArrivalTime(), q3.peek().getArrivalTime());
//        departureTime = Math.min(Math.min(q1.peek().getArrivalTime(), q2.peek().getArrivalTime())
//                , q3.peek().getArrivalTime());
//        if(q1.peek().getArrivalTime() == departureTime)
//            return q1;
//        else if(q2.peek().getArrivalTime() == departureTime)
//            return q2;
//        else
//            return q3;
        return null;
    }
    
    public Queue shortestQueue(Queue q1, Queue q2, Queue q3){
        int shortestLine = Math.min(Math.min(queue1.size(), queue2.size()), queue3.size()); 
        if(q1.size() == shortestLine)
            return q1;
        else if(q2.size() == shortestLine)
            return q2;
        else
            return q3;
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
    
    public void insertEvent(Event anEvent){
        eventList.add(anEvent);
        Collections.sort(eventList);
    }
    
    public static void main(String[] args){
        Bank3Queues bank3Queues = new Bank3Queues();
        Scanner s = bank3Queues.readEventFile("/Users/trungnp/NetBeansProjects/Data Structures/src/data/structures/arrivalEvents");
        bank3Queues.simulate(s);
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
    
    @Override
    public String toString(){
        if(isDepart)
            return "Event departs at " + getArrivalTime();
        else
            return "Event arrives at " + getArrivalTime();
    }
}
