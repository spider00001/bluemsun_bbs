package com.bluemsun.utils;


import com.bluemsun.entity.Blog;
import com.bluemsun.entity.User;
import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtil {

    private final Gson gson = new Gson();
    private final static Jedis jedis;
    private final static JedisPool jedisPool;

    static {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Jedis.xml");
        jedisPool = (JedisPool)applicationContext.getBean("jedisPool");
        jedis = jedisPool.getResource();
    }

    public User getUser(int userId) {
        User userRes = null;
        try {
            userRes = gson.fromJson(jedis.get("user:"+userId),User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRes;
    }

    public boolean setUser(User user) {
        try {
            jedis.set("user:"+user.getId(),gson.toJson(user));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    public boolean pfadd(String key, String value) {
        try {
            jedis.select(1);
            jedis.pfadd(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.select(0);
        }
    }

    public long pfcount(String key) {
        long count = 0;
        try {
            jedis.select(1);
            count = jedis.pfcount(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.select(0);
        }
        return count;
    }

    public boolean expire(String key, int seconds) {
        try {
            jedis.expire(key,seconds);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean flushDB(int db) {
        try {
            jedis.select(db);
            jedis.flushDB();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.select(0);
        }
    }

}
