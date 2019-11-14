package com.wuzhiwei.bigdataDemo.j2seDemo.fileCompress;

/**
 *
 * 创建 公共的工具类： 既然是工具类：当中的方法就都是静态的方法，可以直接调用；
 *
 *
 * **/

public class Util {


    public static byte[] int2Bytes(int i){

//        int i = 4;
        byte[] arr = new byte[4];
        arr[0] = (byte) (i & 0xff) ;
        arr[1] = (byte) (i >> 8 & 0xff) ;
        arr[2] = (byte) (i >> 16 & 0xff  );
        arr[3] = (byte) (i >> 24 & 0xff) ;
        return arr;

    }



    public static int bytes2Int(byte[] bytes){
        int i0 = bytes[0];
        int i1 = ( bytes[1] & 0xFF ) << 8;
        int i2 = ( bytes[2] & 0xFF ) << 16;
        int i3 = ( bytes[3] & 0xFF ) << 24;
        return i0 |i1 | i2| i3;

    }

}
