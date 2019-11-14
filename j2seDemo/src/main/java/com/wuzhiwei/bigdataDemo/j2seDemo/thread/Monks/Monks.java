package com.wuzhiwei.bigdataDemo.j2seDemo.thread.Monks;

import java.util.Random;

public class Monks extends Thread{

    public static int MAX = 5;

    private String name;
    private int count;
    private Box box;


    public int getCount() {
        return count;
    }

    public Monks(String name, Box box) {
//        super(name);
        this.name = name;
        this.box = box;
    }

    @Override
    public void run() {
        while (true){

            int n = box.getMontou(this);
            try {
                Thread.sleep(new Random().nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if( n == 0 ){
                return;
            }else {
                count ++;
//                System.out.println("name:"+ this.name+" has getMontous :"+this.count);
            }

        }
    }
}
