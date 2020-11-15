package spring_message.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import spring_message.common.dao.BaseDao;

public class BaseDaoImpl implements BaseDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public <T> void insert(T stu) {
		sqlSessionTemplate.insert("insert", stu);
	}

	@Override
	public <T> void update(T stu) {
		sqlSessionTemplate.update("updateById", stu);
	}

	@Override
	public <T> List<T> queryCondition(T stu) {
		List<T> selectList = sqlSessionTemplate.selectList("queryCondition", stu);
		if (selectList != null)
		{
			return selectList;
		}
		return new ArrayList<>();
	}

	@Override
	public <T> long queryConditionCount(T stu) {
		Object selectOne = sqlSessionTemplate.selectOne("queryConditionCount", stu);
		return selectOne == null ? 0 : (long)selectOne;
	}

	@Override
	public void deleteById(String id) {
		sqlSessionTemplate.delete("deleteById", id);
	}

}
