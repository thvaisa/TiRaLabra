/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.util.BitSet;

/**
 *
 * @author timvaisa
 */
public class TestDataStorage implements DataWriter{
    private BitSet data;
    
    public TestDataStorage(){
        int NBits = 10000;
        data = new BitSet(10000);
    }
    
    @Override
    public void OpenFileWriting(String fName) {
        
    }

    @Override
    public boolean SaveBits(BitSet set) {
        data = set;
        return true;
    }

    @Override
    public void SetBitSet(BitSet data) {
        this.data = data;
    }
    
}
