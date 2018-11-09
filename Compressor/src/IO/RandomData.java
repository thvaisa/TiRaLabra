/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;
import IO.DataReader;
import java.util.BitSet;
import java.util.Random;


/**
 *
 * @author timvaisa
 */
public class RandomData implements DataReader {
    private BitSet data;
               
    public RandomData(){
        int NUM_BYTES = 10000;
        Random rnd = new Random();
        byte[] bytes = new byte[NUM_BYTES];
        rnd.nextBytes(bytes);
        data = BitSet.valueOf(bytes);
    }
    
    @Override
    public void ReadFile(String fName) {
        return;
    }

    @Override
    public BitSet ExtractNBits(int startIndx, int NBits) {
        if(startIndx+NBits<data.size()){
            return data.get(startIndx, NBits);
        }else{
            return data.get(startIndx, data.size());
        }
    }

    @Override
    public BitSet getBitSet() {
        return data;
    }

    @Override
    public int GetSize() {
        return data.size();
    }
    
    
}
