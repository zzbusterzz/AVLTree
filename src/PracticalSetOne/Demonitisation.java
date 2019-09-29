/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticalSetOne;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author 1
 */
public class Demonitisation {
    
    public static void main(String[] args) {
        int[] noofPoeple = new int[500];
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter height of the persons in cms who are in queue(non int to quit)");
        boolean run = true;
        int indexMax = 0;
        
        while(run){
            try{
                noofPoeple[indexMax] = scan.nextInt();
                indexMax++;
            }catch (Exception ex){
                run = false;
            }
        }
        
        int groupCount = 0;
        int currentIndex = 1;
        
        while(currentIndex <= indexMax){
            if(noofPoeple[currentIndex - 1] > noofPoeple[currentIndex])
                groupCount++;
            
            currentIndex++;
        }
        
        System.out.println("Total Groups in queue are: " + groupCount);
    }
}
