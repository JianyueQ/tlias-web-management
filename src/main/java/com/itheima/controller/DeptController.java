package com.itheima.controller;

import com.itheima.annotations.OperateLog;
import com.itheima.pojo.entity.Dept;
import com.itheima.pojo.vo.DeptVo;
import com.itheima.result.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
//@Lazy  // 懒加载 单例
@Scope("prototype") // 多例
@RestController()
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    public DeptController() {
        System.out.println("deptController创建了...");
    }

//    @RecordTime
    @OperateLog
    @GetMapping
    public Result list(){
        List<DeptVo> deptVoList = deptService.list();
        return Result.success(deptVoList);
    }
    @OperateLog
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        deptService.delete(id);
        return Result.success();
    }
    @OperateLog
    @PostMapping()
    public Result add(@RequestBody Dept dept){
        deptService.add(dept);
        return Result.success();
    }
    @OperateLog
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        DeptVo deptVo = deptService.findById(id);
        return Result.success(deptVo);
    }
    @OperateLog
    @PutMapping
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
        return Result.success();
    }
}
