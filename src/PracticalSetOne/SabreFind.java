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
public class SabreFind {
    public static void main(String[] args) {
        System.out.println("Enter array length m & n");
        boolean run = true;
        Scanner scan = new Scanner(System.in);
        int arrayLengthM = 0;
        int arrayLengthN = 0;
        String[][] charString = null;
        while (run) {
            if (scan.hasNextInt()) {
                if(arrayLengthM > 0){
                    arrayLengthN = scan.nextInt();
                    charString = new String [arrayLengthM][arrayLengthN];
                    run = false;
                }
                else
                    arrayLengthM = scan.nextInt();
            }
        }

        System.out.println("Enter strings");
        
        for(int i = 0; i < arrayLengthM; i++){
            if(scan.hasNextLine()){
                String word = scan.nextLine();
                if(word.length() > arrayLengthN ||  word.equals("")){
                    System.out.println("Enter new phrase");
                    i--;
                } 
                else
                {
                    for(int j = 0; j < word.length(); j++){
                        charString[i][j] = word.charAt(j)+"";
                    }
                }
            }
        }
        
        int sabaCount = 0;
        for(int i = 0; i < arrayLengthM; i++){
            for(int j = 0; j < arrayLengthN; j++){//horizontal
                if((j+3 < arrayLengthN) 
                        && charString[i][j].equalsIgnoreCase("s")
                        && charString[i][j+1].equalsIgnoreCase("a")
                        && charString[i][j+2].equalsIgnoreCase("b")
                        && charString[i][j+3].equalsIgnoreCase("a")){
                   sabaCount++;
                }
                
                if((i+3 < arrayLengthM) //vertical
                        && charString[i][j].equalsIgnoreCase("s")
                        && charString[i+1][j].equalsIgnoreCase("a")
                        && charString[i+2][j].equalsIgnoreCase("b")
                        && charString[i+3][j].equalsIgnoreCase("a")){
                        sabaCount++;
                }
                
                 if((j+3 < arrayLengthN && i+3 < arrayLengthM) //diagonal
                        && charString[i][j].equalsIgnoreCase("s")
                        && charString[i+1][j+1].equalsIgnoreCase("a")
                        && charString[i+2][j+2].equalsIgnoreCase("b")
                        && charString[i+3][j+3].equalsIgnoreCase("a")){
                   sabaCount++;
                }
            }
        }
        
        System.out.println("Saba count = "+ sabaCount);
    }
}
