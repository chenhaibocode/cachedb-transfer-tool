package com.jdjr.rdp.common.cachedb.util;

import com.jd.std.ucc.client.ConfClient;
import com.jd.std.ucc.client.client.ConfClientFactory;
import com.jdjr.rdp.common.cachedb.listener.TimerConfigKeyListener;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @Author: chenhaibo
 * @Description:
 * @Date: Created in 21:08 2018/5/24
 */
public class LocalTimerConfigKeyMap {
    private final static Log logger = LogFactory.getLog(LocalTimerConfigKeyMap.class);
    private static Map<String, String> mapUcc = null;
    private static final String pathUcc = "/rdpcommontimerconfig";
    private static final String pathReadTokenUcc = "4gVwoAol";
    private static final String pathWriteTokenUcc = "iEyErOp8";
    private static ConfClient confClientUcc;
    static {
        try {
            confClientUcc = ConfClientFactory.getConfClient();
            TimerConfigKeyListener listenerUcc = new TimerConfigKeyListener();
            confClientUcc.subscribePath(pathUcc, pathReadTokenUcc, listenerUcc);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private static String getConfValueByKey(String key) {
        if (LocalTimerConfigKeyMap.mapUcc != null) {
            return LocalTimerConfigKeyMap.mapUcc.get(key);
        } else {
            return null;
        }
    }

    public static void setMapUcc(Map<String, String> mapUcc) {
        LocalTimerConfigKeyMap.mapUcc = mapUcc;
    }

    private static void updateConfValue(String key, String newValue){
        try {
            confClientUcc.updateConfValue(pathUcc, pathWriteTokenUcc, key, newValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setTimerConfigValue(String key, String newValue) {
        if(StringUtils.isBlank(key) || StringUtils.isBlank(newValue)) {
            logger.error("LocalTimerConfigKeyMap.setTimerConfigValue key is empty or newValue is empty.");
            return;
        }
        String oldValue = getConfValueByKey(key);
        if(StringUtils.isBlank(oldValue)){
            logger.info("LocalTimerConfigKeyMap.setTimerConfigValue oldValue is empty.");
            updateConfValue(key, newValue);
        }else {
            if(!newValue.equalsIgnoreCase(oldValue.trim())) {
                logger.info("LocalTimerConfigKeyMap.setTimerConfigValue oldValue: " + oldValue + ", newValue: "+newValue+".");
                updateConfValue(key, newValue);
            }else {
                logger.info("LocalTimerConfigKeyMap.setTimerConfigValue oldValue equals newValue.");
            }
        }
    }

    public static String getTimerConfigValue(String key) {
        if(StringUtils.isBlank(key)) {
            logger.error("LocalTimerConfigKeyMap.getTimerConfigValue key is empty.");
            return null;
        }
        return getConfValueByKey(key);
    }

}
