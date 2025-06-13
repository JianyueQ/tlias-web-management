package com.itheima.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.dto.EmpDto;
import com.itheima.pojo.dto.EmpPageDto;
import com.itheima.pojo.entity.Emp;
import com.itheima.pojo.vo.EmpVo;
import com.itheima.result.PageResult;
import com.itheima.service.EmpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    @Override
    public PageResult page(EmpPageDto empPageDto) {

        Integer pageNum = empPageDto.getPage();
        Integer pageSize = empPageDto.getPageSize();

        PageHelper.startPage(pageNum, pageSize);

        Page<Emp> page = empMapper.list(empPageDto);

        long total = page.getTotal();
        List<Emp> rows = page.getResult();

        return new PageResult(total,rows);
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void add(EmpDto empDto) {
        Emp emp = new Emp();
        BeanUtils.copyProperties(empDto, emp);
        empMapper.add(emp);
    }

    @Override
    public EmpVo findById(Integer id) {
        return empMapper.findById(id);
    }

    @Override
    public void update(EmpDto empDto) {
        Emp emp = new Emp();
        BeanUtils.copyProperties(empDto, emp);
        empMapper.update(emp);
    }
}
