package com.spring.databasemigration.databasemigration.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spring.databasemigration.databasemigration.pojo.User;

@Mapper
public interface UserDao  extends BaseMapper<User>{

}
