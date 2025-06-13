package com.itheima.mapper;

import com.itheima.pojo.entity.Dept;
import com.itheima.pojo.vo.DeptVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    List<DeptVo> list();

    void deleteById(Integer id);

    void add(Dept dept);

    DeptVo findById(Integer id);

    void update(Dept dept);
}
