package com.wuzhiwei.bigdataDemo.j2seDemo.thread;

import java.util.LinkedList;

public class TicketPool {

    private LinkedList<Integer> pool = new LinkedList<Integer>();
    private int MAX = 100;

    public synchronized int add(Integer i){

        try {

            while (pool.size() >= MAX){

                wait();
            }

            pool.add(i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return i;

    }

    public synchronized int remove(){

        while (pool.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return pool.removeFirst();
    }






}
