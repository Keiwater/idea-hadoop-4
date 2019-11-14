package com.wuzhiwei.bigdataDemo.j2seDemo.thread.Monks02;
/*
*  算法优化：
*
*   在Box发放馒头的机制中，补充相应的计数器：计算馒头，或者和尚的数量：以达到提高整体程序运行性能的 应用场景。
*
*   box：中维护：一个计数器;记录：有多少个和尚没有吃馒头
*   每次发放馒头的时候，判断剩余馒头的数量 >= 没有吃馒头的和尚数量
*
*
* */

public class App {
    public static void main(String[] args) {
        Box x= new Box();

        for(int i = 0; i<30; i++){
            new Monks("Monks"+i,x).start();
        }
    }
}
