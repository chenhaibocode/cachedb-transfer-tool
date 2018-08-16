package com.jdjr.rdp.common.cachedb.datant;

import com.google.common.base.Strings;
import com.jd.std.ucc.client.ConfClient;
import com.jd.std.ucc.client.client.ConfClientFactory;
import com.jdjr.rdp.common.cachedb.listener.ConfigKeyListener;
import com.jdjr.rdp.common.cachedb.util.LocalConfigKeyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenhaibo
 * @Description:
 * @Date: Created in 17:15 2018/3/7
 */
public abstract class BaseDaoProxy implements MultiDbSwitch {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static final String path = "/rdpcommoncachedb";
    private static final String pathToken = "2kPRZBxD";
    protected ProxyQueryTemplate queryTemplate;
    protected ProxyWriteTemplate writeTemplate;
    private static ConfClient confClient;

    static {
        try {
            confClient = ConfClientFactory.getConfClient();
            ConfigKeyListener listener = new ConfigKeyListener();
            confClient.subscribePath(path, pathToken, listener);
        } catch (Exception e1) {

        }
    }

    @Override
    public boolean isReadR2m() {
        try {
            String value = LocalConfigKeyMap.getConfValueByKey(getReadSwitchKey());
            if (Strings.isNullOrEmpty(value)) {
                return true;
            }
            return value.trim().equalsIgnoreCase("r2m");
        } catch (Exception e) {
            logger.error("Get config by key, is exception");
        }
        return false;
    }

    @Override
    public Map<String, String> getDoubleWrite() {
        Map<String, String> map = new HashMap<String, String>();
        try {
            String v = LocalConfigKeyMap.getConfValueByKey(getWriteSwitchKey());
            if (Strings.isNullOrEmpty(v)) {
                map.put("masterDao", "r2m");
                map.put("slaverDao", null);
                return map;
            }
            if (v.trim().toUpperCase().startsWith("R2M")) {
                map.put("masterDao", "r2m");
                if (v.trim().equalsIgnoreCase("r2m-jimdb")
                        || v.trim().equalsIgnoreCase("jimdb-r2m")) {
                    map.put("slaverDao", "jimdb");
                }
            } else {
                map.put("masterDao", "jimdb");
                if (v.trim().equalsIgnoreCase("r2m-jimdb")
                        || v.trim().equalsIgnoreCase("jimdb-r2m")) {
                    map.put("slaverDao", "r2m");
                }
            }
        } catch (Exception e) {
            logger.error("getDoubleWrite Get config by key, is exception");
        }
        return map;
    }

    protected abstract String getReadSwitchKey();

    protected abstract String getWriteSwitchKey();

    public void setQueryTemplate(ProxyQueryTemplate queryTemplate) {
        this.queryTemplate = queryTemplate;
    }

    public void setWriteTemplate(ProxyWriteTemplate writeTemplate) {
        this.writeTemplate = writeTemplate;
    }
}
