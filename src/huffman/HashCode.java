/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author Student
 */
 public class HashCode
{
    public int frequency;
    public String ch;
    public HashCode leftLink;
    public HashCode rightLink;
    
    public String nodeAssignment = "0";
    
    public HashCode(String ch,int frequency)
    {
        this.ch = ch;
        this.frequency = frequency;
    }
}
