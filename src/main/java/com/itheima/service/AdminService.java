package com.itheima.service;

import com.itheima.pojo.entity.Login;
import com.itheima.pojo.vo.AdminLoginVo;

public interface AdminService {
    AdminLoginVo login(Login login);
}
