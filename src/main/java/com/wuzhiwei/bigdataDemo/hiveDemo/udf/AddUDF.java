package com.wuzhiwei.bigdataDemo.hiveDemo.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.hive.ql.exec.UDF;

public class AddUDF extends UDF {

    public String evaluate(){

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");

        return sdf.format(dt);

    }



    //测试的main方法
    public static void main(String[] args) throws Exception{
        AddUDF fes = new AddUDF();
        String date_str = "20150101";
        System.out.println(fes.evaluate());
//        String date_str1 = "20160101";
//        String result = fes.evaluate(date_str1);
//        System.out.println("result is:" + result);
//        double date_dou = 20160101;
//        int result_dou = fes.evaluate(date_dou,"count");
//        System.out.println(result_dou);
//        System.out.println(date_dou);
    }


}
