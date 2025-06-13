package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.annotations.AutoFile;
import com.itheima.enumeration.OperationType;
import com.itheima.pojo.dto.EmpDto;
import com.itheima.pojo.dto.EmpPageDto;
import com.itheima.pojo.entity.Emp;
import com.itheima.pojo.vo.EmpVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {
    Page<Emp> list(EmpPageDto empPageDto);

    void delete(List<Integer> ids);

    @AutoFile(value = OperationType.INSERT)
    void add(Emp emp);

    EmpVo findById(Integer id);

    @AutoFile(value = OperationType.UPDATE)
    void update(Emp emp);

    void deleteByDeptId(Integer id);
}
