package com.wuzhiwei.bigdataDemo.j2seDemo.thread.Monks;

public class App {
    public static void main(String[] args) {
        Box x= new Box();

        for(int i = 0; i<30; i++){
            new Monks("Monks"+i,x).start();
        }
    }
}
