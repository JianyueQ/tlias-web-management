package com.itheima.mapper;

import com.itheima.pojo.entity.DeptLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {

    void instart(DeptLog deptLog);
}
