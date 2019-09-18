/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hashing;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Hashing 
{ 
    private class DataNode
    {
        String value = null;
    }
    
    private Scanner scan;
    private int hashTableSize = 0;
    
    private DataNode[] hashedDataArray;
    
    public static void main(String[] args) 
    {
        Hashing hashing = new Hashing();
        hashing.DisplayOptions();
    }
    
    void DisplayOptions()
    {
        scan = new Scanner(System.in);
        System.out.println("Enter the size of table");
        while( hashTableSize == 0)
        {
            if(scan.hasNextInt())
                hashTableSize = scan.nextInt();
        }
        
        hashedDataArray = new DataNode[hashTableSize];
        
        int option = 0;
       
        while(option != 5)
        {
            System.out.println("Operation :\n1:Insert\n2:Search\n3:Delete\n4:Print\n5:Exit");
            if(scan.hasNextInt())
                option = scan.nextInt();
            
            switch(option)
            {
                case 1:
                    Insert();
                    break;
                    
                case 2:
                    boolean run = true;
                    while(run)
                    {
                        if(scan.hasNextInt())
                        {
                            int value = scan.nextInt();
                            run = false;
                            String searchPos = Search(value);
                            if(searchPos == null)
                                System.out.print("Value is absent");
                            else
                                System.out.print("Value is present at : " + searchPos);
                        }
                    }
                break;
                    
                case 3:
                    Delete();
                    break;
                    
                case 4:
                    System.out.println("Hashed array : ");
                    for(int i = 0; i < hashedDataArray.length; i++)
                    {
                        if(hashedDataArray[i] != null && hashedDataArray[i].value != "")
                            System.out.println( "Index : " + i + " : " + hashedDataArray[i].value);
                    }
                break;
                    
                default:
                    break;
            }
        }
    }
    
    void Insert()
    {
        int value;
        boolean run = true;
        while(run)
        {
            if(scan.hasNextInt())
            {
                value = scan.nextInt();
                run = false;
                int index = getHashedIndex(value);
                if(hashedDataArray[index] == null)
                {
                    DataNode node = new DataNode();
                    node.value =  value+"";
                    hashedDataArray[index] = node;
                    System.out.println("Inserted " + value + " at " + index + "\n");
                } 
                else
                {
                    if(hashedDataArray[index].value != null)//go to next index where its not null
                    {
                        int count = 0;
                        boolean runloop = true;
                        while(count < hashedDataArray.length && runloop)
                        {
                            if(hashedDataArray[index] == null || hashedDataArray[index].value == null)
                            {
                                if(hashedDataArray[index] == null)
                                {
                                    DataNode node = new DataNode();
                                    node.value =  value+"";
                                    hashedDataArray[index] = node;
                                    System.out.println("Inserted " + value + " at " + index);
                                }else
                                {
                                    hashedDataArray[index].value = value+"";
                                    System.out.println("Inserted " + value + " at " + index);
                                }
                                runloop = false;
                            }   
                            count++;
                            
                            if(index >= hashedDataArray.length - 1)
                                index = 0;
                            else
                                index++;
                        }
                        
                        if(runloop)
                        {
                            System.out.println("Overflow");
                        }
                    }
                    else
                    {
                        hashedDataArray[index].value = value+"";
                        System.out.println("Inserted " + value + " at " + index);
                    }
                }
            }
        }
    }
    
    String Search(int value)
    {   
        String indexV = null;
       
        int index = getHashedIndex(value);
        String finalValue = hashedDataArray[index].value;
        if(finalValue.equals(value+""))
        {
            indexV = index+"";

        }
        else //Search till end
        {
            for(int i = 0; i < hashedDataArray.length; i++)
            {
                 if(index >= hashedDataArray.length)
                    index = 0;

                 index++;

                 if(hashedDataArray[index].value.equals(value+""))
                 {
                     indexV = index+"";
                     //System.out.print("Value is present at : " + indexV);
                     break;
                 } 
                 else if(hashedDataArray[index] == null)//if instance of class is present then its already used node so we can iterate over it
                 {
                     break;
                 }
            }
        }
        return indexV;
    }
    
    void Delete()
    {
        boolean run = true;
        int value;
        while(run)
        {
            if(scan.hasNextInt())
            {
                run = false;
                value = scan.nextInt();
                //int index = getHashedIndex(value);
                String posofElement = Search(value);
                if(!posofElement.equals(null))
                {
                    hashedDataArray[ Integer.parseInt(posofElement)].value = null;
                }        
            }
        }
    }
    
    int getHashedIndex(int value)
    {
        return value%hashTableSize;
    }
}