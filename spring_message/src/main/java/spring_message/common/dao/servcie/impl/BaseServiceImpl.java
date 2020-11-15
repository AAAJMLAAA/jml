package spring_message.common.dao.servcie.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import spring_message.common.dao.BaseDao;
import spring_message.common.dao.servcie.BaseService;

public class BaseServiceImpl implements BaseService{

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public <T> void insert(T stu) {
		baseDao.insert(stu);
	}

	@Override
	public <T> void update(T stu) {
		baseDao.update(stu);
	}

	@Override
	public <T> List<T> queryCondition(T stu) {
		return baseDao.queryCondition(stu);
	}

	@Override
	public <T> long queryConditionCount(T stu) {
		return baseDao.queryConditionCount(stu);
	}

	@Override
	public void deleteById(String id) {
		baseDao.deleteById(id);
	}

}
