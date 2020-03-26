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
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class MigratoryBirds {

    // Complete the migratoryBirds function below.
    static int migratoryBirds(List<Integer> arr) {
        int result = 0;
        int result2 = 0;
        int result3 = 0, result4 = 0, result5 = 0;
        for(int a : arr) {
            if(a == 1)
                result++;
            else if (a == 2)
                result2++;
            else if (a == 3)
                result3++;
            else if(a == 4)
                result4++;
            else
                result5++;
        }
        int compare = Math.max(Math.max(Math.max(Math.max(result5, result4), result3), result2), result);
        if(compare == result)
            return 1;
        else if(compare == result2)
            return 2;
        else if(compare == result3)
            return 3;
        else if(compare == result4)
            return 4;
        else 
            return 5;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = migratoryBirds(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

