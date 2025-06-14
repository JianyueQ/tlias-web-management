package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.entity.Dept;
import com.itheima.pojo.entity.DeptLog;
import com.itheima.pojo.vo.DeptVo;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;


    @Override
    public List<DeptVo> list() {
        return deptMapper.list();
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
        empMapper.deleteByDeptId(id);
//        try {

//            if (true){
//                throw new AccountNotFoundException("无法删除该部门");
//            }
//            int i = 1/0;
//        }finally {
            //将每次操作都抄入日志
//            DeptLog deptLog = new DeptLog();
//            deptLog.setCreateTime(LocalDateTime.now());
//            deptLog.setLog("执行了删除操作,删除了"+id+"号部门");
//            deptLogService.addLog(deptLog);
//        }


    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    @Override
    public DeptVo findById(Integer id) {
        return deptMapper.findById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        dept.setCreateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }


}
