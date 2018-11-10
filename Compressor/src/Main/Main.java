/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import IO.DataReader;
import IO.DataWriter;
import IO.RandomData;
import IO.TestDataStorage;
import LZ77.BitChunk;
import LZ77.CustomBitsArray;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.LongBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;



/**
 *
 * @author timvaisa
 */
public class Main {
     public static void main(String[] args) {
        DataReader reader = new RandomData();
        DataWriter writer = new TestDataStorage();
        String str = "1xaxab";
       
        
        //ByteBuffer bytes = ByteBuffer.wrap(str.getBytes(Charset.forName("UTF-8")));
        
        //LongBuffer lBuffer = new LongBuffer();
        //long[] result = new long[lBuffer.capacity()];
        //lBuffer.get(result);
          byte[] bytes = str.getBytes();
          StringBuilder binary = new StringBuilder();
          for (byte b : bytes)
          {
             int val = b;
             for (int i = 0; i < 8; i++)
             {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
             }
          }
          System.out.println(binary);

       
        // you must specify a charset
        //System.out.println(bytes.position());
        
        
        CustomBitsArray bits = new CustomBitsArray(8,64);
        
        //System.out.println(Long.toBinaryString(result[0]));
        //System.out.println(Long.toBinaryString(result[1]));
        
        bits.insertData(str.getBytes());


    }
}
