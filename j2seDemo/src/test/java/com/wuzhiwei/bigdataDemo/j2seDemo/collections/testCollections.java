package com.wuzhiwei.bigdataDemo.j2seDemo.collections;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class testCollections {

    @Test
    public void testHashMap(){

        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(1,"tom");
        map.put(2,"tom");
        map.put(3,"tom");
        map.put(1,"tomas");



    }
}
