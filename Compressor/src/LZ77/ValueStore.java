/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LZ77;

import java.util.BitSet;

/**
 *
 * @author rokka
 */
public class ValueStore {
    public int position;
    public int length;
    public BitSet bits;
    
    public ValueStore(int position, int length, BitSet bits){
        this.position = position;
        this.length = length;
        this.bits = bits;
    }
    
}
