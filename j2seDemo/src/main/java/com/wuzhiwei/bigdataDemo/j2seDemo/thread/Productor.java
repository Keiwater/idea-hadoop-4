package com.wuzhiwei.bigdataDemo.j2seDemo.thread;

public class Productor extends Thread{

    private String name;
    private TicketPool pool;
    private int index = 0;

    public Productor(String name,TicketPool pool) {
        this.name = name;
        this.pool = pool;
    }

    @Override
    public void run(){
        while (true){
            int n = pool.add(index++);
            System.out.println(name + " produced :"+ n);
        }
    }

}
