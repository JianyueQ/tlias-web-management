package com.itheima.mapper;

import com.itheima.pojo.entity.Emp;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {


    Emp selectByUsername(@NonNull String username);
}
