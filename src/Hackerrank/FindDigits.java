/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

/**
 *
 * @author trungnp
 */
public class FindDigits {
    public static int findDigits(int n) {
        int result = 0;
        String[] a = String.valueOf(n).split("");
        
        for(String i : a) {
            int b = Integer.parseInt(i);
            if(b == 0)
                continue;
            else {
                if (n % b == 0)
                    result++;
            }
        }
        
        return result;
    }
}
