package spring_message_producer.service;

import java.util.List;

import spring_message.common.dao.servcie.BaseService;
import spring_message_producer.pojo.Student;
import spring_message_producer.vo.StudentVo;

public interface StudentService  extends BaseService{

	/**
	 * @Description:  vo对象返回
	 * @date: 2020年10月4日 下午2:47:08
	 * @param stu
	 * @return
	 */
	public List<StudentVo> queryVo(Student stu);
}
