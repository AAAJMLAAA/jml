package com.spring.demo.demoStart.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spring.demo.demoStart.pojo.Student;

@Mapper
public interface StudentDao extends BaseMapper<Student>{

}
