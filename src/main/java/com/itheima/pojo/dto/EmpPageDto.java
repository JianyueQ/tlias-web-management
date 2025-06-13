package com.itheima.pojo.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpPageDto {

    private String name;
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  end;

    @NonNull
    private Integer page;
    @NonNull
    private Integer pageSize;
}
