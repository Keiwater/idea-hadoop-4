package com.wuzhiwei.bigdataDemo.j2seDemo;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class zipStreamTest {


    public static void main(String[] args) throws Exception {

        FileOutputStream fos = new FileOutputStream("E:\\projectsTest\\javaTest\\zipStream\\xxx.xar");

        ZipOutputStream zos = new ZipOutputStream(fos);

        String[] pths = {
          "E:\\projectsTest\\javaTest\\zipStream\\id_ocr.py",
          "E:\\projectsTest\\javaTest\\zipStream\\2.csv",
          "E:\\projectsTest\\javaTest\\zipStream\\3.png"

        };

        for(String s : pths){
            addFiles(zos,s);
        }
        zos.close();
        fos.close();
        System.out.println("it is over!!!!");


    }

    public static void addFiles (ZipOutputStream zos, String path) throws Exception{

        File f = new File(path);

        zos.putNextEntry(new ZipEntry(f.getName()));

        FileInputStream fis = new FileInputStream(f);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        fis.close();

        zos.write(bytes);
        // 关闭当前的压缩条目
        zos.closeEntry();

    }


    @Test
    public void unzip() throws Exception {

        FileInputStream fis = new FileInputStream("E:\\projectsTest\\javaTest\\zipStream\\xxx.xar");
        ZipInputStream zis = new ZipInputStream(fis);


        ZipEntry entry = null;

        byte[] bytes = new byte[1024];
        int len = 0;

        while ((entry=zis.getNextEntry())!=null){
            String name = entry.getName();
            FileOutputStream fos = new FileOutputStream("E:\\projectsTest\\javaTest\\zipStream\\unzip\\"+name);
            while ((len = zis.read(bytes))!=-1){
                fos.write(bytes,0,len);
            }
            fos.close();
        }

        zis.close();
        fis.close();

    }


}
