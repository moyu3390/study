package com.nijunyang.zookeeper.zklock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Created by nijunyang on 2020/12/5 21:44
 */
public class TestZkLock {
    static int i = 0;
    static ZKLock zkLock = new ZKLock();

    public static void main(String[] args) throws InterruptedException {


        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        for (int j = 0; j < 1000; j++) {
            executorService.submit(
                    () -> {
                        ZKLock.Lock testLock = zkLock.lock("testLock", 20000L);
                        try {
                            testRun();
                        } finally {
                            zkLock.unlock(testLock);
                        }
                    }
            );
        }
        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println(i);
    }

    private static void testRun() {
        i++;
    }
}
