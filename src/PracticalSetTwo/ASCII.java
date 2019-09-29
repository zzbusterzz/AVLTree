/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticalSetTwo;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author 1
 */
public class ASCII {

    public static void main(String[] args) {
        String oldString;
        int runtimes = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter test cases");
        runtimes = sc.nextInt();
        sc.nextLine();

        int currentRun = 0;

        while (currentRun < runtimes) {
            System.out.println("Enter the String");
            oldString = sc.nextLine();

            String newString = "";
            for (int i = 0; i < oldString.length(); i++) {
                int ASCII = oldString.charAt(i);
                if (isPrimeNo(ASCII)) {
                    System.out.print("Is prime no: " + ASCII);
                    newString += oldString.charAt(i);
                } else {
                    newString += (char) getClosestPrimeNoToAValue(ASCII);
                }
            }

            System.out.println("Dhanajay magical word is : " + newString + " : old string is : " + oldString);
            currentRun++;
        }
    }

    private static boolean isPrimeNo(int value) {
        for (int i = 2; i <= value / 2; ++i) {
            // condition for nonprime number
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int getClosestPrimeNoToAValue(int value) {
        boolean notfoundPrime = true;
        int iterationCount = 1;
        while (notfoundPrime) {
            if (isPrimeNo(value - iterationCount)) {
                return value - iterationCount;
            }

            if (isPrimeNo(value + iterationCount)) {
                return value + iterationCount;
            }

            iterationCount++;
        }
        return 0;
    }
}
