/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LZ77;

/**
 *
 * @author rokka
 */
public class BitChunk {
    long data;
    
    BitChunk(long data){
        this.data = data;
    }
    
    public long data(){
        return data;
    }
    
    public boolean equals(BitChunk o) {
        return data==o.data();
    }
    
    public String toString(){
        return (char)data+"";
    }  

}
