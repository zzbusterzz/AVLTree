/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RedBlackTree;

import java.util.InputMismatchException;
import java.util.Scanner;
import sun.security.ssl.Debug;

/**
 *
 * @author 1
 */
public class RedBlackTree
{
    protected class Link
    {
        public long data;
        public int node_color = 0;//Black
        
        public Link nextLinkLeft;
        public Link nextLinkRight;
        
        public Link parent;
        
        public boolean isVisited = false;
        public Link(long data)
        {
            this.data = data;
        }
    }    
    
    private Link beginNode = null;
    private Scanner scan = null;
    private int option = 0;
    
    public RedBlackTree()
    {
        scan = new Scanner(System.in);
        option = 0;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        RedBlackTree list = new RedBlackTree();
        list.DisplayOptions();
    }
    
    private void DisplayOptions()
    {
        while(scan.hasNextInt())
        {
            System.out.println("Select a option: \n1:Insert(-1 to break out) \n2:Print Tree \n3:Delete nodes \n100:Exit");
            option = scan.nextInt();        
            switch(option)
            {
                case 1:
                        try
                        {
                            while(scan.hasNextLong())
                                insertData(scan.nextLong());
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
                    Long value = 0L;
                    System.out.println("Enter number to delete");
                    try
                    {
                        value = scan.nextLong();
                        Link node = getNodeLocationWithNumber(beginNode , value);
                        
                        if(node == null)
                            System.out.println("Number does not exist in tree");
                        else
                            DeleteANode(node, value);
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
                    
                default:
            }           
        }        
        scan.close();
    }
    
    private Link getNodeLocationWithNumber(Link node, long number)
    {
        if(node.data == number)
            return node;
        else
        {
            if(node.data > number)
            {
                if(node.nextLinkLeft != null)
                {
                    return getNodeLocationWithNumber(node.nextLinkLeft, number);
                }else return null;
            } 
            else
            {
                if(node.nextLinkRight != null)
                {
                    return getNodeLocationWithNumber(node.nextLinkRight, number);
                }else return null;
            }
        }
    }
    
    
    private void insertData(long data)
    {       
        if(beginNode == null)
         {
             beginNode = new Link(data);
             beginNode.parent = null;
         }
         else
         {
            InsertInTree(beginNode, data);
         }   
    }
    
    private void InsertInTree(Link currentNode, long data)
    {
        if(currentNode.data > data)//insert into left
        {
            if(currentNode.nextLinkLeft == null)
            {
                currentNode.nextLinkLeft = new Link(data);
                currentNode.nextLinkLeft.parent = currentNode;
            } else
                InsertInTree(currentNode.nextLinkLeft, data);
        }
        else if(currentNode.data < data)//insert into right
        {
            if(currentNode.nextLinkRight == null)
            {
                currentNode.nextLinkRight = new Link(data);
                currentNode.nextLinkRight.parent = currentNode;
            } else
                InsertInTree(currentNode.nextLinkRight, data);
        }
    }
    
    public void DeleteANode(Link currentNode, long data)
    {
        if(currentNode.data == data)
        {
            if(currentNode.nextLinkLeft == null && currentNode.nextLinkRight == null)//if leaf node directly delete the value
            {
                if(currentNode.parent != null)
                {
                    if(isParentsLeft(currentNode))
                        currentNode.parent.nextLinkLeft = null;
                    else 
                        currentNode.parent.nextLinkRight = null;
                } 
                else//This is root node
                {
                    beginNode = null;
                }
            } 
            else//get the child count
            {
                if((currentNode.nextLinkLeft != null && currentNode.nextLinkRight != null) || (currentNode.nextLinkLeft != null && currentNode.nextLinkRight == null) )//if both child are present
                {
                    //we will get max no from left child
                    Link max = getMaxNumberFromTree(currentNode.nextLinkLeft);
                    if(isParentsLeft(currentNode))
                    {
                        if(currentNode.parent != null)
                            currentNode.parent.nextLinkLeft = max;//assign child as max node
                    }
                    else 
                    {
                        if(currentNode.parent != null)
                            currentNode.parent.nextLinkRight = max;
                    }
                    
                    if(isParentsLeft(max))
                        max.parent.nextLinkLeft = null;
                    else
                        max.parent.nextLinkRight = null;
                    
                                        
                    max.nextLinkLeft = currentNode.nextLinkLeft;//assign child of max node left from current node
                    max.nextLinkRight = currentNode.nextLinkRight;//assign child of max node right from current node

                    if(max.nextLinkLeft != null)//Reassign children parent left
                        max.nextLinkLeft.parent = max;

                    if(max.nextLinkRight != null)//reassign children parent right
                        max.nextLinkRight.parent = max;
                    
                    max.parent = currentNode.parent;
                    
                    if(beginNode == currentNode)
                        beginNode = max;
                    
                    currentNode.nextLinkLeft = null;
                    currentNode.nextLinkRight = null;
                    
                    currentNode = null;
                } 
                else//only one child is present get the present child and move to its parent
                {
                    if(currentNode.parent == null)//if we delete root and only right child are present then move the right child to root
                    {
                        beginNode = currentNode.nextLinkRight;
                        beginNode.parent = null;
                    }
                    else
                    {
                        if(currentNode.nextLinkLeft != null)
                        {
                            if(isParentsLeft(currentNode))
                            
                                currentNode.parent.nextLinkLeft = currentNode.nextLinkLeft;
                            else 
                                currentNode.parent.nextLinkRight = currentNode.nextLinkLeft;
                            
                                currentNode.nextLinkLeft = currentNode.parent;
                        } 
                        else
                        {
                            if(isParentsLeft(currentNode))
                                currentNode.parent.nextLinkLeft = currentNode.nextLinkRight;
                            else 
                                currentNode.parent.nextLinkRight = currentNode.nextLinkRight;
                            
                            currentNode.nextLinkRight.parent = currentNode.parent; 
                        }
                    }
                }
            }
            
            printAllElementsInList();
        }
    }
    
    private Link getMaxNumberFromTree(Link currentNode)
    {
        Link currentMaxNode = getMaxFromNodes(currentNode);
        
        Link CurrentIteratedNode = null;
        while(currentMaxNode.nextLinkLeft != null || currentMaxNode.nextLinkRight != null)
        {   
            CurrentIteratedNode = getMaxFromNodes(currentMaxNode);
            
            if(CurrentIteratedNode == currentMaxNode)
                break;
        }
        
        return currentMaxNode;
    }
    
    private Link getMaxFromNodes(Link currentNode)
    {
        if( currentNode.nextLinkRight != null )
        {
            if( currentNode.data <  currentNode.nextLinkRight.data )
                return getMaxFromNodes(currentNode.nextLinkRight);
        } 
        else if( currentNode.nextLinkLeft != null )
        {
            if( currentNode.data <  currentNode.nextLinkLeft.data )
                return getMaxFromNodes(currentNode.nextLinkLeft);
        }
        return currentNode;
    }
    
    private boolean isParentsLeft(Link currentNode)
    {
        if(currentNode.parent == null)
            return true;
        
        if(currentNode.parent.nextLinkLeft != null && currentNode.parent.nextLinkLeft == currentNode)
            return true;
        else
            return false;
    }
    
    private void printAllElementsInList()
    {
        if(beginNode == null)
        {
            System.out.println("List is empty");
        }
        else
        {
            System.out.println("Following data is present:");
            
            //inorder traversal
            //left, root and right
            InorderTraversal(beginNode);
            
            System.out.println();
        }        
    }
    
    private void InorderTraversal(Link node )
    {
        if(node == null)
            return;
        
        InorderTraversal(node.nextLinkLeft);//Print left element
        
        System.out.print(node.data + " ");//print root
        
        InorderTraversal(node.nextLinkRight);//Print right element
    }
}

