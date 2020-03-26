/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author trungnp
 */
public class CircularArrayRotation {
    public static int [] circularArrayRotation(int[] a, int k, int [] queries) {
        int[] q = new int[queries.length];
        //int jump = (a[i] + k) % a.length;
        k = k % a.length;
//        if(k == a.length) {
//            for(int i = 0; i < queries.length; i++) {
//                q[i] = a[queries[i]];
//            }
//        } else {
            for(int i = 0; i < queries.length; i++) {
                int jump = 0;
                if(queries[i] - k < 0) {
                    jump = a.length - Math.abs(queries[i]-k);
                } else 
                    jump = queries[i] - k;
//                    jump = (a.length - Math.abs(queries[i] - k)) % a.length;
                q[i] = a[jump];
            }
//        }
        
//        for(int i = 0; i < a.length; i++) {
//            int jump = (i + k) % a.length;
//            if(jump < queries.length)
//                q[jump] = a[i];
//        }
        
        return q;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
//        int[] a = {1 , 2, 3};
//        int[] q = {0, 1, 2};
        int [] a = new int[515];
        int[] q = new int[500];
        int[] o = new int[500];
        Scanner i1 = new Scanner(new File("/Users/trungnp/NetBeansProjects/Data Structures/src/Hackerrank/input"));
        Scanner i2 = new Scanner(new File("/Users/trungnp/NetBeansProjects/Data Structures/src/Hackerrank/input2"));
        Scanner output = new Scanner(new File("/Users/trungnp/NetBeansProjects/Data Structures/src/Hackerrank/output"));
        int count = 0;
        while(i1.hasNextInt()) {
            a[count++] = i1.nextInt();
        }
        count = 0;
        while(i2.hasNextInt()) {
            o[count] = output.nextInt();
            q[count++] = i2.nextInt();
        }
        
        int[] test = circularArrayRotation(a, 100000, q);
        
        for(int i = 0; i < test.length; i++) {
            if(test[i] != o[i])
                System.out.println(test[i] + " | " + i);
        }
        
//        System.out.println(Arrays.toString(circularArrayRotation(a, 2, q)));
    }
}
