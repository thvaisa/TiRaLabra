/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LZ77;

import IO.DataReader;
import IO.DataWriter;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;


/**
 *
 * @author timvaisa
 */
public class Compressor {
    public List<ValueStore> list;
    /**
     * @param args the command line arguments
     */
   
    public boolean compress(CustomBitsArray data){
        list = new ArrayList<>();
        
        int cursor = 0;
        int lookAheadBufferSize = 5;
        int windowSize = 10;
        int width = data.GetChunkSize();
        int N = data.GetSize(); 


        while(cursor<N){
            Tuple match = findMatch(data,cursor,lookAheadBufferSize,
                                                windowSize, width);
            
            System.out.println("Dictionary: "+match.value1);
            System.out.println("LookAhead: "+match.value2);
            cursor = cursor+match.value2+1;
            if(cursor<N){
                System.out.println("Match: "+Long.toBinaryString(data.getBitChunk(cursor).data()));
            }else{
                System.out.println("Match: "+Long.toBinaryString(data.getBitChunk(0).data()));
            }
        }
        return true;
    }
    
    
    
    

    
    
    private Tuple findMatch(
                            CustomBitsArray data, int cursor, 
                            int lookAheadSize, int windowSize,
                            int width)
    {
        
        int mSize = data.GetSize();
        
        int maxIndx = min(cursor+lookAheadSize,mSize);
        int dictMaxIndx = max(0,cursor-windowSize);
        
        int startIndx = -1; 
        
        //Find the first occurrence
        for(int j=dictMaxIndx; j<cursor; ++j){
            if(data.equalData(j,cursor)){
                startIndx=j;
                break;
            }
        }
        
        if(startIndx==-1) return new Tuple(0,0);
        
        int cnt=1;
        while(cursor+cnt<maxIndx){
            if(!data.equalData(cursor+cnt,startIndx+cnt)){
                break;
            }
            ++cnt;
        }
 
        return new Tuple((cursor-startIndx),cnt);
    }
    
}