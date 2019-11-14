package com.wuzhiwei.bigdataDemo.j2seDemo.thread;

public class Consumer extends Thread {
    private String name;
    private TicketPool pool;

    public Consumer(String name,TicketPool pool) {
        this.name = name;
        this.pool = pool;
    }

    @Override
    public void run(){
        while (true){

            int n = pool.remove();

            System.out.println(name + " consumed : "+ n );

        }
    }
}
