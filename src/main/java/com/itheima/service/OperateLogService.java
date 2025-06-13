package com.itheima.service;

import com.itheima.pojo.entity.OperateLog;

/**
 * 操作日志服务接口
 */
public interface OperateLogService {

    /**
     * 保存操作日志
     */
    void save(OperateLog operateLog);
}
