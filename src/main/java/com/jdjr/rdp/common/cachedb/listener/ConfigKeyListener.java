package com.jdjr.rdp.common.cachedb.listener;

import com.jd.std.ucc.client.event.PathListener;
import com.jdjr.rdp.common.cachedb.util.LocalConfigKeyMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @Author: chenhaibo
 * @Description:
 * @Date: Created in 9:36 2018/3/21
 */
public class ConfigKeyListener implements PathListener {

    private final static Log log = LogFactory.getLog(ConfigKeyListener.class);

    @Override
    public void handleConfig(Map<String, String> map) {
        try {
            if (map != null && map.size() > 0) {
                LocalConfigKeyMap.setMap(map);
                log.warn("Mehtoud:handleConfig-->load failover config data is success,in total=" + map.size());
            } else {
                log.error("Mehtoud:handleConfig-->Not loaded any config data!");
            }
        } catch (Exception e) {
            log.error("Mehtoud:handleConfig-->The config data init error!:" + e.getMessage(), e);
        }
    }

    @Override
    public void exceptionCaught(Throwable throwable) {

    }
}
