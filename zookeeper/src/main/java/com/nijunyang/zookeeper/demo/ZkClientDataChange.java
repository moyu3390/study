package com.nijunyang.zookeeper.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by nijunyang on 2020/10/27 21:36
 */
public class ZkClientDataChange {

    ZooKeeper zkClient;

    @Before
    public void before() throws IOException {
        //集群用,分割
        String connectString = "192.168.0.67:2181";
        zkClient = new ZooKeeper(connectString, 40000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getPath());
                System.out.println(event);
            }
        });
    }

    //获取数据
    @Test
    public void getData1() throws KeeperException, InterruptedException {
        byte[] data = zkClient.getData("/njy", false, null);
        System.out.println(new String(data));
    }

    //添加监听
    @Test
    public void getData2() throws KeeperException, InterruptedException {
        //直接调用初始化的监听
        byte[] data = zkClient.getData("/njy", true, null);
        System.out.println(new String(data));
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void getData3() throws KeeperException, InterruptedException {
        //Stat 会填充带回来
        Stat stat = new Stat();
        //添加自定义监听
        byte[] data = zkClient.getData("/njy", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    //重复添加监听
                    zkClient.getData(event.getPath(), this, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(event.getPath());
            }
        }, stat);
        System.out.println(stat);
        Thread.sleep(Long.MAX_VALUE);
    }

    //带回调
    @Test
    public void getData4() throws KeeperException, InterruptedException {
        zkClient.getData("/njy", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, java.lang.String path, Object ctx, byte[] data, Stat stat) {
                System.out.println(new String(data));
                System.out.println(stat);
            }
        }, "");
        Thread.sleep(Long.MAX_VALUE);
    }

    //获取子节点
    @Test
    public void getChild() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/njy", false);
        children.stream().forEach(System.out::println);
    }

    //监听子节点变化
    @Test
    public void getChild2() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/njy", event -> {
            System.out.println(event.getPath());
            try {
                zkClient.getChildren(event.getPath(), false);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        children.stream().forEach(System.out::println);
        Thread.sleep(Long.MAX_VALUE);
    }

    //持续监听
    @Test
    public void getChild3() throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        List<String> children = zkClient.getChildren("/njy", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getPath());
                try {
                    zkClient.getChildren(event.getPath(),this);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, stat);
        children.stream().forEach(System.out::println);
        Thread.sleep(Long.MAX_VALUE);
    }

    //创建节点
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        List<ACL> list = new ArrayList<>();
//        int perm = ZooDefs.Perms.ADMIN | ZooDefs.Perms.READ;//cdwra
//        int READ = 1 << 0;
//        int WRITE = 1 << 1;
//        int CREATE = 1 << 2;
//        int DELETE = 1 << 3;
//        int ADMIN = 1 << 4;
//        int ALL = READ | WRITE | CREATE | DELETE | ADMIN;
        int perm = ZooDefs.Perms.ALL;
        //ACL权限
        ACL acl = new ACL(perm, new Id("world", "anyone"));
//        ACL acl2 = new ACL(perm, new Id("ip", "192.168.0.67"));
//        ACL acl3 = new ACL(perm, new Id("ip", "192.168.0.101"));
        list.add(acl);
//        list.add(acl2);
//        list.add(acl3);
        zkClient.create("/njy/njyn1", "hello".getBytes(), list, CreateMode.PERSISTENT);
    }
}
