package com.jdjr.rdp.common.cachedb.util;

import java.util.Map;

/**
 * @Author: chenhaibo
 * @Description:
 * @Date: Created in 9:39 2018/3/21
 */
public class LocalConfigKeyMap {
    private static Map<String, String> map = null;

    public static String getConfValueByKey(String key) {
        if (LocalConfigKeyMap.map != null) {
            return LocalConfigKeyMap.map.get(key);
        } else {
            return null;
        }
    }

    public static void setMap(Map<String, String> map) {
        LocalConfigKeyMap.map = map;
    }
}