/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mWayTree;

/**
 *
 * @author 1
 */
public class MWayNode 
{
    int[] data;
    MWayNode[] locationPointer;
    
    public MWayNode(int order)
    {
        locationPointer = new MWayNode[order];
        data = new int[order];
    }
}
