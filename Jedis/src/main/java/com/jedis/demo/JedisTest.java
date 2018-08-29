package com.jedis.demo;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    /**
     * Jedis直接连接
     */
    @Test
    public void demoOne() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
    }
}
