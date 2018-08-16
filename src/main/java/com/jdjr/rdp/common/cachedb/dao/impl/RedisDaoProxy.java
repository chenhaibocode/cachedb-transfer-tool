package com.jdjr.rdp.common.cachedb.dao.impl;

import com.jdjr.rdp.common.cachedb.dao.RedisDao;
import com.jdjr.rdp.common.cachedb.datant.BaseDaoProxy;
import com.jdjr.rdp.common.cachedb.datant.ProxyQueryTemplate;
import com.jdjr.rdp.common.cachedb.datant.ProxyWriteTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: chenhaibo
 * @Description: proxy实现
 * @Date: Created in 17:34 2018/3/7
 */
public class RedisDaoProxy extends BaseDaoProxy implements RedisDao {

    private String readRdpCachedbConfig;
    private String writeRdpCachedbConfig;

    public void setReadRdpCachedbConfig(String readRdpCachedbConfig) {
        this.readRdpCachedbConfig = readRdpCachedbConfig;
    }

    public void setWriteRdpCachedbConfig(String writeRdpCachedbConfig) {
        this.writeRdpCachedbConfig = writeRdpCachedbConfig;
    }

    private RedisDao r2mDao;
    private RedisDao jimdbDao;

    public void setR2mDao(RedisDao r2mDao) {
        this.r2mDao = r2mDao;
    }

    public void setJimdbDao(RedisDao jimdbDao) {
        this.jimdbDao = jimdbDao;
    }

    @Override
    protected String getReadSwitchKey() {
        return readRdpCachedbConfig;
    }

    @Override
    protected String getWriteSwitchKey() {
        return writeRdpCachedbConfig;
    }

    @Override
    public String get(final String key) {
        return (String) queryTemplate.query(this, r2mDao, jimdbDao, new ProxyQueryTemplate.QueryCallback<RedisDao>() {
            @Override
            public String query(RedisDao dao) {
                return dao.get(key);
            }
        });
    }

