package com.wuzhiwei.bigdataDemo.j2seDemo.thread.Monks;

public class Box {

    private int connt = 100;

    public synchronized int getMontou(Monks monk){

        if(connt == 0){
            System.out.println("name:"+ monk.getName()+" has getMontous :"+ monk.getCount());
            return 0;
        }

        int current_boxcnt = connt;
        int cnt = monk.getCount();
        if ( cnt == monk.MAX) {

            System.out.println("name:"+ monk.getName()+" has getMontous :"+ monk.getCount());
//            monk.interrupt();
            return 0;
        }



        connt --;
        if(connt > 70){

            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else {

            this.notifyAll();
        }

        return current_boxcnt;
    }

}
