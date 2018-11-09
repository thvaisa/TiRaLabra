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
   
    public boolean compress(DataReader reader, DataWriter writer){
        list = new ArrayList<>();
        
        
        int cursor = 0;
        int lookAheadBufferSize = 5;
        int windowSize = 10;
        int width = 2;
        BitSet data = reader.getBitSet();
        int N = data.size();      

        while(cursor<N){
            Tuple match = findMatch(data,cursor,lookAheadBufferSize,
                                                windowSize, width);
            int jump = match.value2*width;
            if(cursor+jump+width>=N){
                jump=N-cursor;
            }
            
            BitSet nextBits = data.get(cursor+jump+width,cursor+jump+width*2);
            list.add(new ValueStore(match.value1,jump,nextBits));
            
            int maxIndx = min(cursor+lookAheadBufferSize,data.size());
            int dictMaxIndx = max(0,cursor-windowSize);
            
            System.out.println("Dictionary: "+data.get(dictMaxIndx,cursor).toString());
            System.out.println("LookAhead: "+data.get(cursor,maxIndx).toString());
            System.out.println("Match: "+Integer.toString(match.value1)+","+Integer.toString(match.value2)+","+match.)
            
            cursor = cursor+jump+width;
        }
        return true;
    }
    
    
    
    
    public boolean decompress(DataReader reader, DataWriter writer){
        
        
        
        
        return true;
    }
    
    
    
    //REPLACE with better algorithm
    private boolean equal(BitSet data,int indx1,int indx2, int width){
        for(int i=0;i<width;++i){
            if(data.get(indx1+i)!=data.get(indx2+i)) return false;
        }
        return true;
    }
    
    
    
    
    
    private Tuple findMatch(
                                        BitSet data, int cursor, 
                                        int lookAheadSize, int windowSize,
                                        int width)
    {
        
        
        int maxIndx = min(cursor+lookAheadSize,data.size());
        int dictMaxIndx = max(0,cursor-windowSize);
        
        int startIndx = -1; 
        
        for(int j=dictMaxIndx; j<cursor; j=j+width){
            if(equal(data,cursor,j,width)){
                startIndx=j;
                break;
            }
        }
        
        if(startIndx==-1) return new Tuple(0,0);
        
        int cnt=1;
        while(cursor+cnt*width<maxIndx){
            if(!equal(data,cursor+cnt*width,startIndx+cnt*width,width)){
                break;
            }
            ++cnt;
        }
 
        return new Tuple((cursor-startIndx)/width,cnt);
    }
    
}
