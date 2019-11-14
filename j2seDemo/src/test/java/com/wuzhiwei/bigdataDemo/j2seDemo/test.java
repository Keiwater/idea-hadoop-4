package com.wuzhiwei.bigdataDemo.j2seDemo;

import org.junit.Test;

import java.io.File;
@SuppressWarnings("all")
public class test {

    @Test
    public void test02(){

        byte[] arr =  int2Bytes(234);
//        System.out.println(arr[0]+arr[1]+arr[2]+arr[3]);

        String s = null;
        s.toCharArray();
//        System.out.println("yyyyyyyup");
        System.out.println(bytes2Int(arr));
    }



    public byte[] int2Bytes(int i){

//        int i = 4;
        byte[] arr = new byte[4];
        arr[0] = (byte) i ;
        arr[1] = (byte) (i >> 8) ;
        arr[2] = (byte) (i >> 16 );
        arr[3] = (byte) (i >> 24 ) ;
        return arr;

    }



    public int bytes2Int(byte[] bytes){
//        int i0 = bytes[0];
        int i0 = (bytes[0] & 0xFF);
        int i1 = ( bytes[1] & 0xFF ) << 8;
        int i2 = ( bytes[2] & 0xFF ) << 16;
        int i3 = ( bytes[3] & 0xFF ) << 24;
        return i0 |i1 | i2| i3;

    }

    @Test
    public void test2(){
        System.out.println();


    }


    @Test
    public void testFileName(){

        File f = new File("aa.jpg");

    }
}
