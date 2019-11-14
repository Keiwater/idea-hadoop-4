package com.wuzhiwei.bigdataDemo.zktest;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.List;

public class TestZK {

    @Test
    public void ls() throws Exception{

        ZooKeeper zk = new ZooKeeper("localhost:2181",5000, null);

        List<String> childrens = zk.getChildren("/", null);
        for(String s: childrens){

            System.out.println(s);
        }
    }

    @Test
    public void lsAll() throws Exception{
        ls("/");
    }

    /**
     *   列出指定path 下的孩子
     *
     */
    public void ls(String path) throws Exception{

        System.out.println(path);
        ZooKeeper zk = new ZooKeeper("localhost:2181",5000, null);

        List<String> childrens = zk.getChildren(path, null);

        if(childrens.isEmpty() || childrens == null){
            return;
        }
        for(String s: childrens){

            if( path.equals("/") ){
                ls(path + s);
            }else
                ls(path + "/" + s);
        }
    }

    /**
     * 设置数据
     *
     */
    @Test
    public void setData() throws Exception{

        ZooKeeper zk = new ZooKeeper("localhost:2181",5000, null);
        zk.setData("/a","tomasLee".getBytes(), 0);

    }



    @Test
    public void createEmphoral() throws Exception{

        ZooKeeper zk = new ZooKeeper("localhost:2181",5000, null);

        zk.create("/c/c1", "tomnihaoma".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);

        System.out.println(" hello ");
    }


    @Test
    public void testWatch() throws Exception{


        //  客户端，新建一个zk访问链接；
        final ZooKeeper zk = new ZooKeeper("localhost:2181",5000, null);

        Stat st = new Stat();

        Watcher w = null;
        w = new Watcher() {
            public void process(WatchedEvent event) {

                try {

                    System.out.println("数据改了！！！");

                    // 访问 zk 信息， 客户端可以设置 一个watch ，监控zk集群发生的变化情况
                    zk.getData("/a", this, null );
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        byte[] data = zk.getData("/a",w,st);

        System.out.println(new String(data));

        while (true){
            Thread.sleep(10000);
        }


    }


}
