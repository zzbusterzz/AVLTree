/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlistpracticals;

import java.util.InputMismatchException;
import java.util.Scanner;
import sun.security.util.Debug;

/**
 *
 * @author Student
 */
public class LinkedList 
{
    private class Link
    {
        public long data;
        public Link nextLink;
        
        public boolean isVisited = false;
        public Link(long data)
        {
            this.data = data;
        }
    }    
    
    private Link beginNode = null;
    private Scanner scan = null;
    private int option = 0;
    private int count = 0;
    private int maxAllocatedSize = 100;
    
    public LinkedList()
    {
        scan = new Scanner(System.in);
        option = 0;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        LinkedList list = new LinkedList();
        list.DisplayOptions();
    }
    
    private void DisplayOptions()
    {
        while(option != 7)
        {
            System.out.println("Select a option: \n1:Add Value(-1 to break out) \n2:Print List \n3:Check for cycle \n4:Introduce cycle at the end \n5:Remove cycle at the end  \n6:Rotate List to right \n7:Exit");
            option = getOptionIntValue();            
            switch(option)
            {
                case 1:
                        try
                        {
                            Long value = 0L;
                            while(value != -1)
                            {
                                value = scan.nextLong();
                                if(value != -1)
                                    insertData(value);
                            }
                        }
                        catch(InputMismatchException ex)
                        {
                            Debug.println("Input mismatch", ex.getMessage());
                        }
                        catch(Exception ex)
                        {
                            Debug.println("Exception in input", ex.getMessage());
                        }
                    break;
                    
                case 2:
                    printAllElementsInList();
                    break;
                   
                    
                case 3:
                    boolean isCyclePresent = checkifCycleIsPresent();
                    
                    if(isCyclePresent)
                        System.out.println("Loop detected");
                    else
                        System.out.println("Loop not detected");
                    break;
                    
                    
                case 4:
                    introduceACycleAtTheEnd();
                    break;
                    
                case 5:
                    removeCycleAtTheEnd();
                    break;
                
                case 6:
                    System.out.println("\nEnter rotation count:");
                    int countToRotate = getOptionIntValue();
                    if(countToRotate > 0)
                        rotateList(countToRotate);
                    break;
                    
                default:
            }           
        }        
        scan.close();
    }
    
    private int getOptionIntValue()
    {
        try
        {
            return scan.nextInt();
        } 
        catch(InputMismatchException ex)
        {
            Debug.println("Input mismatch", ex.getMessage());
        }
        catch(Exception ex)
        {
            Debug.println("Exception in input", ex.getMessage());
        }
        
        return -1;
    }
    
    public void insertData(long data)
    {
        if(count > maxAllocatedSize) 
        {
            System.out.println("Max size reached");
        } 
        else
        {
           if(beginNode == null)
            {
                beginNode = new Link(data);
                count++;
            }
            else
            {
                Link currentNode = beginNode;

                while(currentNode.nextLink != null && !currentNode.isVisited)
                {
                    currentNode.isVisited = true;
                    currentNode = currentNode.nextLink;
                }
                currentNode.isVisited = true;                

                Link newNode = new Link(data);
                count++;
                currentNode.nextLink = newNode;
                
                resetVisitStatus();
            } 
        }        
    }
    
    public void printAllElementsInList()
    {
        if(beginNode == null)
        {
            System.out.println("List is empty");
        }
        else
        {
            System.out.println("Following data is present:");
            Link currentNode = beginNode;        
            System.out.print(currentNode.data);

            while(currentNode.nextLink != null && !currentNode.isVisited)
            {
                currentNode.isVisited = true;
                currentNode = currentNode.nextLink;
                System.out.print("," + currentNode.data);
            }
            currentNode.isVisited = true;
            
            resetVisitStatus();
            
            System.out.println();
        }        
    }
    
    //Returns true if a cycle is present ie current node already have visited flag true
    public boolean checkifCycleIsPresent()
    {
        Link currentNode = beginNode;
        
        boolean isVisited = false;
        while(currentNode.nextLink != null)
        {
            if(!currentNode.isVisited)
            {
                currentNode.isVisited = true;                
            }
            else
            {
                isVisited = true;
                break;
            }
            currentNode = currentNode.nextLink;
        }
        
        resetVisitStatus();
        
        return isVisited;
    }
    
    public void introduceACycleAtTheEnd()//Link the node to begining
    {
        Link currentNode = beginNode;
        while(currentNode.nextLink != null && !currentNode.isVisited)
        {
            currentNode.isVisited = true;
            currentNode = currentNode.nextLink;
        }
        
        currentNode.nextLink = beginNode.nextLink;
        
        resetVisitStatus();
    }
        
    public void removeCycleAtTheEnd()
    {
        Link currentNode = beginNode;
        Link previousNode = beginNode;
        Link breakNode = null;
        while(currentNode.nextLink != null && !currentNode.isVisited)
        {
            previousNode = currentNode;
            currentNode.isVisited = true;
            currentNode = currentNode.nextLink;
        }
        
        if(currentNode.isVisited)//Break at previous node
        {
            breakNode = previousNode;
            System.out.print("Broke on node : "+ breakNode.data);
            previousNode.nextLink = null;
        }
         
        resetVisitStatus();
    }
    
    public void resetVisitStatus()
    {
        Link currentNode = beginNode;
        while(currentNode.nextLink != null && currentNode.isVisited)
        {
            currentNode.isVisited = false;
            currentNode = currentNode.nextLink;
        }
        currentNode.isVisited = false;
    }
    
    public void rotateList(int value)
    {
        Link currentNode = beginNode;
        //first move the begin node to the position
        //keep the position of next end node
        //get previous end nodee
        //get previous begin node
        //point end node next to previous begin node
        //delete the null of next end node
        int currentPosition = 0;
        int positionToBreak = count - value;
        
        if(count < value)
        {
            System.out.printf("\nrotate value should not be larger than linked list\n");    
            return;
        }
        
        Link newBgeinPosition = null;
        Link newEndNode = null;
        Link currentEndNode = null;
        
        while(currentNode.nextLink != null)
        {   
            currentPosition++;
            if(positionToBreak == currentPosition)
            {
                newBgeinPosition = currentNode.nextLink;
                newEndNode = currentNode;
            }
            currentNode = currentNode.nextLink;
        }
        
        if(currentNode.nextLink == null)
        {
            currentEndNode = currentNode;
        }
        
        newEndNode.nextLink = null;
        currentEndNode.nextLink = beginNode;
        beginNode = newBgeinPosition;
    }
}