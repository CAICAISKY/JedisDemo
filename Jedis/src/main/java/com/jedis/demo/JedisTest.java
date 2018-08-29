package com.jedis.demo;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
    /**
     * Jedis直接连接,适用于无密码
     */
    @Test
    public void demoOne() {
        //设置IP地址和端口
        Jedis jedis = new Jedis("39.108.182.112", 6379);
        //存取
        jedis.set("name", "Schuyler");
        String name = jedis.get("name");
        System.out.println(name);
        //切记释放资源
        jedis.close();
    }

    /**
     * 通过连接池连接Redis，可进行灵活设置
     */
    @Test
    public void demoTwo() {
        //获取连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(20);
        //设置最大空闲连接数
        config.setMaxIdle(10);
        //获得连接池
        JedisPool jedisPool = new JedisPool(config, "39.108.182.112", 6379, 20000, "123456");
        //获得核心对象
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set("name", "Schuyler");
            String name = jedis.get("name");
            System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                //这里的连接池是直接new出来的所以最好关闭了，如果交给spring管理，可以不进行关闭
                jedisPool.close();
            }
        }
    }


}
