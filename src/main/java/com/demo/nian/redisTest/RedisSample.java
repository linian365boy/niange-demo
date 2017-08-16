package com.demo.nian.redisTest;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author tanfan
 * @ClassName: RedisSample
 * @Description: 未使用spring的最新redis线程池
 * @date: 2017/4/1 18:18
 * @since JDK 1.7
 */
public class RedisSample {
    private static JedisPool pool;

    public static JedisPool getPool(){
        if(pool != null ){
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(500);
            pool = new JedisPool(config ,"127.0.0.1",6388);
        }
        return pool;
    }



}
