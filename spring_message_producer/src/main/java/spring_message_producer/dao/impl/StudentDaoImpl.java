package spring_message_producer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring_message.common.dao.impl.BaseDaoImpl;
import spring_message_producer.dao.StudentDao;
import spring_message_producer.pojo.Student;
import spring_message_producer.vo.StudentVo;

@Repository
public class StudentDaoImpl extends BaseDaoImpl implements StudentDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;


	@Override
	public List<StudentVo> queryVo(Student stu) {
		List<StudentVo> selectList = sqlSessionTemplate.selectList("queryConditionVo", stu);
		if (selectList != null)
		{
			return selectList;
		}
		return new ArrayList<>();
	}
}
