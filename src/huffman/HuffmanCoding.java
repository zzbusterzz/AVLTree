/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import sun.security.ssl.Debug;

/**
 *
 * @author Student
 */
public class HuffmanCoding 
{
    //read string
    //generate encode values
    //apply encode value
    //save the new file & save data with encoded values
    
    private Scanner scan;
    
    public HuffmanCoding()
    {
        scan = new Scanner(System.in);
    }    
    
    public static void main(String[] args) 
    {
        HuffmanCoding hc = new HuffmanCoding();
        hc.EncodeString();
    }
    
    private void EncodeString()
    {
        String stringTOEncode = null;
        
        if(scan.hasNextLine())
        {
            stringTOEncode = scan.nextLine();
        }
        
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        
        String key;
        int val;
        for(int  i = 0; i < stringTOEncode.length(); i++)
        {
            key = stringTOEncode.charAt(i) + "";
            if(map.containsKey(key))
            {
                val = map.get(key);
                map.replace(key, val, val + 1);//increment instance
            } 
            else
            {
                map.put(key, 1);
            }
        }
        
        PriorityQueue<HashCode> dataQueue = new PriorityQueue<HashCode>(new IntegerComparator());
        
        for(Map.Entry<String,Integer> mp : map.entrySet())
        {
            dataQueue.add(new HashCode(mp.getKey(), mp.getValue()));
        }
        
        while(dataQueue.size() > 1)
        {
            HashCode node1 = dataQueue.remove();
            HashCode node2 = dataQueue.remove();
            
            HashCode parentNode = new HashCode(node1.ch + node2.ch  ,node1.frequency + node2.frequency);
            parentNode.leftLink = node1;
            parentNode.rightLink = node2;
            
            Debug.println("Nodes: " + node1.frequency + " : "  + node1.ch,  "Nodes: " + node2.frequency + " : "  + node2.ch);
            
            dataQueue.add(parentNode);
        }
        
        HashCode root = dataQueue.remove();
        HashMap<String, String> binaryMap = new HashMap<String, String>();
        
        //AssignCode(root, binaryMap);//recursive function
                
        //iterative traversal
        HashCode currentNode = root;
        List<HashCode> nodes = new ArrayList<HashCode>();
        
        nodes.add(currentNode);
        nodes.add(currentNode.leftLink);
        nodes.add(currentNode.rightLink);
        currentNode.leftLink.nodeAssignment = "0";
        currentNode.rightLink.nodeAssignment = "1";        
        
        int nodePointer = 1;
        while(nodePointer < nodes.size())
        {
            HashCode nodeP = nodes.get(nodePointer);
            HashCode nodeL = nodeP.leftLink;
            HashCode nodeR = nodeP.rightLink;
            
            if(nodeL != null)
            {
                nodes.add(nodeL);
                nodeL.nodeAssignment = nodeP.nodeAssignment + "0";
            }else{
                 binaryMap.put(nodeP.ch, nodeP.nodeAssignment);
            }
                
            
            if(nodeR != null)
            {
                nodes.add(nodeR);
                nodeR.nodeAssignment = nodeP.nodeAssignment + "1";
            }else
            {
                 binaryMap.put(nodeP.ch, nodeP.nodeAssignment);
            }
            
           nodePointer++;
        }
        //end of iterative  traversal
        
        
        String encodedString = "";
        String ch;
        for(int  i = 0; i < stringTOEncode.length(); i++)
        {
            ch = stringTOEncode.charAt(i)+"";
            
            if(binaryMap.containsKey(ch))
                encodedString +=  binaryMap.get(ch);
            else
            {
                encodedString = null;
                break;    
            }
        }
        
        if(encodedString != null)
        {
            System.out.println("Huffman String : " + encodedString);
        }
        else
        {
            Debug.println("Key Doesnt exist", "Exiting the program");
        }
   }
    
    //Recursive function to call
    void AssignCode(HashCode node, HashMap<String, String> binaryMap)//recursion
    {
        if(node.rightLink != null)
        {
            node.rightLink.nodeAssignment = node.nodeAssignment + "1";
            AssignCode(node.rightLink, binaryMap);
        }
        else
        {
            binaryMap.put(node.ch, node.nodeAssignment);
        }
        
        if(node.leftLink != null)
        {
            node.leftLink.nodeAssignment = node.nodeAssignment + "0";
            AssignCode(node.leftLink, binaryMap);
        }else
        {
             binaryMap.put(node.ch, node.nodeAssignment);
        }
    }
}