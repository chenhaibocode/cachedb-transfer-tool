package com.jdjr.rdp.common.cachedb.listener;

import com.jd.std.ucc.client.event.PathListener;
import com.jdjr.rdp.common.cachedb.util.LocalTimerConfigKeyMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @Author: chenhaibo
 * @Description:
 * @Date: Created in 21:10 2018/5/24
 */
public class TimerConfigKeyListener implements PathListener {

    private final static Log log = LogFactory.getLog(TimerConfigKeyListener.class);

    @Override
    public void handleConfig(Map<String, String> map) {
        try {
            if (map != null && map.size() > 0) {
                LocalTimerConfigKeyMap.setMapUcc(map);
                log.info("Mehtoud:TimerConfigKeyListener.handleConfig-->load failover config data is success,in total=" + map.size());
            } else {
                log.error("Mehtoud:TimerConfigKeyListener.handleConfig-->Not loaded any config data!");
            }
        } catch (Exception e) {
            log.error("Mehtoud:TimerConfigKeyListener.handleConfig-->The config data init error!:" + e.getMessage(), e);
        }
    }

    @Override
    public void exceptionCaught(Throwable throwable) {

    }
}
