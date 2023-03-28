package com.easy.query.core.basic.plugin.track;

/**
 * @FileName: TrackManager.java
 * @Description: 文件说明
 * @Date: 2023/3/19 13:51
 * @author xuejiaming
 */
public interface TrackManager {
    void begin();

    /**
     * 当前线程是否在追踪中
     * @return
     */
    boolean currentThreadTracking();
    TrackContext getCurrentTrackContext();
    void release();

}