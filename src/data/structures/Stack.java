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
public class Stack {
    public static void main(String[] args) {
        int[] a = {1,4,3,6,8,3,2,100,5,5};
        System.out.println(findLargest(a, 1, 3));
    }
    
    public static int findLargest(int[] a, int first, int last){
        if(first == last)
            return a[first];
        if(a[first] > a[last])
            return findLargest(a, first, last-1);
        else
            return findLargest(a, first+1, last);
    }
    
}
