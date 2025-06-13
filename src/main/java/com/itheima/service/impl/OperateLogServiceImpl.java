package com.itheima.service.impl;

import com.itheima.pojo.entity.OperateLog;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作日志服务实现类
 */
@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public void save(OperateLog operateLog) {
        operateLogMapper.insert(operateLog);
    }
}
