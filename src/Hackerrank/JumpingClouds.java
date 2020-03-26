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
public class JumpingClouds {
    public static int jumpingClouds(int[] c, int k) {
        int result = 100;
        int jump = 0;
        while(true) {
            jump = (jump+k) % c.length;
            if(c[jump] == 0)
                result--;
            else
                result -= 3;
            if(jump == 0)
                break;
        }
        
        return result;
    }
}
