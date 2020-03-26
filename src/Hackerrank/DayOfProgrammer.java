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

public class DayOfProgrammer {

    // Complete the dayOfProgrammer function below.
    static String dayOfProgrammer(int year) {
        String result = "";
        int eightMonths = 243;
        if(1700 <= year && year < 1918){
            if(year % 4 == 0)
                eightMonths++;
        } else if (1919 <= year && year <= 2700) {
            if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                eightMonths++;
            }
        } else {
            eightMonths = eightMonths - 13;
        }
        result = String.valueOf(256-eightMonths) + ".09." + String.valueOf(year);
        return result;
    }
    

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        String result = dayOfProgrammer(year);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

