/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.util.Arrays;

/**
 *
 * @author trungnp
 */
public class AngryProfessor {
    public String angryPressor(int k, int [] a) {
        Arrays.sort(a);
        int count = 0;
        while(a[count] <= 0) {
            count++;
        }
        if(count+1 <= k)
            return "YES";
        else
            return "NO";
    }
}
