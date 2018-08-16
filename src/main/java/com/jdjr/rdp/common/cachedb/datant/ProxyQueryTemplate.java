package com.jdjr.rdp.common.cachedb.datant;

/**
 * @Author: chenhaibo
 * @Description:
 * @Date: Created in 17:21 2018/3/7
 */
public class ProxyQueryTemplate<T> {

    public Object query(MultiDbSwitch multiDbSwitch, T r2mDao, T jimdbDao, QueryCallback<T> queryCallback) {
        T dao = multiDbSwitch.isReadR2m() ? r2mDao : jimdbDao;
        return queryCallback.query(dao);
    }

    public interface QueryCallback<T> {
        Object query(T dao);
    }
}
