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
public class ViralAdvertising {
    public int viralAdvertising(int n) {
        int result = 2;
        int totalPeople = 2;
        
        if(n == 1) {
            return 2;
        }
        else {
            for(int i = 2; i <= n; i++) {
                totalPeople = (totalPeople*3)/2;
                result += totalPeople;
            }
        }
        
        return result;
    }
}
