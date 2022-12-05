package com.it.redis;

import cn.hutool.core.util.StrUtil;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Cying
 * @Date 2022/7/29 11:22
 * @Description
 */
public class RedisMain {

    public static void main(String[] args) {


            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                    10,
                    100,
                    1,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(100),
                    new ThreadPoolExecutor.CallerRunsPolicy()

            );

            //循环操作redis
            for (int i = 0; i < 10; i++) {
                poolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {

                        String cying = get("cying");

                        System.out.println(Thread.currentThread().getName()+"获取的key："+cying);
                    }
                });
            }

            //终止线程池
            poolExecutor.shutdown();

            while (!poolExecutor.isTerminated()) {}
        System.out.println("所有线程执行完毕----耗时：");
    }

    private static String get(String key) {
        Jedis redis = RedisUtils.connect();
        String value = redis.get(key);
        System.out.println("value："+value);
        if (value == null) { //代表缓存值过期
            //设置3min的超时，防止del操作失败的时候，下次缓存过期一直不能load db
            if (redis.setnx("key_mutex", "ceshi") == 1) { //代表设置成功
                value = getDb();
                redis.set(key, value);
                redis.del("key_mutex");
                redis.close();
                return value;
            } else { //这个时候代表同时候的其他线程已经load db并回设到缓存了，这时候重试获取缓存值即可
                try {

                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+"线程在等待-------");
                    get(key); //重试
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            redis.close();
            return value;
        }
        redis.close();
        return "";
    }


    private static String getDb() {

        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName()+"操作数据库！！！");
            return "hello";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
