/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticalSetTwo;

import java.util.Scanner;

/**
 *
 * @author 1
 */
public class InstaPhotos {

    public static void main(String[] args) {
        int[][] dimensionArray;
        Scanner scan = new Scanner(System.in);
        int L = 0;
        int N = 0;
        dimensionArray = null;
        boolean run = true;

        System.out.println("Enter Min size of image : ");

        while (run) {
            if (scan.hasNextInt()) {
                L = scan.nextInt();
                if (L > 0) {
                    run = false;
                } else {
                    System.out.println("Enter Min size of image : ");
                }
            }
        }
        run = true;

        System.out.println("Enter no of photos : ");
        while (run) {
            if (scan.hasNextInt()) {
                N = scan.nextInt();
                if (N > 0) {
                    dimensionArray = new int[N][3];
                    run = false;
                } else {
                    System.out.println("Enter no of photos : ");
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println("Enter the Width & height for photo");//-1-upload another 1-Crop 2-accepted
            for (int j = 0; j < 2; j++) {
                if (scan.hasNextInt()) {
                    dimensionArray[i][j] = scan.nextInt();
                    if (dimensionArray[i][j] < L) {
                        dimensionArray[i][2] = -1;//upload another one
                    }
                }

                if (j == 1 && dimensionArray[i][2] != -1) {
                    if (dimensionArray[i][j - 1] == dimensionArray[i][j]) {
                        dimensionArray[i][2] = 2;//Accepted
                    } else {
                        dimensionArray[i][2] = 1;//Crop it
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            switch (dimensionArray[i][2]) {
                case -1:
                    System.out.println("UPLOAD ANOTHER");
                    break;

                case 1:
                    System.out.println("CROP IT");
                    break;

                case 2:
                    System.out.println("ACCEPTED");
                    break;
            }
        }
    }
}
