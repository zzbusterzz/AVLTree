/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltrees;

import java.util.InputMismatchException;
import java.util.Scanner;
import sun.security.util.Debug;

/**
 *
 * @author Student
 */
public class AVLTrees {

   protected class Link
    {
        public long data;
        
        public Link nextLinkLeft;
        public Link nextLinkRight;
        
        public Link parent;
        
        public int height;
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
    private int maxAllocatedSize = 500;
    
    public AVLTrees()
    {
        scan = new Scanner(System.in);
        option = 0;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        AVLTrees list = new AVLTrees();
        list.DisplayOptions();
    }
    
    private void DisplayOptions()
    {
        while(option != 100)
        {
            System.out.println("Select a option: \n1:Insert(Non int to break out) \n2:Print Tree \n3:Delete nodes \n100 to break:Exit");
            option = scan.nextInt();           
            switch(option)
            {
                case 1:
                        try
                        {
                            Long value = 0L;
                            while(scan.hasNextInt())
                            {
                                value = scan.nextLong();
                                if(value != -1)
                                    insertData(value);
                            }
                            scan.next();
                        }
                        catch(InputMismatchException ex)
                        {
                            Debug.println("Input mismatch", ex.getMessage());
                            scan.next();
                        }
                        catch(Exception ex)
                        {
                            Debug.println("Exception in input", ex.getMessage());
                            scan.next();
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
                        
                        scan.next();
                    }
                    catch(InputMismatchException ex)
                    {
                        Debug.println("Input mismatch", ex.getMessage());
                        scan.next();
                    }
                    catch(Exception ex)
                    {
                        Debug.println("Exception in input", ex.getMessage());
                        scan.next();
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
        if(count > maxAllocatedSize) 
        {
            System.out.println("Max size reached");
        } 
        else
        {
           if(beginNode == null)
            {
                beginNode = new Link(data);
                beginNode.parent = null;
                count++;
            }
            else
            {
               InsertInTree(beginNode, data);
            } 
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
                count++;
            } else
                InsertInTree(currentNode.nextLinkLeft, data);
        }
        else if(currentNode.data < data)//insert into right
        {
            if(currentNode.nextLinkRight == null)
            {
                currentNode.nextLinkRight = new Link(data);
                currentNode.nextLinkRight.parent = currentNode;
                count++;
            } else
                InsertInTree(currentNode.nextLinkRight, data);
        }
        
        BalanceTree();
    } 
    
    public void BalanceTree()
    {
        Height(beginNode);
        if(brokenNode != null)
            Rotate();
        
        while(brokenNode != null)
        {
            Height(beginNode);
            if(brokenNode != null)
                Rotate();
        }
    }
    
    void Rotate()
    {
        //Since rotation comes on values with either one of child being null but not both
        boolean isL1 = false;
        boolean isR1 = false;
        boolean isL2 = false;
        boolean isR2 = false;
        
        if(brokenNode.nextLinkLeft != null && brokenNode.nextLinkRight != null)
        {
            //Get max height of left or right node
            if(brokenNode.nextLinkLeft.height > brokenNode.nextLinkRight.height)
            {
                isL1 = true;
                
                if(brokenNode.nextLinkLeft.nextLinkLeft != null && brokenNode.nextLinkLeft.nextLinkRight != null)
                {
                    if(brokenNode.nextLinkLeft.nextLinkLeft.height > brokenNode.nextLinkLeft.nextLinkRight.height)//LR Present
                    {
                        isL2 = true;//Left Navigate
                    } else
                    {
                        isR2 = true;//Right Navigate
                    }
                } 
                else
                {
                    if(brokenNode.nextLinkLeft.nextLinkLeft == null)
                    {
                        isR2 = true;//Only right is present
                    } else
                    {
                        isL2 = true;//Only left is present
                    }
                }
            } 
            else
            {
                if(brokenNode.nextLinkRight.nextLinkLeft != null && brokenNode.nextLinkRight.nextLinkRight != null)
                {
                    if(brokenNode.nextLinkRight.nextLinkLeft.height > brokenNode.nextLinkRight.nextLinkRight.height)//LR Present
                    {
                        isL2 = true;//Left Navigate
                    } else
                    {
                        isR2 = true;//Right Navigate
                    }
                } 
                else
                {
                    if(brokenNode.nextLinkRight.nextLinkLeft == null)
                    {
                        isR2 = true;//Only right is present
                    } else
                    {
                        isL2 = true;//Only left is present
                    }
                }
            }
        } 
        else
        {
            if(brokenNode.nextLinkLeft == null)
            {
                isR1 = true;
                
                if(brokenNode.nextLinkLeft.nextLinkLeft != null && brokenNode.nextLinkLeft.nextLinkRight != null)
                {
                    if(brokenNode.nextLinkLeft.nextLinkLeft.height > brokenNode.nextLinkLeft.nextLinkRight.height)//LR Present
                    {
                        isL2 = true;//Left Navigate
                    } else
                    {
                        isR2 = true;//Right Navigate
                    }
                } 
                else
                {
                    if(brokenNode.nextLinkLeft.nextLinkLeft == null)
                    {
                        isR2 = true;//Only right is present
                    } else
                    {
                        isL2 = true;//Only left is present
                    }
                }
            } else
            {
                isL1 = true;
                
                
                if(brokenNode.nextLinkRight.nextLinkLeft != null && brokenNode.nextLinkRight.nextLinkRight != null)
                {
                    if(brokenNode.nextLinkRight.nextLinkLeft.height > brokenNode.nextLinkRight.nextLinkRight.height)//LR Present
                    {
                        isL2 = true;//Left Navigate
                    } else
                    {
                        isR2 = true;//Right Navigate
                    }
                } 
                else
                {
                    if(brokenNode.nextLinkRight.nextLinkLeft == null)
                    {
                        isR2 = true;//Only right is present
                    } else
                    {
                        isL2 = true;//Only left is present
                    }
                }
            }
        }
        
        Debug.println("L1 L2 R1 R2", isL1 + " : " + isL2 + " : " + isR1 + " : " + isR2 );
        
        //if(brokenNode.nextLinkLeft != null && brokenNode.nextLinkLeft.nextLinkLeft != null)//LL Rotation
        if(isL1 && isL2)
        {
            Debug.println("LL Rotation on " + brokenNode.data, null);
            
            Link Node1 = brokenNode;
            Link Node2 = Node1.nextLinkLeft;
            Link B = Node2.nextLinkRight;
           
            if(Node1 != beginNode)
            {
                if(isParentsLeft(Node1))
                {
                    Node1.parent.nextLinkLeft = Node2;

                } else
                {
                    Node1.parent.nextLinkRight = Node2;
                }
            } else
            {
                beginNode = Node2;
            }
            
            
            Node2.parent = Node1.parent;//Move 2 to root                
            Node1.nextLinkLeft = B;
            Node1.parent = Node2;//Move 1 to right
        } 
        else if(isR1 && isR2) 
//if(brokenNode.nextLinkRight != null && brokenNode.nextLinkRight.nextLinkRight != null )//RR Rotation
        {
            Debug.println("RR Rotation on "+ brokenNode.data, null);
            
            Link Node1 = brokenNode;
            Link Node2 = Node1.nextLinkRight;
            Link B = Node2.nextLinkLeft;
           
            if(Node1 != beginNode)
            {
                if(isParentsLeft(Node1))
                {
                    Node1.parent.nextLinkLeft = Node2;               
                } 
                else
                {
                    Node1.parent.nextLinkRight = Node2;
                }
            } else{
                beginNode = Node2;
            }
            
            Node2.parent = Node1.parent;//Move 2 to root                
            Node1.nextLinkRight = B;
            Node1.parent = Node2;//Move 1 to left
            
        }
        else if(isR1 && isL2)
            //if(brokenNode.nextLinkRight != null && brokenNode.nextLinkRight.nextLinkLeft != null  )//RL Rotation
        {
            Debug.println("RL Rotation on "+ brokenNode.data, null);
            
            Link Node1 = brokenNode;
            Link Node2 = Node1.nextLinkRight;
            Link Node3 = Node2.nextLinkLeft;
            
            Link A = Node1.nextLinkLeft;
            Link B = Node2.nextLinkRight;
            Link C = Node3.nextLinkLeft;
            Link D = Node3.nextLinkRight;
                    
            
            if(Node1 != beginNode)
            {
                if(isParentsLeft(Node1))
                {
                    Node1.parent.nextLinkLeft = Node3;

                } else
                {
                    Node1.parent.nextLinkRight = Node3;
                
                }
            } else{
                beginNode = Node3;
            }
            
            Node3.parent = Node1.parent;//Move 3 to root
            
            Node3.nextLinkLeft = Node1;//1 Left of root
            Node1.parent = Node3;
            
            Node3.nextLinkRight = Node2;//2 Right of root
            Node2.parent = Node3;
            
            
            Node1.nextLinkLeft = A;
            if(A != null)
                A.parent = Node1;
            
            Node1.nextLinkRight = C;
            if(C != null)
                C.parent = Node1;
            
            Node2.nextLinkLeft = D;
            if(D != null)
                D.parent = Node2;
            
            Node2.nextLinkRight = B;
            if(B != null)
                B.parent = Node2;
            
        }
        else if(isL1 && isR2)
            //if(brokenNode.nextLinkLeft != null && brokenNode.nextLinkLeft.nextLinkRight != null )//LR Rotation
        {
            Debug.println("LR Rotation on "+ brokenNode.data, null);
            
            Link Node1 = brokenNode;
            Link Node2 = Node1.nextLinkLeft;
            Link Node3 = Node2.nextLinkRight;
            
            Link A = Node1.nextLinkRight;
            Link B = Node2.nextLinkLeft;
            Link C = Node3.nextLinkLeft;
            Link D = Node3.nextLinkRight;
                    
            
            if(Node1 != beginNode)
            {
                if(isParentsLeft(Node1))
                {
                    Node1.parent.nextLinkLeft = Node3;

                } else
                {
                    Node1.parent.nextLinkRight = Node3;
                
                }
            } else{
                beginNode = Node3;
            }
            
            Node3.parent = Node1.parent;//Move 3 to root
            
            Node3.nextLinkLeft = Node2;//2 Left of root
            Node2.parent = Node3;
            
            Node3.nextLinkRight = Node1;//1 Right of root
            Node1.parent = Node3;
            
            
            Node1.nextLinkRight = A;                
            if(A != null)
                A.parent = Node1;
           
            Node1.nextLinkLeft = D;
            if(D != null)
                D.parent = Node1;
           
            Node2.nextLinkRight = C;            
            if(C != null)
                C.parent = Node2; 
            
            Node2.nextLinkLeft = B;
            if(B != null)
                B.parent = Node2;
        }
        
        brokenNode = null;//Update height at the end of the call
        Height(beginNode);
    }
    
    Link brokenNode = null;
    public int Height(Link root)
    {
        if(root == null)
        {
            return 0;
        }
        else
        {
            int left = Height(root.nextLinkLeft);
            int right = Height(root.nextLinkRight);
            
            root.height = Math.max(left, right) + 1;
            int balance = (left+ 1) - (right + 1) ;
            
            if((balance < -1 || balance > 1) && brokenNode == null)
                brokenNode = root;
            
            return root.height;
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
