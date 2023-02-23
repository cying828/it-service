package com.it.thread;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Cying
 * @Date 2022/7/26 21:15
 * @Description
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        //开始时间
        long ls = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            Runnable worker = new MyRunnable("" + i);
            //执行Runnable
            poolExecutor.execute(worker);
        }

        //终止线程池
        poolExecutor.shutdown();

        while (!poolExecutor.isTerminated()) {
        }
        long lm = System.currentTimeMillis();
        System.out.println("所有线程执行完毕----耗时："+(lm-ls));
    }
}
