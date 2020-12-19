package com.spring.databasemigration.databasemigration.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spring.databasemigration.databasemigration.pojo.ColumnEntity;
import com.spring.databasemigration.databasemigration.pojo.TableEntity;

@Mapper
public interface TableDao  extends BaseMapper<TableEntity>{

	@Select("select TABLE_NAME AS tableName,TABLE_COMMENT AS tableComment from information_schema.TABLES where TABLE_SCHEMA=(select database())")
    List<TableEntity> listTable();

    @Select("select COLUMN_NAME AS columnName,"
    		+ " IS_NULLABLE AS nullAble,"
    		+ " DATA_TYPE AS dataType,"
    		+ " CHARACTER_MAXIMUM_LENGTH AS maxLength,"
    		+ " COLUMN_KEY AS priKey,"
    		+ " COLUMN_COMMENT AS columnComment"
    		+ " from information_schema.COLUMNS where TABLE_SCHEMA = (select database()) and TABLE_NAME=#{tableName}")
    List<ColumnEntity> listTableColumn(String tableName);
    
    void runSql(@Param("sqlPath") String sqlPath) throws Exception ;
    
    @Select("select * from ${tableName}")
    List<Map<String,Object>> selectName(@Param("tableName") String tableName);
}
