/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * @author trungnp
 */
class ParArray{
    private long[] theArray;
    private int nElements;
    
    public ParArray(int n){
        theArray = new long[n];
        nElements = 0;
    }
    
    public void insert(long n){
        theArray[nElements] = n;
        nElements++;
    }
    
    public int size(){
        return nElements;
    }
    
    public void display(){
        System.out.print("A=");
        for(int i = 0; i < nElements; i++)
            System.out.print(theArray[i] + " ");
        System.out.println(" ");
    }
    
    public int partitionIt(int left, int right){
        if((right - left) < 3){
            manualSort(left, right);
            return left;
        } else {
        long pivot = theArray[right];
        int leftPtr = left - 1;
        int rightPtr = right ;
        
        while(true){
            while(theArray[++leftPtr] < pivot)
                ;
            while(rightPtr > left && theArray[--rightPtr] > pivot)
                ;
            if(leftPtr >= rightPtr)
                break;
            else
                swap(leftPtr, rightPtr);
        }
        swap(leftPtr, right);
        return leftPtr;
        }
    }
    
    public void swap(int left, int right){
        long temp;
        temp = theArray[left];
        theArray[left] = theArray[right];
        theArray[right] = temp;
    }

    public void manualSort(int left, int right) {
        if(left == right)
            ;
        else if (right - left == 1){
            if(theArray[left] > theArray[right]){
                swap(left, right);
            }
        }
        else {
            if(theArray[left] > theArray[right]){
                swap(left, right);
            }
            if(theArray[left] > theArray[right-1])
                swap(left, right-1);
            if(theArray[right-1] > theArray[right]){
                swap(right-1, right);
            }
        }
    }
}

public class ParRightPiv {
    public static void main(String[] args) {
        int maxSize = 3;
        ParArray arr;
        arr = new ParArray(maxSize);
        
        for(int i = 0; i < maxSize; i++){
            long n = (int)(Math.random()*199);
            arr.insert(n);
//            arr.insert(15);
//            arr.insert(33);
//            arr.insert(4);
        }
        
        arr.display();
        int partDex = arr.partitionIt(0, maxSize-1);
        System.out.println(", Parrtition is at index " + partDex);
        arr.display();
    }
}
