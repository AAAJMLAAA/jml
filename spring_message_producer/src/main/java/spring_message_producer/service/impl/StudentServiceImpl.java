package spring_message_producer.service.impl;

import java.util.List;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spring_message.common.dao.servcie.impl.BaseServiceImpl;
import spring_message_producer.dao.StudentDao;
import spring_message_producer.pojo.Student;
import spring_message_producer.service.StudentService;
import spring_message_producer.vo.StudentVo;

@Service
public class StudentServiceImpl extends BaseServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
    @Autowired
    private Queue queue;
    //注入springboot封装的工具类
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    
	@Override
	public <T> void insert(T stu) {
		ObjectMapper objectMapper = new ObjectMapper();
		String writeValueAsString = null;
		try {
			writeValueAsString = objectMapper.writeValueAsString(stu);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 发送信息
		jmsMessagingTemplate.convertAndSend(queue, writeValueAsString);
		super.insert(stu);
	}

	@Override
	public List<StudentVo> queryVo(Student stu) {
		return studentDao.queryVo(stu);
	}

}
