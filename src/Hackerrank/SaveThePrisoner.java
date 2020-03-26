/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author trungnp
 */
public class SaveThePrisoner {
    public static int saveThePrisoner(int n, int m, int s) {
//        int result = s;
        int count = m % n;
        
        if(count == 1)
            return s;
        
        int result = s + count;
        if(result > n) {
            result = result % (n+1);
        }
        else {
            result--;
        }
        
        if(result == 0)
                result = n;
        
        return result;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("/Users/trungnp/NetBeansProjects/Data Structures/src/Hackerrank/input"));
        Scanner o = new Scanner(new File("/Users/trungnp/NetBeansProjects/Data Structures/src/Hackerrank/output"));
        int count = 0;
        while(s.hasNextLine()) {
            //System.out.println(saveThePrisoner(s.nextInt(), s.nextInt(), s.nextInt()));
            int a = saveThePrisoner(s.nextInt(), s.nextInt(), s.nextInt());
            int b = o.nextInt();
            count++;
            if(a != b) {
                System.out.println(a + " | " +count);
            }
        }
        
    }
}
