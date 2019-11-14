package com.wuzhiwei.bigdataDemo.j2seDemo.thread;

public class App {

    public static void main(String[] args) {

        TicketPool pool = new TicketPool();
        Productor p1 = new Productor("Tom",pool);
        Productor p2 = new Productor("Tomas",pool);
        Consumer c1 = new Consumer("C1",pool);
        Consumer c2 = new Consumer("C2",pool);

        p1.start();
//        p2.start();
        c1.start();
        c2.start();


    }
}
