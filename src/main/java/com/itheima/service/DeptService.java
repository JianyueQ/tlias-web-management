package com.itheima.service;

import com.itheima.pojo.entity.Dept;
import com.itheima.pojo.vo.DeptVo;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    List<DeptVo> list();

    void delete(Integer id);

    void add(Dept dept);

    DeptVo findById(Integer id);

    void update(Dept dept);
}
