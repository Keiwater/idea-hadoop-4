package com.wuzhiwei.bigdataDemo.j2seDemo;

import org.junit.Test;

public class testThreads {

    @Test
    public void test01(){

        new MyThreads("T1").start();
        new MyThreads("AAA2").start();


    }

    class  MyThreads extends Thread {
        private String name;

        public MyThreads(String name) {
            this.name = name;
        }

        @Override
        public void run() {

            for(int i = 0; i < 100 ;i++){

                System.out.println(name +" : " + i);
            }

        }
    }


}
