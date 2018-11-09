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
public interface DataWriter {
    public void OpenFileWriting(String fName);
    public boolean SaveBits(BitSet set);
    public void SetBitSet(BitSet data);
}
