package com.it.thread;

import com.it.model.Apple;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @Author Cying
 * @Date 2022/7/26 21:33
 * @Description
 */
public class MyRunnable implements Runnable {

    Lock lock = new ReentrantLock();

    private String command;
    private static int number = 100;


    public MyRunnable(String s) {
        this.command = s;
    }
    @Override
    public void run() {
        lockData();
    }

    private void lockData() {
        lock.lock();
            try {
                if (number>0) {
                    System.out.println("序号"+command+"P"+Thread.currentThread().getName() + "开始数量："+number
                            + "剩余数量："+ --number);
                    Thread.sleep(100);
                } else {
                    System.out.println("不好意思啊，已经售罄！！！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
    }

    private void sysData() {
        synchronized (this) {
            try {
                if (number>0) {
                    System.out.println("序号"+command+"P"+Thread.currentThread().getName() + "开始数量："+number
                            + "剩余数量："+ --number);
                    Thread.sleep(100);
                } else {
                    System.out.println("不好意思啊，已经售罄！！！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}
