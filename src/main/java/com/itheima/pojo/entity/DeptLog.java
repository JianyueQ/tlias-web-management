package com.itheima.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeptLog {

    private Integer id;

    private String log;

    private LocalDateTime createTime;
}
