/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HuffmanEncoding;

import java.util.Comparator;

/**
 *
 * @author Student
 */
public class IntegerComparator implements Comparator<HashCode>
{
    public int compare(HashCode o1, HashCode o2) 
    {
        if(o1.frequency < o2.frequency)
            return o1.frequency;
        else
            return o2.frequency;
    }
}