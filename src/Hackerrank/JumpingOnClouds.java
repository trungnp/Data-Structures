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
public class JumpingOnClouds {
    public static int jumpingOnClouds(int[] c) {
        int result = 0;
        int count = 0;
        
        while(count < c.length) {
            if(c[count+1] == 0) {
                if(c[count+2] == 0)
                    count = count+3;
                else {
                    count++;
                }
                result++;
            } else {
                count += 2;
                result++;
            }
        }
        
        return result;
//        Arrays.sort(c);
//        int count = 0;
//        int a = 0;
//        while(a < c.length) {
//            if(c[count] == 0)
//                count++;
//            a++;
//        }
//        //count++;
//        int s = count/2 + (c.length - (count));
//
//        return s;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
//        int[] a = {0,0,1,0,0,1,0};
        Scanner s = new Scanner(new File("/Users/trungnp/NetBeansProjects/Data Structures/src/Hackerrank/input"));
        int[] a = new int[100];
        int count = 0;
        while(s.hasNextInt())
            a[count++] = s.nextInt();
        System.out.println(jumpingOnClouds(a));
    }
}
