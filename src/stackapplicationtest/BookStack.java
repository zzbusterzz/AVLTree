/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackapplicationtest;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author 1
 */
public class BookStack {
        public static void main(String[] args){
        
        int firststk, secondstk, thirdstk;
        
        Stack<Integer> stack1 = new Stack();
        Stack<Integer> stack2 = new Stack();
        Stack<Integer> stack3 = new Stack();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("How many elements you want top enter in the first Stack? :");
        firststk=sc.nextInt();
        System.out.println("Enter "+firststk+" elements in first stack!");
        
        for(int i=0;i<firststk;i++){
            int x = sc.nextInt();
            stack1.push(x);
        }
        System.out.println(""+stack1+"");        
        System.out.println("How many elements you want top enter in the second Stack? :");
        secondstk=sc.nextInt();
        System.out.println("Enter "+secondstk+" elements in second stack!");
        for(int i=0;i<secondstk;i++){
           int y = sc.nextInt();
           stack2.push(y);
        }
        System.out.println(""+stack2+"");
        
        System.out.println("How many elements you want top enter in the third Stack? :");
        thirdstk=sc.nextInt();
        System.out.println("Enter "+thirdstk+" elements in third stack!");
        for(int i=0;i<thirdstk;i++){
           int z = sc.nextInt();
           stack3.push(z);
        }
        System.out.println(""+stack3+"");
        
        int sum1 =0;
        for(int i=0;i<stack1.size();i++){
            sum1 = sum1 + stack1.pop();
        }
        System.out.println(""+sum1+"");
        
        int sum2 =0;
        for(int i=0;i<stack2.size();i++){
            sum2 = sum2 + stack2.pop();
        }
        System.out.println(""+sum2+"");
        
        int sum3 =0;
        for(int i=0;i<stack3.size();i++){
            sum3 = sum3 + stack3.pop();
        } 
        System.out.println(""+sum3+"");
        
        if (sum1==sum2 & sum2==sum3){
            System.out.println("The Max Sum is : "+sum1);
        }

        if(sum1>sum2 && sum1>sum3) {
            sum1=sum1-stack1.pop();
        }
        else if(sum2>sum1 && sum2>sum3) {
            sum2=sum2-stack2.pop();
        }
        else if(sum3>sum1 && sum3>sum2) {
            sum3=sum3-stack3.pop();
        }
        System.out.println("Max is "+sum1);
    }
}