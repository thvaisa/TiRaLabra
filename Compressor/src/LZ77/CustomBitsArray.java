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

public class CustomBitsArray {
    private int chunkSize;
    private long bits[];
    private int size;
    private int nBits;
    private int maxCapacity;
    private int reservedSpace;
    private int chunksInLong;
    private int ptr;
    
    
    public int GetSize(){
        return size;
    }
    
    public int GetChunkSize(){
        return chunkSize;
    }
    
    
    public CustomBitsArray(int chunkSize, int reserveSpace){
        chunksInLong = (int) Math.floor(Math.floor(64.0/chunkSize));
        int nlongs = (int)Math.ceil(reserveSpace/(double)chunksInLong);
        reservedSpace = reserveSpace;
        this.chunkSize = chunkSize;
        size = 0;
        ptr = 0;
        nBits = 0;
        bits = new long[nlongs];
    }

    public BitChunk getBitChunk(int indx){
        Tuple tuple = getOffset(indx);
        //System.out.println(tuple.value1);
        System.out.println(tuple.value2);
        long value = (bits[tuple.value1] >> (64-(tuple.value2+1)*chunkSize)) << 64-chunksInLong;
        return new BitChunk(value);
    }
    
    private Tuple getOffset(int indx){
        int arrIndx = (int) Math.ceil(indx/chunksInLong);
        int offset = (indx-arrIndx*chunksInLong);
        return new Tuple(arrIndx,offset);
    }
    
    public boolean equalData(int indx1, int indx2){
        Tuple tuple1 = getOffset(indx1);
        Tuple tuple2 = getOffset(indx2);
        return (bits[tuple1.value1] >> (64-(tuple1.value2+1)*chunkSize)) << 64-chunksInLong == (bits[tuple2.value1] >> (64-(tuple2.value2+1)*chunkSize)) << 64-chunksInLong;
    }
    
    public void insertData(long[] data, int nBits){
        this.nBits = nBits;
        size = (int)Math.ceil(nBits/chunkSize);
        for(int i=0; i<data.length; ++i){
            bits[ptr++] = data[i];
        }
    }
    
    
            
    private long getLongFull(byte[] b, int off) {
        return ((b[off + 7] & 0xFFL) << 0) +
               ((b[off + 6] & 0xFFL) << 8) +
               ((b[off + 5] & 0xFFL) << 16) +
               ((b[off + 4] & 0xFFL) << 24) +
               ((b[off + 3] & 0xFFL) << 32) +
               ((b[off + 2] & 0xFFL) << 40) +
               ((b[off + 1] & 0xFFL) << 48) +
               (((long) b[off]) << 56);
    }
       
    
    private long getLong(byte[] b, int off, int max) {
        long val = (((long) b[off]) << 56);
        for(int i=1;i<max;++i){
            val+=((b[off + i] & 0xFFL) << 8*(7-i));
        }
        return val;
    }
        
    //With C I would be already at home
    public void insertData(byte[] data){
        this.nBits = data.length*8;
        size = (int)Math.ceil(nBits/chunkSize);
        for(int i=0; i<data.length; i=i+8){
            if(i+8<data.length){
                bits[ptr++] = getLongFull(data,i);
            }else{
                //System.out.println(data.length-i);
                //System.out.println(Long.toBinaryString(getLong(data,i,data.length-i)));
                bits[ptr++] = getLong(data,i,data.length-i);
                //System.out.println(bits[ptr-1]);
            }
        }
    }
    
    private int count(){
        return size;
    }

}
