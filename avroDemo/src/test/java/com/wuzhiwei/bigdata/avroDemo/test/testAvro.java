package com.wuzhiwei.bigdata.avroDemo.test;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;
import tutorialspoint.com.test;

import java.io.File;

public class testAvro {

    /*
    *    根据： 从 avro-tools工具自动输出的java包导入的内容（schema），来对 模拟对象的数据实例进行串行标准化 ，
    *    并 将串行化的结果(数据)写入avro文件（"result.avro"）当中去；
    *    1、先定义模式文件；即： 先编译：先生成类
    *    2、导入：然后对数据进行串行化；
     */

    @Test
    public void write() throws Exception {

        SpecificDatumWriter<test> testDataWriter = new SpecificDatumWriter<test>(test.class);

        DataFileWriter<test> testWriter = new DataFileWriter<test>(testDataWriter);

        test t01 = new test("Tomas",19);

        testWriter.create(t01.getSchema(), new File("result.avro"));

        testWriter.append(t01);
        testWriter.append(t01);
        testWriter.append(t01);
        testWriter.append(t01);
//        testWriter.append();
//        testWriter.append();

        testWriter.close();
    }


    @Test
    public void readdata() throws Exception {
        SpecificDatumReader testDtreader = new SpecificDatumReader<test>(test.class);
        DataFileReader<test> testFileReader = new DataFileReader<test>(new File("result.avro"),testDtreader);

        while (testFileReader.hasNext()){
//            testFileReader.next().getName();

            System.out.println(testFileReader.next().getName());
        }
    }


    /**
     * 串行、 反串行
     * 直接使用avro api：和  新创建的  schema文件；
     * 来实现:
     *      数据串行化写入  avro文件
     *
     * */

    @Test
    public void writeInSchema() throws Exception{
        Schema schema = new Schema.Parser().parse(new File("E:\\WorkSpaces\\IdeaProjects\\hiveDemo01\\avroDemo\\src\\main\\java\\tutorialspoint\\com\\test.avsc"));
        //这 一句： 就相当于： 定义了一个 test或者 emp 类
        GenericRecord e1 = new GenericData.Record(schema);

        e1.put("Name","airfric");
        e1.put("Id",44444444);

        SpecificDatumWriter<GenericRecord> sDataWriter = new SpecificDatumWriter<GenericRecord>(schema);

        DataFileWriter<GenericRecord> empFileWriter = new DataFileWriter<GenericRecord>(sDataWriter);
        empFileWriter.create(schema, new File("testApiResult.avro"));

        empFileWriter.append(e1);
        empFileWriter.append(e1);
        empFileWriter.append(e1);
        empFileWriter.append(e1);

        empFileWriter.close();
    }


    @Test
    public void read() throws Exception {
        Schema schema = new Schema.Parser().parse(new File("E:\\WorkSpaces\\IdeaProjects\\hiveDemo01\\avroDemo\\src\\main\\java\\tutorialspoint\\com\\test.avsc"));

        GenericRecord e1 = new GenericData.Record(schema);
        SpecificDatumReader<GenericRecord> datumReader = new SpecificDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(new File("E:\\WorkSpaces\\IdeaProjects\\hiveDemo01\\avroDemo\\testApiResult.avro"),datumReader);

        GenericRecord emp = null;
        while (dataFileReader.hasNext()){

            emp = dataFileReader.next(emp);
            System.out.println(emp);

            System.out.println("name is : "+emp.get("Name"));

        }

        System.out.println("Say hello");

    }

}
