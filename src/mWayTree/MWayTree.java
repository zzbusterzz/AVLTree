/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mWayTree;

import java.util.Scanner;
import sun.security.ssl.Debug;

/**
 *
 * @author 1
 */
public class MWayTree 
{
    private Scanner scan;
    private int option;
    private int order = 2;//Binary Tree is of order two
    
    private MWayNode rootNode = null;
    public static void main(String[] args) 
    {
        MWayTree mwayTree = new MWayTree();
        mwayTree.DisplayOptions();
    }
    
    public void DisplayOptions()
    {
        scan = new Scanner(System.in);
        System.out.println("Enter the value for order of tree");
        
        boolean loop = true;
        while(loop)
        {
            if(scan.hasNextInt())
            {
                order = scan.nextInt();
                loop  =false;
            }else
                scan.next();
        }
        
        rootNode = new MWayNode(order);
        
        while(option != 4)
        {
            System.out.print("1:Insert(Enter a char type to break away from insertion)\n2:Delete\n3:Print\n4:Exit\n");
            switch(scan.nextInt())
            {
                case 1://insertion
                    loop = true;
                    while(loop)
                    {
                        if(scan.hasNextInt())
                            InsertInMWayTree(scan.nextInt());
                        else
                        {
                            loop = false;
                            scan.next();
                        }
                    }
                    
                    break;
                    
                case 2://deletion
                    break;
                    
                case 3://print
                    break;
                    
                case 4:
                    option = 4;
                    break;
                    
                default:
                    break;
            }
        }
    }
    
    void InsertInMWayTree(int value)
    {
        Debug.println("Entered character", value + "");
    }
    
    void DeleteFromMWayTree(){
        
    }
    
    void PrintMWayTree(){
        
    }
}