    @Override
    public void set(final String key, final String value) {
        writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallbackNoReturn<RedisDao>() {
            @Override
            public void write(RedisDao dao) {
                dao.set(key, value);
            }
        });
    }

    @Override
    public void setex(final String key, final int seconds, final String value) {
        writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallbackNoReturn<RedisDao>() {
            @Override
            public void write(RedisDao dao) {
                dao.setex(key, seconds, value);
            }
        });
    }

    @Override
    public void del(final String key) {
        writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallbackNoReturn<RedisDao>() {
            @Override
            public void write(RedisDao dao) {
                dao.del(key);
            }
        });
    }

    @Override
    public void lpush(final String key, final String... value) {
        writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallbackNoReturn<RedisDao>() {
            @Override
            public void write(RedisDao dao) {
                dao.lpush(key, value);
            }
        });
    }

    @Override
    public String lpop(final String key) {
        return (String) writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallback<RedisDao>() {
            @Override
            public String write(RedisDao dao) {
                return dao.lpop(key);
            }
        });
    }

    @Override
    public String rpop(final String key) {
        return (String) writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallback<RedisDao>() {
            @Override
            public String write(RedisDao dao) {
                return dao.rpop(key);
            }
        });
    }

    @Override
    public void incr(final String key) {
        writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallbackNoReturn<RedisDao>() {
            @Override
            public void write(RedisDao dao) {
                dao.incr(key);
            }
        });
    }

    @Override
    public Long incrBy(final String key, final long step) {
        return (Long) writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallback<RedisDao>() {
            @Override
            public Long write(RedisDao dao) {
                return dao.incrBy(key, step);
            }
        });
    }

    @Override
    public Long llen(final String key) {
        return (Long) queryTemplate.query(this, r2mDao, jimdbDao, new ProxyQueryTemplate.QueryCallback<RedisDao>() {
            @Override
            public Long query(RedisDao dao) {
                return dao.llen(key);
            }
        });
    }

    @Override
    public Boolean sIsMember(final String key, final String value) {
        return (Boolean) queryTemplate.query(this, r2mDao, jimdbDao, new ProxyQueryTemplate.QueryCallback<RedisDao>() {
            @Override
            public Boolean query(RedisDao dao) {
                return dao.sIsMember(key, value);
            }
        });
    }

    @Override
    public Set<String> smembers(final String key) {
        return (Set<String>) queryTemplate.query(this, r2mDao, jimdbDao, new ProxyQueryTemplate.QueryCallback<RedisDao>() {
            @Override
            public Set<String> query(RedisDao dao) {
                return dao.smembers(key);
            }
        });
    }

    @Override
    public Long sadd(final String key, final String[] values) {
        return (Long) writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallback<RedisDao>() {
            @Override
            public Long write(RedisDao dao) {
                return dao.sadd(key, values);
            }
        });
    }

    @Override
    public Long sadd(final String key, final String value) {
        return (Long) writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallback<RedisDao>() {
            @Override
            public Long write(RedisDao dao) {
                return dao.sadd(key, value);
            }
        });
    }

    @Override
    public Boolean exist(final String key) {
        return (Boolean) queryTemplate.query(this, r2mDao, jimdbDao, new ProxyQueryTemplate.QueryCallback<RedisDao>() {
            @Override
            public Boolean query(RedisDao dao) {
                return dao.exist(key);
            }
        });
    }

    @Override
    public String spop(final String key) {
        return (String) writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallback<RedisDao>() {
            @Override
            public String write(RedisDao dao) {
                return dao.spop(key);
            }
        });
    }

    @Override
    public void append(final String key, final String value) {
        writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallbackNoReturn<RedisDao>() {
            @Override
            public void write(RedisDao dao) {
                dao.append(key, value);
            }
        });
    }

    @Override
    public void expire(final String key, final int seconds) {
        writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallbackNoReturn<RedisDao>() {
            @Override
            public void write(RedisDao dao) {
                dao.expire(key, seconds);
            }
        });
    }

    @Override
    public Boolean setnx(final String key, final String value) {
        return (Boolean) writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallback<RedisDao>() {
            @Override
            public Boolean write(RedisDao dao) {
                return dao.setnx(key, value);
            }
        });
    }

    @Override
    public String getSet(final String key, final String newVal) {
        return (String) writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallback<RedisDao>() {
            @Override
            public String write(RedisDao dao) {
                return dao.getSet(key, newVal);
            }
        });
    }

    @Override
    public void setBit(final String key, final long offset, final boolean value) {
        writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallbackNoReturn<RedisDao>() {
            @Override
            public void write(RedisDao dao) {
                dao.setBit(key, offset, value);
            }
        });
    }

    @Override
    public Boolean getBit(final String key, final long offset) {
        return (Boolean) queryTemplate.query(this, r2mDao, jimdbDao, new ProxyQueryTemplate.QueryCallback<RedisDao>() {
            @Override
            public Boolean query(RedisDao dao) {
                return dao.getBit(key, offset);
            }
        });
    }

    @Override
    public Long bitCount(final String key) {
        return (Long) queryTemplate.query(this, r2mDao, jimdbDao, new ProxyQueryTemplate.QueryCallback<RedisDao>() {
            @Override
            public Long query(RedisDao dao) {
                return dao.bitCount(key);
            }
        });
    }

    @Override
    public List<String> mget(final String... keys) {
        return (List<String>) queryTemplate.query(this, r2mDao, jimdbDao, new ProxyQueryTemplate.QueryCallback<RedisDao>() {
            @Override
            public List<String> query(RedisDao dao) {
                return dao.mget(keys);
            }
        });
    }

    @Override
    public Long hset(final String key, final String field, final String value) {
        return (Long) writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallback<RedisDao>() {
            @Override
            public Long write(RedisDao dao) {
                return dao.hset(key, field, value);
            }
        });
    }

    @Override
    public String hget(final String key, final String field) {
        return (String) queryTemplate.query(this, r2mDao, jimdbDao, new ProxyQueryTemplate.QueryCallback<RedisDao>() {
            @Override
            public String query(RedisDao dao) {
                return dao.hget(key, field);
            }
        });
    }

    @Override
    public Long hdel(final String key, final String field) {
        return (Long) writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallback<RedisDao>() {
            @Override
            public Long write(RedisDao dao) {
                return dao.hdel(key, field);
            }
        });
    }

    @Override
    public Set<String> hkeys(final String key) {
        return (Set<String>) queryTemplate.query(this, r2mDao, jimdbDao, new ProxyQueryTemplate.QueryCallback<RedisDao>() {
            @Override
            public Set<String> query(RedisDao dao) {
                return dao.hkeys(key);
            }
        });
    }

    @Override
    public Map<String, String> hgetAll(final String key) {
        return (Map<String, String>) queryTemplate.query(this, r2mDao, jimdbDao, new ProxyQueryTemplate.QueryCallback<RedisDao>() {
            @Override
            public Map<String, String> query(RedisDao dao) {
                return dao.hgetAll(key);
            }
        });
    }

    @Override
    public Long scard(final String key) {
        return (Long) queryTemplate.query(this, r2mDao, jimdbDao, new ProxyQueryTemplate.QueryCallback<RedisDao>() {
            @Override
            public Long query(RedisDao dao) {
                return dao.scard(key);
            }
        });
    }

    @Override
    public void srem(final String key, final String content) {
        writeTemplate.write(this, r2mDao, jimdbDao, new ProxyWriteTemplate.WriteCallbackNoReturn<RedisDao>() {
            @Override
            public void write(RedisDao dao) {
                dao.srem(key, content);
            }
        });
    }
}
