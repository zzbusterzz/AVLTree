/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackapplicationtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Student
 */
public class BracketTest 
{
    private static Stack<Character> stack;
    private static Scanner scan;
    private static ArrayList<Character> bracketCharacters;
    public static void main(String[] args) 
    {
        stack = new Stack();
        Stack<Character> endBracketStack = new Stack<Character>();
        scan = new Scanner(System.in);
        bracketCharacters = new ArrayList<Character>(Arrays.asList('{', '}', '[', ']', '(', ')'));
        
        String value = "0";
        
        while(!value.isEmpty() || !value.equals(""))
        {
            System.out.println("Enter the string to validate(Enter to exit)");
            value = scan.nextLine();

            //TODO: Remove characters and make string only of brackets

            char charAt;
            if(value.equals(null) || !value.equals(""))//Enters the characters in stack
            {
                for(int i = 0; i < value.length(); ++i)
                {
                    charAt = value.charAt(i);
                    if(bracketCharacters.contains(charAt))
                        stack.push(charAt);
                }
                
                while(!stack.empty()){
                    if(!endBracketStack.isEmpty()){
                        charAt = endBracketStack.peek();
                        StackPushPopOnBrackets(charAt, endBracketStack, stack);
                    } 
                    else
                    {
                        endBracketStack.push(stack.pop());
                    }
                }
                
                if(endBracketStack.size() == 2)//Pop last two elements and compare
                {
                    charAt = endBracketStack.peek();
                    StackPushPopOnBrackets(charAt, endBracketStack,endBracketStack);
                }
                
                if(endBracketStack.isEmpty())
                    System.out.println("YES");
                else
                    System.out.println("NO");
                
                stack.clear();                
            } 
            else
                System.out.println("End of Input");
        }
    }
    
    private static void StackPushPopOnBrackets(char charAt, Stack<Character> endBracketStack, Stack<Character> stack){
        switch(charAt){
            case  '}':
            case  '{':
                if(stack.peek().equals('{')){
                    endBracketStack.pop();
                    stack.pop();
                }else{
                    endBracketStack.push(stack.pop());
                }

                break;

            case ']':
            case '[':
                if(stack.peek().equals('[')){
                    endBracketStack.pop();
                    stack.pop();
                }else{
                    endBracketStack.push(stack.pop());
                }
                break;

            case ')':
            case '(':
                 if(stack.peek().equals('(')){
                    endBracketStack.pop();
                    stack.pop();
                }else{
                    endBracketStack.push(stack.pop());
                }
                break;
        }
    }
}
