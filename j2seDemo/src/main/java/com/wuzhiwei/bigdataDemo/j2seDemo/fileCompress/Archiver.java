package com.wuzhiwei.bigdataDemo.j2seDemo.fileCompress;

import java.io.*;

/**
 *  手动实现  归档，解归档
 *
 * */

public class Archiver {
    public static void main(String[] args) throws Exception {

        FileOutputStream fos = new FileOutputStream("out.xar");

        fos.write(addFile("E:\\projectsTest\\javaTest\\archiver\\4lunkuo.png"));
        fos.write(addFile("E:\\projectsTest\\javaTest\\archiver\\id_ocr.py"));
        fos.write(addFile("E:\\projectsTest\\javaTest\\archiver\\合同提起身份证和账号.csv"));

        fos.close();


    }


    /**
     *  path  文件存放路径；
     *
     *
     */

    public static byte[] addFile(String Filepath) throws Exception {
        //文件
        File f = new File(Filepath);
        //文件长度
        int len =(int)f.length();

        //文件名称
        String fname = f.getName();
        byte[] fnameBytes = fname.getBytes();
        //写入文件名称长度
        byte[] fnamLenArr = Util.int2Bytes(fnameBytes.length);

        //初始化总数组
        int total = 4 + fname.getBytes().length + 4 + len;
        byte[] bytes = new byte[total];

        // 1、前 4个位置，写入文件名称的长度
        System.arraycopy(fnamLenArr,0,bytes,0,4);
        // 2、写入文件名称本身

        System.arraycopy(fnameBytes,0,bytes,4,fnameBytes.length);
        // 3、写入文件内容的长度

        byte[] fcontentLenArr = Util.int2Bytes(len);
        System.arraycopy(fcontentLenArr,0,bytes,4+fnameBytes.length,4);
        /**
         * 4、写入文件内容, 将整个文件内容，读入到buffer数组当中去，然后将buffer当中的内容，吐出来
         *  读取数据文件内容到，数组当中去
         */
        FileInputStream fi = new FileInputStream(f);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buf = new byte[1024];
        int len01 = 0;
        while ((len01 = fi.read(buf))!= -1){

            baos.write(buf,0,len01);
        }

        fi.close();
        byte[] fcontentArr = baos.toByteArray();

        System.arraycopy(fcontentArr,0,bytes,4+fnameBytes.length+4,fcontentArr.length);

        return bytes;
    }


}
