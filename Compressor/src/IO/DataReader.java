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
public interface DataReader {
    
    public void ReadFile(String fName);
    public BitSet ExtractNBits(int startIndx, int NBits);
    public BitSet getBitSet();
    public int GetSize();
}
