/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;
import java.util.Collections;
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
    //open file
    //read file
    //generate encode values
    //apply encode value
    //save the new file & save data with encoded values
    
    //TODO: Generate datastructure with encoded values
    
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
        
        
        HashMap<String, Integer> binaryMap = new HashMap<String, Integer>();
        HashCode currentNode = root;
        
        
        List<HashCode> nodes = new ArrayList<HashCode>();
        
        nodes.add(currentNode);
        nodes.add(currentNode.leftLink);
        nodes.add(currentNode.rightLink);
        currentNode.rightLink.nodeAssignment = "1";
        
        
        int nodePointer = 1;
        while(nodePointer < nodes.size())
        {
           HashCode nodeL = nodes.get(nodePointer).leftLink;
           HashCode nodeR = nodes.get(nodePointer).rightLink;
            
            if(nodeL != null)
                nodes.add(nodeL);
            
            if(nodeR != null)
                nodes.add(nodeR);
            
           nodePointer++;
        }
        
        
        for(int i = 3; i < nodes.size(); i++)
        {
            HashCode cd = nodes.get(i);
            if( (i-1)%2 == 0)
                cd.nodeAssignment = nodes.get(i%2).nodeAssignment + "1";
            else
                cd.nodeAssignment = nodes.get(i%2).nodeAssignment + "0";
            
            if(cd.leftLink == null && cd.rightLink == null)
            {
                binaryMap.put(cd.ch, Integer.parseInt(cd.nodeAssignment));
            }//Add to binary map if element is leaf
        }
        
        String encodedString = "";
        String ch;
        for(int  i = 0; i < stringTOEncode.length(); i++)
        {
            ch = stringTOEncode.charAt(i)+"";
            
            if(binaryMap.containsKey(ch))
                encodedString +=  binaryMap.get(ch);
            else{
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
}
