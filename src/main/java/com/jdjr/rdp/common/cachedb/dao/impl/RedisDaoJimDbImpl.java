package com.jdjr.rdp.common.cachedb.dao.impl;

import com.jd.jim.cli.Cluster;
import com.jdjr.rdp.common.cachedb.dao.RedisDao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: chenhaibo
 * @Description: JIMDB实现
 * @Date: Created in 17:42 2018/3/7
 */
public class RedisDaoJimDbImpl implements RedisDao {

    private Cluster jimdbClient;

    @Override
    public String get(String key) {
        return jimdbClient.get(key);
    }

    @Override
    public void set(String key, String value) {
        jimdbClient.set(key, value);
    }

    @Override
    public void setex(String key, int seconds, String value) {
        jimdbClient.setEx(key, value, seconds, TimeUnit.SECONDS);
    }

    @Override
    public void del(String key) {
        jimdbClient.del(key);
    }

    @Override
    public void lpush(String key, String... value) {
        jimdbClient.lPush(key, value);
    }

    @Override
    public String lpop(String key) {
        return jimdbClient.lPop(key);
    }

    @Override
    public String rpop(String key) {
        return jimdbClient.rPop(key);
    }

    @Override
    public void incr(String key) {
        jimdbClient.incr(key);
    }

    @Override
    public Long incrBy(String key, long step) {
        return jimdbClient.incrBy(key, step);
    }

    @Override
    public Long llen(String key) {
        return jimdbClient.lLen(key);
    }

    @Override
    public Boolean sIsMember(String key, String value) {
        return jimdbClient.sIsMember(key, value);
    }

    @Override
    public Set<String> smembers(String key) {
        return jimdbClient.sMembers(key);
    }

    @Override
    public Long sadd(String key, String[] values) {
        return jimdbClient.sAdd(key, values);
    }

    @Override
    public Long sadd(String key, String value) {
        return jimdbClient.sAdd(key, value);
    }

    @Override
    public Boolean exist(String key) {
        return jimdbClient.exists(key);
    }

    @Override
    public String spop(String key) {
        return jimdbClient.sPop(key);
    }

    @Override
    public void append(String key, String value) {
        jimdbClient.append(key, value);
    }

    @Override
    public void expire(String key, int seconds) {
        jimdbClient.expire(key, (long) seconds, TimeUnit.SECONDS);
    }

    @Override
    public Boolean setnx(String key, String value) {
        return jimdbClient.setNX(key, value);
    }

    @Override
    public String getSet(String key, String newVal) {
        return jimdbClient.getSet(key, newVal);
    }

    @Override
    public void setBit(String key, long offset, boolean value) {
        jimdbClient.setBit(key, offset, value);
    }

    @Override
    public Boolean getBit(String key, long offset) {
        return jimdbClient.getBit(key, offset);
    }

    @Override
    public Long bitCount(String key) {
        return jimdbClient.bitCount(key);
    }

    @Override
    public List<String> mget(String... keys) {
        return jimdbClient.mGet(keys);
    }

    @Override
    public Long hset(String key, String field, String value) {
        boolean re = jimdbClient.hSet(key, field, value);
        if (re) {
            return 1L;
        }
        return 0L;
    }

    @Override
    public String hget(String key, String field) {
        return jimdbClient.hGet(key, field);
    }

    @Override
    public Long hdel(String key, String field) {
        return jimdbClient.hDel(key, field);
    }

    @Override
    public Set<String> hkeys(String key) {
        return jimdbClient.hKeys(key);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return jimdbClient.hGetAll(key);
    }

    @Override
    public Long scard(String key) {
        return jimdbClient.sCard(key);
    }

    @Override
    public void srem(String key, String content) {
        jimdbClient.sRem(key, content);
    }

    public void setJimdbClient(Cluster jimdbClient) {
        this.jimdbClient = jimdbClient;
    }
}
