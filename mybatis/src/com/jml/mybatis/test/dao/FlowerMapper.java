package com.jml.mybatis.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.jml.mybatis.test.pojo.Flower;

public interface FlowerMapper {

	@Select("select * from flower")
	public List<Flower> queryList();
}
