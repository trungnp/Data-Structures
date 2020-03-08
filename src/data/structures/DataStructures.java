/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.Arrays;

/**
 *
 * @author trungnp
 */
public class DataStructures {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("This is first line of code");
        
        int x[][] = {{3,10}, {7,5}};
        
        int c[][] = a(x);
        
        c[0][0] = 100;
        
        for(int i = 0; i < c.length; i++){
            for(int j = 0; j < c.length; j++)
                System.out.println(x[i][j]);
        }
        
    }
    
    public static int [][] a(int [][] b){
        return b;
    }
    
}
