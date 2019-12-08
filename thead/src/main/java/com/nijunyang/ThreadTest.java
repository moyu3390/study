package com.nijunyang;

import java.util.concurrent.*;

/**
 * @author: create by nijunyang
 * @date:2019/7/8
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        System.out.println("------");
        Future<Integer> task = executorService.submit(new Task());
        Thread.sleep(8000);
        System.out.println("------");
        System.out.println(task.get());
        task.cancel(true);
        executorService.shutdown();



    }
}

class Task implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        Thread.sleep(7000);
        return 0;
    }
}
