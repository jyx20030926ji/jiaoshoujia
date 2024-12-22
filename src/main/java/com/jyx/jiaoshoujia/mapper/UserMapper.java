package com.jyx.jiaoshoujia.mapper;

import com.jyx.jiaoshoujia.enity.po.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface UserMapper {


    @Select("select * from user where account=#{account}")
    User getUserByAccount(String account);






}
