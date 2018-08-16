package com.jdjr.rdp.common.cachedb.dao.impl;

import com.jdjr.rdp.common.cachedb.dao.RedisDao;
import com.wangyin.rediscluster.client.CacheClusterClient;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: chenhaibo
 * @Description: R2M实现
 * @Date: Created in 17:42 2018/3/7
 */
public class RedisDaoR2mImpl implements RedisDao {

    private CacheClusterClient redisClient;

    @Override
    public String get(String key) {
        return redisClient.get(key);
    }

    @Override
    public void set(String key, String value) {
        redisClient.set(key, value);
    }

    @Override
    public void setex(String key, int seconds, String value) {
        redisClient.setex(key, seconds, value);
    }

    @Override
    public void del(String key) {
        redisClient.del(key);
    }

    @Override
    public void lpush(String key, String... value) {
        redisClient.lpush(key, value);
    }

    @Override
    public String lpop(String key) {
        return redisClient.lpop(key);
    }

    @Override
    public String rpop(String key) {
        return redisClient.rpop(key);
    }

    @Override
    public void incr(String key) {
        redisClient.incr(key);
    }

    @Override
    public Long incrBy(String key, long step) {
        return redisClient.incrBy(key, step);
    }

    @Override
    public Long llen(String key) {
        return redisClient.llen(key);
    }

    @Override
    public Boolean sIsMember(String key, String value) {
        return redisClient.sismember(key, value);
    }

    @Override
    public Set<String> smembers(String key) {
        return redisClient.smembers(key);
    }

    @Override
    public Long sadd(String key, String[] values) {
        return redisClient.sadd(key, values);
    }

    @Override
    public Long sadd(String key, String value) {
        return redisClient.sadd(key, value);
    }

    @Override
    public Boolean exist(String key) {
        return redisClient.exists(key);
    }

    @Override
    public String spop(String key) {
        return redisClient.spop(key);
    }

    @Override
    public void append(String key, String value) {
        redisClient.append(key, value);
    }

    @Override
    public void expire(String key, int seconds) {
        redisClient.expire(key, seconds);
    }

    @Override
    public Boolean setnx(String key, String value) {
        return this.redisClient.setnx(key, value) == 1;
    }

    @Override
    public String getSet(String key, String newVal) {
        return redisClient.getSet(key, newVal);
    }

    @Override
    public void setBit(String key, long offset, boolean value) {
        redisClient.setbit(key, offset, value);
    }

    @Override
    public Boolean getBit(String key, long offset) {
        return redisClient.getbit(key, offset);
    }

    @Override
    public Long bitCount(String key) {
        return redisClient.bitcount(key);
    }

    @Override
    public List<String> mget(String... keys) {
        return redisClient.mget(keys);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return redisClient.hset(key, field, value);
    }

    @Override
    public String hget(String key, String field) {
        return redisClient.hget(key, field);
    }

    @Override
    public Long hdel(String key, String field) {
        return redisClient.hdel(key, field);
    }

    @Override
    public Set<String> hkeys(String key) {
        return redisClient.hkeys(key);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return redisClient.hgetAll(key);
    }

    @Override
    public Long scard(String key) {
        return redisClient.scard(key);
    }

    @Override
    public void srem(String key, String content) {
        redisClient.srem(key, content);
    }

    public void setRedisClient(CacheClusterClient redisClient) {
        this.redisClient = redisClient;
    }
}
