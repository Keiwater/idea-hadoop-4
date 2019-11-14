package com.wuzhiwei.bigdataDemo.Test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class testUDF {
    @Test
    public void test01(){


        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println(sdf.format(dt));


    }

}
