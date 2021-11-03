package com.bluemsun.utils;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtil {

    private final static Jedis jedis;
    private final static JedisPool jedisPool;

    static {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Jedis.xml");
        jedisPool = (JedisPool)applicationContext.getBean("jedisPool");
        jedis = jedisPool.getResource();
    }

    public boolean set(String key, String value) {
        try {
            jedis.set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String get(String key) {
        String res = null;
        try {
            res = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }

    public boolean del(String key) {
        try {
            jedis.del(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setnx(String key, String value) {
        try {
            jedis.setnx(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean lpush(String key, String... values) {
        try {
            jedis.lpush(key, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object lpop(String key) {
        String res = null;
        try {
            res = jedis.lpop(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean lset(String key, long index, String value) {
        try {
            jedis.lset(key,index,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }






}
