package com.itheima.mapper;

import com.itheima.pojo.entity.OperateLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志 Mapper 接口
 */
@Mapper
public interface OperateLogMapper {

    /**
     * 插入一条操作日志记录
     */
    void insert(OperateLog operateLog);
}
