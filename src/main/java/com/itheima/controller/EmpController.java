package com.itheima.controller;

import com.itheima.pojo.dto.EmpDto;
import com.itheima.pojo.dto.EmpPageDto;
import com.itheima.pojo.vo.EmpVo;
import com.itheima.result.PageResult;
import com.itheima.result.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理Controller
 */
@RestController
@RequestMapping("/emps")
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(EmpPageDto empPageDto) {
        log.info("分页查询员工信息，参数：{}", empPageDto);
        PageResult pageResult = empService.page(empPageDto);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除员工信息，参数：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody EmpDto empDto) {
        log.info("添加员工信息，参数：{}", empDto);
        empService.add(empDto);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("查询员工信息，参数：{}", id);
        EmpVo empVo = empService.findById(id);
        return Result.success(empVo);
    }

    @PutMapping
    public Result update(@RequestBody EmpDto empDto) {
        log.info("更新员工信息，参数：{}", empDto);
        empService.update(empDto);
        return Result.success();
    }
}
