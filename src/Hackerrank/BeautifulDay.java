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
public class BeautifulDay {
    public static int beautifulDay(int i, int j, int k) {
        int result = 0;
        StringBuilder sb;
        
        for(int a = i; a <= j; a++) {
            sb = new StringBuilder(String.valueOf(a));
            sb.reverse();
            int b = Integer.valueOf(sb.toString());
            if(Math.abs(a-b) % k == 0)
                result++;
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(beautifulDay(20, 23, 6));
    }
}
