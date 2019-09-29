/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackapplicationtest;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
import sun.security.util.Debug;


/**
 *
 * @author Student
 */
public class StackApplicationTest {
    private static Scanner scan;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        scan = new Scanner(System.in);        
        Stack<Integer> st = new Stack();
        int num = 0;
        int currentLoopCount = 0;
        int exitCaseLoopCount = 0;
        
        System.out.println("Welcome User");
        System.out.println("How many times you want to run the query ?\n");
        exitCaseLoopCount= returnInputValue();
        
        if(exitCaseLoopCount < 1)
        {
            exitCaseLoopCount = 0;
        }
        
        while(currentLoopCount != exitCaseLoopCount)
        {
            ShowOptions();       
        
            num = returnInputValue();

            switch(num)
            {
                case 1://Push Element in stack
                    int value = returnInputValue();                
                    if(value == -1)
                    {
                        System.out.println("Illegal value\n");
                    }
                    else
                    {
                        st.push(value);
                    }                
                    break;
                case 2://Pop element in stack
                    if(!st.empty())
                        System.out.print("Deleted the element " + st.pop() + "\n"); 
                    break;
                case 3://Print maximum element in stack
                    if(!st.empty())
                    {
                        int maxElement = st.get(0);
                        for(int i = 1; i < st.size(); ++i)
                        {
                            int currentElement = st.get(i);
                            if(maxElement < currentElement)
                                maxElement = currentElement;
                        }
                        System.out.print("Max element is : " + maxElement + "\n"); 
                    }
                    else
                    {
                        System.out.print("Stack is empty\n"); 
                    }
                    break;
//                case 4:
//                    scan.close();
//                    break;

                default:
                    break;
            }
            
            currentLoopCount++;
        }
        
        if(currentLoopCount == exitCaseLoopCount)
        {
             System.out.println("Exiting program, End of Count");
             scan.close();
        }
    }
    
    private static void ShowOptions()
    {
         System.out.println(    "Select a query: \n" +   
                                "1 x - Push the element x into the stack.\n" +
                                "2 -Delete the element present at the top of the stack.\n" +
                                "3 -Print the maximum element in the stack.\n");//+
                                //"4 -Exit");
    }
    
    private static int returnInputValue()
    {
        int num = 0;
        try
        {
            num = scan.nextInt();    
        } 
        catch(InputMismatchException e)
        {
             Debug.println("Input character mismatched", e.getMessage());
             num = -1;
        }
        catch(Exception ex)
        {
            Debug.println("Input character is invalid", null);
            num = -1;
        }        
        return num;
    }    
}
