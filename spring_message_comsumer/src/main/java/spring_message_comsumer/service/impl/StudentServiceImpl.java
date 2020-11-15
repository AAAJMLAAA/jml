package spring_message_comsumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import spring_message.common.dao.servcie.impl.BaseServiceImpl;
import spring_message_comsumer.dao.StudentDao;
import spring_message_comsumer.pojo.Student;
import spring_message_comsumer.service.StudentService;

@Service
public class StudentServiceImpl extends BaseServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	// 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
	@JmsListener(destination = "ActiveMQQueue")
	// SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
	@SendTo("SQueue")
	@Override
	public void handleMessage(String msg) {
		ObjectMapper objectMapper = new ObjectMapper();
		Student student = null;
		try {
			student = objectMapper.readValue(msg, Student.class);
			System.out.println(student);
			studentDao.insert(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
