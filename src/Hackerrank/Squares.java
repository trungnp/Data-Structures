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
public class Squares {
    public static int squares(int a, int b) {
        int result = 0;
        
        for(int i = (int)Math.sqrt(a); i <= Math.sqrt(b); i++) {
            if(i*i >= a && i*i <=b)
                result++;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(squares(385793959, 712365911));
    }
}
