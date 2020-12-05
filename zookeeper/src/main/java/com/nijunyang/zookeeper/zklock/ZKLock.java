package com.nijunyang.zookeeper.zklock;

import lombok.Data;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * Created by nijunyang on 2020/11/29 21:51
 */
public class ZKLock {
    private static final String SERVER = "192.168.0.67:2181";
    private ZkClient zkClient;
    private static final String ROOT_PATH = "/lock";

    public ZKLock() {
        zkClient = new ZkClient(SERVER, 5000, 20000);
        buildRoot();
    }

    private void buildRoot() {
        if (!zkClient.exists(ROOT_PATH)) {
            zkClient.createPersistent(ROOT_PATH);
        }
    }

    /**
     * 加锁
     *
     * @param lockId
     * @param timeout
     * @return
     */
    public Lock lock(String lockId, long timeout) {
        Lock lockNode = createLock(lockId);
        // 尝试激活锁
        tryActiveLock(lockNode);
        if (!lockNode.isActive()) {
            try {
                synchronized (lockNode) {
                    lockNode.wait(timeout);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (!lockNode.isActive()) {
            throw new RuntimeException("获取锁超时");
        }
        return lockNode;
    }

    /**
     * 释放锁
     *
     * @param lock
     */
    public void unlock(Lock lock) {
        if (lock.isActive()) {
            zkClient.delete(lock.getPath());
        }
    }

    /**
     * 激活锁
     * @param lockNode
     */
    private void tryActiveLock(Lock lockNode) {
        // 判断当前是否为最小节点
        List<String> list = zkClient.getChildren(ROOT_PATH)
                .stream()
                .sorted()
                .map(p -> ROOT_PATH + "/" + p)
                .collect(Collectors.toList());
        String firstNodePath = list.get(0);

        //第一个节点是当前节点，将锁设置为激活
        if (firstNodePath.equals(lockNode.getPath())) {
            lockNode.setActive(true);
        } else {
            //第一个节点不是当前节点，则监听前面一个节点
            String upNodePath = list.get(list.indexOf(lockNode.getPath()) - 1);
            zkClient.subscribeDataChanges(upNodePath, new IZkDataListener() {
                @Override
                public void handleDataChange(String dataPath, Object data) throws Exception {
                }
                @Override
                public void handleDataDeleted(String dataPath) throws Exception {
                    //监听之后继续尝试加锁，加锁成功唤醒之前的等待
                    tryActiveLock(lockNode);
                    synchronized (lockNode) {
                        if (lockNode.isActive()) {
                            lockNode.notify();
                        }
                    }
                    zkClient.unsubscribeDataChanges(upNodePath, this);
                }
            });
        }
    }
    /**
     * 创建锁节点
     *
     * @param lockId
     * @return Lock
     */
    private Lock createLock(String lockId) {
        String nodePath = zkClient.createEphemeralSequential(ROOT_PATH + "/" + lockId, "write");
        return new Lock(lockId, nodePath);
    }

    @Data
    public static class Lock {

        private String lockId;
        private String path;
        /**
         * 是否激活锁
         */
        private boolean active;

        public Lock(String lockId, String path) {
            this.lockId = lockId;
            this.path = path;
        }
    }
}
