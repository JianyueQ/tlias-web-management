package com.itheima.service.impl;

import com.itheima.Exception.AccountNotFoundException;
import com.itheima.Exception.PasswordErrorException;
import com.itheima.Utils.JwtUtils;
import com.itheima.constant.EmplConstant;
import com.itheima.constant.LoginMessageConstant;
import com.itheima.mapper.AdminMapper;
import com.itheima.pojo.entity.Emp;
import com.itheima.pojo.entity.Login;
import com.itheima.pojo.vo.AdminLoginVo;
import com.itheima.properties.JwtProperties;
import com.itheima.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private JwtProperties jwtProperties;


    @Override
    public AdminLoginVo login(Login login) {
        //根据用户名查询全部信息
        Emp emp = adminMapper.selectByUsername(login.getUsername());
        if (emp.getUsername() == null || !login.getUsername().equals(emp.getUsername())) {
            throw new AccountNotFoundException(LoginMessageConstant.USER_NOT_EXIST);
        }
        //用户名不为空,比对该用户名的密码是否正确
        if (!emp.getPassword().equals(login.getPassword())) {
            throw new PasswordErrorException(LoginMessageConstant.PASSWORD_ERROR);
        }
        //生成token
        Map<String, Object> claims = new HashMap<>();
        claims.put(EmplConstant.USERNAME, emp.getUsername());
        claims.put(EmplConstant.EMP_ID, emp.getId());
        String token = JwtUtils.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
        log.info("token:{}", token);
        AdminLoginVo adminLoginVo = new AdminLoginVo();
        adminLoginVo.setToken(token);
        return adminLoginVo;
    }
}
