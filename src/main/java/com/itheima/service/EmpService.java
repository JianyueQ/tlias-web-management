package com.itheima.service;

import com.itheima.pojo.dto.EmpDto;
import com.itheima.pojo.dto.EmpPageDto;
import com.itheima.pojo.vo.EmpVo;
import com.itheima.result.PageResult;

import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    PageResult page(EmpPageDto empPageDto);

    void delete(List<Integer> ids);

    void add(EmpDto empDto);

    EmpVo findById(Integer id);

    void update(EmpDto empDto);
}
