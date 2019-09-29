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
public class ArrayPattern {
     public static void main(String[] args){
        
       System.out.print("Enter 2D array size : ");
       Scanner sc=new Scanner(System.in);
       int sum;
       
       int previous_sum = Integer.MIN_VALUE;
       
       int m = sc.nextInt();
       int n = sc.nextInt();
       
       System.out.println("Enter values : ");     
       int dimensionalArray[][] = new int[m][n];
       
       for(int i=0; i<m; i++) {
           for(int j=0; j<n;j++) {
               dimensionalArray[i][j]=sc.nextInt();
           }
       }
       
       System.out.print("\nThe Array that you entered : \n");
       for(int[] x: dimensionalArray){
           for(int y:x){
               System.out.print(y+"  ");
           }
           System.out.println();
       }
       
        for(int i=0; i<4;i++){
            for(int j=0; j<4;j++) {
                   sum=dimensionalArray[i][j]+dimensionalArray[i][j+1]+dimensionalArray[i][j+2]+dimensionalArray[i+1][j+1]+dimensionalArray[i+2][j]+dimensionalArray[i+2][j+1]+dimensionalArray[i+2][j+2];
                   previous_sum=Math.max(previous_sum, sum);
            }   
        }
        
        System.out.println("sum="+previous_sum);
        
    }  
}
