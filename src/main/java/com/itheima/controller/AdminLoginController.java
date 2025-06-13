package com.itheima.controller;

import com.itheima.pojo.entity.Login;
import com.itheima.pojo.vo.AdminLoginVo;
import com.itheima.result.Result;
import com.itheima.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public Result login(@RequestBody Login login) {
        log.info("用户信息;{}",login);
        try {
            AdminLoginVo  adminLoginVo = adminService.login(login);
            return Result.success(adminLoginVo);
        } catch (Exception e) {
            log.error(String.valueOf(e));
            return Result.error("NOT_LOGIN");
        }
    }


}
