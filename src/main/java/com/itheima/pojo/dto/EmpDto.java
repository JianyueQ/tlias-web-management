package com.itheima.pojo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDto{

    private Integer id;

    @NonNull
    private String username;

    @NonNull
    private String name;

    @NonNull
    private Short gender;

    private String image;

    private Integer deptId;

    private LocalDate entrydate;

    private Short job;

}
