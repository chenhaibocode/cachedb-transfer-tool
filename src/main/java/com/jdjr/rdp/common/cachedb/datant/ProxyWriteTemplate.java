package com.jdjr.rdp.common.cachedb.datant;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: chenhaibo
 * @Description:
 * @Date: Created in 17:22 2018/3/7
 */
public class ProxyWriteTemplate<T> {

    private static final Logger log = LoggerFactory.getLogger(ProxyWriteTemplate.class);

    private static final String master = "r2m";

    private static final String slaver = "r2m";

    private static final String masterDao = "masterDao";

    private static final String slaverDao = "slaverDao";

    ThreadPoolExecutor executorWriteCallback = new ThreadPoolExecutor(16, 64, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(512), new ThreadPoolExecutor.CallerRunsPolicy());

    ThreadPoolExecutor executorWriteCallbackNoReturn = new ThreadPoolExecutor(16, 64, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(512), new ThreadPoolExecutor.CallerRunsPolicy());

    public Object write(MultiDbSwitch multiDbSwitch, final T r2mDao, final T jimdbDao, final WriteCallback<T> writeCallback) {
        final Map<String, String> doubleWrite = multiDbSwitch.getDoubleWrite();
        Object result;
        if (master.equalsIgnoreCase(doubleWrite.get(masterDao))) {
            result = writeCallback.write(r2mDao);
        } else {
            result = writeCallback.write(jimdbDao);
        }
        try {
            if (StringUtils.isNotBlank(doubleWrite.get(slaverDao))) {
                executorWriteCallback.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (slaver.equalsIgnoreCase(doubleWrite.get(slaverDao))) {
                            writeCallback.write(r2mDao);
                        } else {
                            writeCallback.write(jimdbDao);
                        }
                    }
                });
            }
        } catch (Exception e) {
            log.error("Double write exception.MultiDbSwitch: " + multiDbSwitch.getClass(), e);
        }
        return result;
    }

    public interface WriteCallback<T> {
        Object write(T dao);
    }

    public interface WriteCallbackNoReturn<T> {
        void write(T dao);
    }

    public void write(MultiDbSwitch multiDbSwitch, final T r2mDao, final T jimdbDao, final WriteCallbackNoReturn<T> writeCallbackNoReturn) {
        final Map<String, String> doubleWrite = multiDbSwitch.getDoubleWrite();
        if (master.equalsIgnoreCase(doubleWrite.get(masterDao))) {
            writeCallbackNoReturn.write(r2mDao);
        } else {
            writeCallbackNoReturn.write(jimdbDao);
        }
        try {
            if (StringUtils.isNotBlank(doubleWrite.get(slaverDao))) {
                executorWriteCallbackNoReturn.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (slaver.equalsIgnoreCase(doubleWrite.get(slaverDao))) {
                            writeCallbackNoReturn.write(r2mDao);
                        } else {
                            writeCallbackNoReturn.write(jimdbDao);
                        }
                    }
                });
            }
        } catch (Exception e) {
            log.error("Double write exception.MultiDbSwitch: " + multiDbSwitch.getClass(), e);
        }
    }
}
