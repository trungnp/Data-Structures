/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.util.ArrayList;

/**
 *
 * @author trungnp
 */
public class RepeatedString {
    public static long repeatedString(String s, long n) {
        long result = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        
        if(!s.contains("a"))
            return 0;
        else {
            if(s.length() == 1)
                return n;
            if(n < s.length()) {
                for(int i = 0; i < n; i++) {
                if(s.charAt(i) == 'a')
                    arr.add(i);
                }
                return arr.size();
            } else {
                for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == 'a')
                    arr.add(i);
                }

                if(n > s.length()) {
                    long multiply =n / s.length();
                    long remainder = n % s.length();
                    result = arr.size() * multiply;
                    if(remainder != 0) {
                        for(int a : arr) {
                            if(a < remainder)
                                result++;
                        }
                    }
                } else {
                    return arr.size();
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        long b = repeatedString("ababa", 3);
        System.out.println(b);
    }
}
