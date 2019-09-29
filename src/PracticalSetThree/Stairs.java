/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticalSetThree;

import java.util.Scanner;

/**
 *
 * @author 1
 */
public class Stairs {

    static int count;

    public static int count(int n) {
        if (n <= 1) {
            return 1;
        } else if (n == 2) {
            return count(n - 1) + count(n - 2);
        } else {
            return count(n - 1) + count(n - 2) + count(n - 3);
        }
    }

    public static void main(String[] args) {
        int noOfStairs;
        Stairs str = new Stairs();
        System.out.println("Enter number of stairs:");
        Scanner s = new Scanner(System.in);
        noOfStairs = s.nextInt();
        System.out.println(count(noOfStairs));
    }
}
