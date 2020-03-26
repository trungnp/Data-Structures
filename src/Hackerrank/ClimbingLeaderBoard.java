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
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ClimbingLeaderBoard {

    // Complete the climbingLeaderboard function below.
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int[] result = new int[alice.length];
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(scores[0]);
       
        
        for(int i = 1; i < scores.length; i++) {
            if(scores[i] != tmp.get(tmp.size()-1))
                tmp.add(scores[i]);
        }
        
        for(int i = 0; i < alice.length; i++) {
            if(alice[i] > tmp.get(0))
                result[i] = 1;
            else if(alice[i] < tmp.get(tmp.size()-1))
                result[i] = tmp.size() + 1;
            else {
                int rank = binarySearch(scores, alice[i], 0, scores.length-1);
                result[i] = rank;
            }
        }
        
        
        return result;
    }
    
    static int binarySearch(int[] array, int key, int low, int high) {
        
        int mid = low + (high-low) / 2;
        
        if(low > high)
            return low;
        
        if(array[mid] == key)
            return mid;
        else if(array[mid] > key)
            return binarySearch(array, key, mid+1, high);
        else
            return binarySearch(array, key, low, mid-1);
        
    }
    
    public static void main(String[] args) {
        int[] a = {100,100,50,40,40,20,10};
        int[] b = {5,25,50,120};
//        int[] a = {100,90,90,80,75,60};
//        int[] b = {50,65,77,90,102};
        System.out.println(Arrays.toString(climbingLeaderboard(a, b)));
    }

//    private static final Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int scoresCount = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        int[] scores = new int[scoresCount];
//
//        String[] scoresItems = scanner.nextLine().split(" ");
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int i = 0; i < scoresCount; i++) {
//            int scoresItem = Integer.parseInt(scoresItems[i]);
//            scores[i] = scoresItem;
//        }
//
//        int aliceCount = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        int[] alice = new int[aliceCount];
//
//        String[] aliceItems = scanner.nextLine().split(" ");
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int i = 0; i < aliceCount; i++) {
//            int aliceItem = Integer.parseInt(aliceItems[i]);
//            alice[i] = aliceItem;
//        }
//
//        int[] result = climbingLeaderboard(scores, alice);
//
//        for (int i = 0; i < result.length; i++) {
//            bufferedWriter.write(String.valueOf(result[i]));
//
//            if (i != result.length - 1) {
//                bufferedWriter.write("\n");
//            }
//        }
//
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
//
//        scanner.close();
//    }
}

