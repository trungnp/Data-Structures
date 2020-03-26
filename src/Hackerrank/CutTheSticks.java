/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author trungnp
 */

public class CutTheSticks {
    public static int[] cutTheSticks(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        Arrays.sort(arr);
        int count = arr.length;
        
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) {
                count--;
                
                continue;
            }
            result.add(count);
            int tmp = arr[i];
            for(int j = i; j < arr.length; j++) {
                if(arr[j] == arr[i])
                    count--;
                arr[j] = arr[j] - tmp;
            }
        }
        
        int[] a =  new int[result.size()];
        for(int b = 0; b < result.size(); b++)
            a[b] = result.get(b);
        return a;

    }
    
    
    public static void main(String[] args) {
        int[] a = {5,4,4,2,2,8};
        System.out.println(Arrays.toString(cutTheSticks(a)));
    }
}
