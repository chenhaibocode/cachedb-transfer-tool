package com.jdjr.rdp.common.cachedb.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: chenhaibo
 * @Description:
 * @Date: Created in 17:33 2018/3/7
 */
public interface RedisDao {

    String get(String key);

    void set(String key, String value);

    void setex(String key, int seconds, String value);

    void del(String key);

    void lpush(String key, String... value);

    String lpop(String key);

    String rpop(String key);

    void incr(String key);

    Long incrBy(String key, long step);

    Long llen(String key);

    Boolean sIsMember(String key, String value);

    Set<String> smembers(String key);

    Long sadd(String key, String[] values);

    Long sadd(String key, String value);

    Boolean exist(String key);

    String spop(String key);

    void append(String key, String value);

    void expire(String key, int seconds);

    Boolean setnx(String key, String value);

    String getSet(String key, String newVal);

    void setBit(String key, long offset, boolean value);

    Boolean getBit(String key, long offset);

    Long bitCount(String key);

    List<String> mget(String... keys);

    Long hset(String key, String field, String value);

    String hget(String key, String field);

    Long hdel(String key, String field);

    Set<String> hkeys(String key);

    Map<String, String> hgetAll(String key);

    Long scard(String key);

    void srem(String key, String content);
}
