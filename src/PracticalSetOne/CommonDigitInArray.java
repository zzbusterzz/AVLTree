/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticalSetOne;

import java.util.Scanner;

/**
 *
 * @author 1
 */
public class CommonDigitInArray {

    public static void main(String[] args) {
        int[] noofPoeple = null;
        int[][] intarrCount = null;
        Scanner scan = new Scanner(System.in);
        int arrayLength = 0;
        boolean run = true;

        System.out.println("Enter array length");

        while (run) {
            if (scan.hasNextInt()) {
                arrayLength = scan.nextInt();
                if (arrayLength > 0) {
                    noofPoeple = new int[arrayLength];
                    intarrCount = new int[10][arrayLength];
                    run = false;
                }
            }
        }

        System.out.println("Enter digits");
        run = true;
        int indexMax = 0;

        while (run) {
            try {
                noofPoeple[indexMax] = scan.nextInt();
                indexMax++;
                if (indexMax >= arrayLength) {
                    run = false;
                }
            } catch (Exception ex) {
                run = false;
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < arrayLength; j++) {
                int t1 = noofPoeple[j];
                while (t1 > 0) {
                    int t2 = t1 % 10;
                    intarrCount[t2][j] = 1;
                    t1 = t1 / 10;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 10; i++) {
            int value = 0;
            for (int j = 0; j < arrayLength; j++) {
                if (intarrCount[i][j] == 1) {
                    value = value + 1;
                }
            }
            ans = Math.max(value, ans);
        }

        System.out.println("Maximum number of subseqnce is : " + ans);
    }
}
