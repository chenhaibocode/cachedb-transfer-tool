package com.jdjr.rdp.common.cachedb.datant;

import java.util.Map;

/**
 * 切换的开关状态
 * 对于读： 1 读R2M  2 读 JimDb
 * 对于写： 1 写R2M  2 写JimDb  12 双写，已R2M为主   21  双写，以JimDb为主
 *
 * @Author: chenhaibo
 * @Description:
 * @Date: Created in 17:18 2018/3/7
 */
public interface MultiDbSwitch {

    boolean isReadR2m();

    Map<String, String> getDoubleWrite();
}