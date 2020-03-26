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
public class UtopiaTree {
    public int utopianTree(int n) {
        int a = 1;
        
        if(n == 0)
            return 1;
        else {
            for(int i = 1; i <= n; i++){
                if(i % 2 == 0)
                    a++;
                else
                    a *= 2;
            }
        }
        return a;
    }
}
