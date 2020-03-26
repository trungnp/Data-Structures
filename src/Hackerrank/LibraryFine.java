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
public class LibraryFine {
    public static int libraryFine(int d1, int m1, int y1, int d2, int m2, int y2) {
        int result = 0;
        
        if(y2 < y1) {
            result = 10000;
        } else if (y2 == y1){
            if(m2 < m1) {
                result = 500 * (m1-m2);
            } else if (m2 == m1) {
                if(d2 < d1) {
                    result = 15 * (d1-d2);
                }
            }
        }
        
        return result;
    }
}
