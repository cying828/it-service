package com.it.redis;

import redis.clients.jedis.Jedis;

/**
 * @Author Cying
 * @Date 2022/7/29 11:11
 * @Description
 */
public class RedisUtils {

    private static Jedis jedis = null;

    //创建连接
    public static Jedis connect() {
//        if (jedis !=null && jedis.ping().equals("PONG")) {
//            return jedis;
//        }
        jedis = new Jedis("127.0.0.1");
        return jedis;
    }
}
